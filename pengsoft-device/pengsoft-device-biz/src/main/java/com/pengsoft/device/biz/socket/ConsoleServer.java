package com.pengsoft.device.biz.socket;

import com.fasterxml.jackson.databind.type.MapType;
import com.pengsoft.device.biz.facade.DeviceFacade;
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
import javax.inject.Named;
import java.util.Map;

/**
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Named
public class LonglianConsoleServer extends Thread {

    @Value("${pengsoft.device.host}")
    private String host;

    @Value("${pengsoft.device.port}")
    private int port;

    @Inject
    private DeviceFacade deviceFacade;

    @Inject
    private ObjectMapper objectMapper;

    private MapType mapType;

    public LonglianConsoleServer() {
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
                            final var delimiter = Unpooled.copiedBuffer("\0".getBytes("UTF-8"));
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
                                    if (mapType == null) {
                                        mapType = objectMapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
                                    }
                                    final Map<String, Object> req = objectMapper.readValue(builder.toString(), mapType);
                                    switch (req.get("type").toString()) {
                                        case "login_req": {
                                            final var data = Map.of("status", 0, "config", Map.of("device_name", "测试"));
                                            final var res = objectMapper.writeValueAsString(Map.of("type", "login_req", "data", data));
                                            log.debug("sent message: \n" + res);
                                            ctx.writeAndFlush(Unpooled.copiedBuffer((res + "\0").getBytes("UTF-8")));
                                            break;
                                        }
                                        default: {
                                            log.warn("unsolved message: \n" + builder.toString());
                                            break;
                                        }

                                    }
                                }
                            });
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 8096)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            final ChannelFuture f = b.bind(host, port).sync();

            f.channel().closeFuture().sync();
        } catch (final Exception e) {
            log.error("console start error", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
