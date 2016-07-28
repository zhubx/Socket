package com.neu.demo.encoder;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

import com.neu.demo.bean.ServerMessage;
import com.neu.demo.util.GlobalConstant;
import com.neu.demo.util.StringUtil;

public class ServerMessageEncoder implements IEncoder{

	public ChannelBuffer doEncoder(ChannelHandlerContext ctx, Channel channel,
			Object msg) {
		// TODO Auto-generated method stub
		ServerMessage message = (ServerMessage) msg;
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeByte(message.getCommandId());
		//如果是群发消息
		if(message.getType() == 1){
			short len = (short) (30 + 1 +  message.getContent().getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)).length);
			buffer.writeShort(len);
			buffer.writeByte(message.getType());
			buffer.writeBytes(StringUtil.get30String(message.getSender()).getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)));
			buffer.writeBytes(message.getContent().getBytes());
		}else{
			short len = (short) (60 + 1 +  message.getContent().getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)).length);
			buffer.writeShort(len);
			buffer.writeByte(message.getType());
			buffer.writeBytes(StringUtil.get30String(message.getSender()).getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)));
			buffer.writeBytes(StringUtil.get30String(message.getIncept()).getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)));
			buffer.writeBytes(message.getContent().getBytes());
		}
		return buffer;
	}

}
