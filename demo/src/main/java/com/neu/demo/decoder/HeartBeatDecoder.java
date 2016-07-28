package com.neu.demo.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

import com.neu.demo.bean.HeartBeat;

public class HeartBeatDecoder implements IDecoder{

	public Object doDecode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) {
		// TODO Auto-generated method stub
		buffer.readBytes(buffer.readShort());
		HeartBeat heart = new HeartBeat();
		return heart;
	}

}
