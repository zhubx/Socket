package com.neu.demo.encoder;

import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEncoder extends OneToOneEncoder{

	public MessageEncoder(Map<String,IEncoder> encoders){
		this.encoders = encoders;
	}
	
	private Map<String,IEncoder> encoders;
	
	private static final Logger LOG = LoggerFactory.getLogger(MessageEncoder.class);
	
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		// TODO Auto-generated method stub
		IEncoder encoder = encoders.get(msg.getClass().getName());
		if(encoder != null){
			return encoder.doEncoder(ctx, channel,msg);
		}else{
			LOG.info("[没有对应的encoder]" + msg.getClass().getName());
		}
		return null;
	}

}
