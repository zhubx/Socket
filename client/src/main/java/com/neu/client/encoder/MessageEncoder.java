package com.neu.client.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.ProtocolMsg;

public class MessageEncoder extends MessageToMessageEncoder<ProtocolMsg>{
	
	private static final Logger LOG = LoggerFactory.getLogger(MessageEncoder.class);
	
	public MessageEncoder(Map<String,IEncoder<ProtocolMsg>> encoders){
		this.encoders = encoders;
	}
	
	private Map<String,IEncoder<ProtocolMsg>> encoders;
	
	@Override
	protected void encode(ChannelHandlerContext arg0, ProtocolMsg message,
			List<Object> arg2) throws Exception {
		// TODO Auto-generated method stub
		IEncoder<ProtocolMsg> encoder = encoders.get(message.getClass().getName());
		if(encoder != null){
			encoder.doEncode(arg0, message,arg2);
		}else{
			LOG.info("[没有对应的encoder]" + message.getClass().getName());
		}
	}

}
