package com.neu.client.decoder;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.ServerMessage;
import com.neu.client.util.GlobalConstant;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ServerMessageDecoder implements IDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(ServerMessageDecoder.class);
	
	public Object doDecode(ChannelHandlerContext ctx, ByteBuf frame) {
		LOG.info("[ServerMessageDecoder]");
		ServerMessage message = new ServerMessage();
		frame.readShort();
		int type = frame.readByte();
		if(type ==1){
			String sender= frame.readBytes(30).toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).trim();
			String msg = frame.readBytes(frame.readableBytes()).toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).trim();
			message.setSender(sender);
			message.setContent(msg);
			message.setType((byte)1);
		}else{
			String sender= frame.readBytes(30).toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).trim();
			String incept= frame.readBytes(30).toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).trim();
			String msg = frame.readBytes(frame.readableBytes()).toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).trim();
			message.setSender(sender);
			message.setIncept(incept);
			message.setContent(msg);
			message.setType((byte)2);
		}
		
		return message;
	}

}
