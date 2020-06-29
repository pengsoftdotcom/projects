package com.pengsoft.device.biz.socket;

import com.pengsoft.support.commons.json.ObjectMapper;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Console Server
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
//@Named
public class ConsoleServer extends Thread {

    private final String host;

    private final int port;

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private List<RequestHandler> handlers;

    public ConsoleServer(@Value("${pengsoft.device.host}") final String host, @Value("${pengsoft.device.port}") final int port) {
        this.host = host;
        this.port = port;
        this.start();
    }

    @Override
    public void run() {
        final EventLoopGroup bossGroup = new NioEventLoopGroup();
        final EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            final ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(final SocketChannel ch) throws Exception {
                            final var delimiter = Unpooled.copiedBuffer("\0".getBytes(StandardCharsets.UTF_8));
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(8096, delimiter));
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
                                    final var byteBuf = (ByteBuf) msg;
                                    final StringBuilder builder = new StringBuilder();
                                    while (byteBuf.isReadable()) {
                                        builder.append((char) byteBuf.readByte());
                                    }
                                    final var req = objectMapper.readValue(builder.toString(), Request.class);
                                    log.debug("received request from device('{}'): \n{}", req.getSn(), builder.toString());
                                    handlers.stream()
                                            .filter(handler -> handler.support(req))
                                            .map(handler -> handler.handle(req))
                                            .forEach(data -> {
                                                try {
                                                    final var res = new Response();
                                                    res.setSeq(req.getSeq() + 1);
                                                    res.setType(req.getType().replace("req", "rsp"));
                                                    res.setData(data);
                                                    final var value = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(res);
                                                    log.debug("sent response to device('{}'):\n{}", req.getSn(), value);
                                                    ctx.writeAndFlush(Unpooled.copiedBuffer((value + "\0").getBytes(StandardCharsets.UTF_8)));
                                                } catch (final Exception e) {
                                                    log.error("write json error", e);
                                                }
                                            });

                                }
                            });
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 8096)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            final ChannelFuture f = b.bind(host, port).sync();

            f.channel().closeFuture().sync();
        } catch (final Exception e) {
            log.error("console server start error", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
