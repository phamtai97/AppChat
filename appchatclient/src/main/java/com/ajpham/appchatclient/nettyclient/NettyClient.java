/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author taipham
 */
public class NettyClient {

    private static String _host;
    private static int _port;
    private static EventLoopGroup _workerGroup;
    private static EchoClientInboundHandler _handler;
    private static String _userName;

    public NettyClient(String host, int port) {
        this._host = host;
        this._port = port;
    }

    public ChannelInitializer<Channel> CreateInitializer() {
        return new ClientInitializer(this._handler);
    }

    public void start() throws IOException {
        this._workerGroup = new NioEventLoopGroup();
        this._handler = new EchoClientInboundHandler();
        try {
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(_workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(CreateInitializer())
                    .option(ChannelOption.SO_KEEPALIVE, true);

            System.out.println("client connect");
            Channel channel = clientBootstrap.connect(_host, _port).sync().channel();
            this._handler.setChannel(channel);

        } catch (InterruptedException ex) {
            Logger.getLogger(NettyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Shutdown() {
        _workerGroup.shutdownGracefully();
        System.out.println("client disconnect");
    }

    public EchoClientInboundHandler getHandler() {
        return this._handler;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public String getUserName() {
        return _userName;
    }

}
