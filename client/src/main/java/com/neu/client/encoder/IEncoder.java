package com.neu.client.encoder;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import com.neu.client.bean.ProtocolMsg;


public interface IEncoder<T extends ProtocolMsg> {
 
	public void doEncode(ChannelHandlerContext arg0, T message,
			List<Object> arg2);
}
