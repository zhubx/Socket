package com.neu.demo.decoder;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.bean.LoginMessage;
import com.neu.demo.bean.Message;
import com.neu.demo.util.GlobalConstant;
import com.neu.demo.util.StringUtil;

public class MessageDecoder extends LengthFieldBasedFrameDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(MessageDecoder.class);
	
	public MessageDecoder(int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		// TODO Auto-generated method stub
		if(decodeAble(ctx,channel,buffer.copy())){
			LOG.info("receive:" + StringUtil.Bytes2HexString(buffer.copy().array()));
			
			if(buffer.readByte() == 0x0001){
				ChannelBuffer c = buffer.readBytes(buffer.readShort());
				String[] body = c.toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).split(" ");
				LoginMessage message = new LoginMessage(body[0],body[1]);
				return message;
			}
			if(buffer.readByte() == 0x0002){
				ChannelBuffer c = buffer.readBytes(buffer.readShort());
				Message message = new Message();
				if(c.readByte() == 1){
					message.setMessageType((byte)1);
					message.setMessage(c.toString());
				}else{
					message.setMessageType((byte)2);
					String[] str = c.toString().split(" ", 2);
					message.setIncept(str[0]);
					message.setMessage(str[1]);
				}
				return message;
			}
		}
		return buffer;
	}
	
	public boolean decodeAble(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer){
		try {
			ChannelBuffer  frame = (ChannelBuffer) super.decode(ctx, channel, buffer);
			if(frame == null || !frame.readable()){
				LOG.info("invalid:" + StringUtil.Bytes2HexString(buffer.copy().array()));
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

}
