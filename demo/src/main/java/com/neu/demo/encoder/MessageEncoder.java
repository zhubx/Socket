package com.neu.demo.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.response.LoginResponse;

public class MessageEncoder extends OneToOneEncoder{

	private static final Logger LOG = LoggerFactory.getLogger(MessageEncoder.class);
	
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		// TODO Auto-generated method stub
		ChannelBuffer buffer = null;
		if(msg instanceof LoginResponse){
			buffer = ChannelBuffers.dynamicBuffer();
			LoginResponse response  = (LoginResponse) msg;
			buffer.writeByte(response.getMessagetype());
			buffer.writeShort(response.getMessagelength());
			buffer.writeShort(response.getResult());
			if(response.getResult() == 0){
				buffer.writeShort(response.getOnlinnum());
				buffer.writeBytes("".getBytes());
			}
			LOG.info(channel + "[Encoder--LoginResponse],userid:"+response.getUserid()+",result:"+response.getResult());
		}
		return buffer;
	}

}
