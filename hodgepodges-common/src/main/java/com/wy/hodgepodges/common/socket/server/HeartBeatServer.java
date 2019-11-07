package com.wy.hodgepodges.common.socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


/**
 * @author wy
 */
public class HeartBeatServer {

    private  int port = 9817;

    public  HeartBeatServer(int port) {
        this.port = port;
    }

    ServerBootstrap bootstrap = null;
    ChannelFuture f;

    // 检测chanel是否接受过心跳数据时间间隔（单位秒）¬
    private static final int READ_WAIT_SECONDS = 10;

    public static void main(String args[]) {
//        HeartBeatServer heartBeatServer = new HeartBeatServer(port);
//        heartBeatServer.startServer();
    }

    public void startServer() {
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        EventLoopGroup workergroup = new NioEventLoopGroup();
        try {
            bootstrap = new ServerBootstrap();
            bootstrap.group(bossgroup, workergroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HeartBeatServerInitializer());
            // 服务器绑定端口监听
            f = bootstrap.bind(port).sync();
            System.out.println("server start ,port: " + port);
            // 监听服务器关闭监听，此方法会阻塞
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }
    }


    private class HeartBeatServerInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            // 监听读操作,读超时时间为5秒，超过5秒关闭channel;
            pipeline.addLast("ping", new IdleStateHandler(READ_WAIT_SECONDS, 0, 0, TimeUnit.SECONDS));
            pipeline.addLast("decoder", new StringDecoder());
            pipeline.addLast("encoder", new StringEncoder());

            pipeline.addLast("handler", new HeartbeatServerHandler());
        }
    }

}