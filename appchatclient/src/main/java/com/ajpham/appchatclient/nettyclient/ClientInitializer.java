/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.nettyclient;

import com.ajpham.appchatclient.dto.RequestDTO;
import com.ajpham.appchatclient.dto.ResponseDTO;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 *
 * @author taipham
 */
public class ClientInitializer extends ChannelInitializer<Channel> {

    private static EchoClientInboundHandler _handler;

    public ClientInitializer(EchoClientInboundHandler handler) {
        this._handler = handler;
    }

    @Override
    protected void initChannel(Channel c) throws Exception {
        ChannelPipeline pipeline = c.pipeline();

        pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
        pipeline.addLast("protobufDecoderRes", new ProtobufDecoder(ResponseDTO.Response.getDefaultInstance()));
        pipeline.addLast("protobufDecoderReq", new ProtobufDecoder(RequestDTO.Request.getDefaultInstance()));
        pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufEncoder", new ProtobufEncoder());
        pipeline.addLast(this._handler);
    }
}
