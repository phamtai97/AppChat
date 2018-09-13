/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatserver.nettyserver;

import com.ajpham.appchatserver.database.MyStorage;
import com.ajpham.appchatserver.database.RockDBApp;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import com.ajpham.appchatserver.dto.ResponseDTO;
import com.ajpham.appchatserver.dto.RequestDTO;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author taipham
 */
public class ServerInitializer extends ChannelInitializer<Channel> {

    private static MyStorage _storage;
    private static ChannelGroup _channelGroup;
    private static final String path = "./storage";
    private static ConcurrentHashMap<String, ChannelId> _mapUserChannel; //userName, channelId

    public ServerInitializer() {
        _storage = new RockDBApp(path);
        _storage.connect();
        System.out.println("Connect database success");
        _channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        _mapUserChannel = new ConcurrentHashMap<>();

    }

    @Override
    protected void initChannel(Channel c) throws Exception {
        ChannelPipeline pipeline = c.pipeline();
        pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
        pipeline.addLast("protobufDecoderReq", new ProtobufDecoder(RequestDTO.Request.getDefaultInstance()));
        pipeline.addLast("protobufDecoderRes", new ProtobufDecoder(ResponseDTO.Response.getDefaultInstance()));
        pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufEncoder", new ProtobufEncoder());
        pipeline.addLast(new EchoServerInboundHandler(_storage, _channelGroup, _mapUserChannel));
    }
}
