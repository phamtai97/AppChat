/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taipham
 */
public class NettyServer {

    private static int _port;

    public NettyServer(int port) {
        this._port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //start server
            ChannelFuture channelFuture = serverBootstrap.bind(_port).sync();

            System.out.println(NettyServer.class.getName()
                    + " started and listening for connections on " + channelFuture.channel().localAddress());

            // Wait until the server socket is closed.
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            Logger.getLogger(NettyServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

            // Wait until all threads are terminated.
            bossGroup.terminationFuture().sync();
            workerGroup.terminationFuture().sync();
            System.out.println("server shutdown");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyServer(9999).start();
    }
}
