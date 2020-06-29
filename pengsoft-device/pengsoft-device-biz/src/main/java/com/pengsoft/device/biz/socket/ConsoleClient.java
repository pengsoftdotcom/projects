package com.pengsoft.device.biz.socket;

import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.support.commons.json.ObjectMapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Console client
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Getter
@Setter
public class ConsoleClient extends Thread {

    private int seq;

    private String host;

    private int port;

    private DeviceGroup deviceGroup;

    private Device device;

    private Channel channel;

    private List<ResponseHandler> handlers;

    private ObjectMapper objectMapper;

    @Override
    public void run() {
        final EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            final Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
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
                            log.debug("received message: \n" + builder.toString());
                            final var res = objectMapper.readValue(builder.toString(), Response.class);
                            handlers.stream()
                                    .filter(handler -> handler.support(res))
                                    .forEach(handler -> handler.handle(res));
                        }
                    });
                }
            });

            final var channelFuture = b.connect(host, port).sync();
            channel = channelFuture.channel();
            channelFuture.channel().closeFuture().sync();
        } catch (final Exception e) {
            log.error("console client start error", e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public void write(final Request req) {
        try {
            seq += 2;
            req.setSeq(seq);
            final var value = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(req);
            log.debug("sent request to device('{}'):\n{}", req.getSn(), value);
            channel.writeAndFlush(Unpooled.copiedBuffer((value + "\0").getBytes(StandardCharsets.UTF_8)));
        } catch (final Exception e) {
            log.error("write json error", e);
        }
    }

    public boolean isOpen() {
        return channel != null && channel.isOpen();
    }

}
