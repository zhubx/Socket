package com.neu.demo.decoder;

import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.bean.LoginMessage;
import com.neu.demo.cache.UserSession;
import com.neu.demo.util.GlobalConstant;
import com.neu.demo.util.StringUtil;

public class MessageDecoder extends LengthFieldBasedFrameDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(MessageDecoder.class);
	
	private Map<Byte,IDecoder> decoders;
	
	private UserSession session = UserSession.getInstance();
	
	public MessageDecoder(int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength,Map<Byte,IDecoder> decoders) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		this.decoders = decoders;
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		if(decodeAble(ctx,channel,buffer.copy())){
			LOG.info("receive:" + StringUtil.Bytes2HexString(buffer.copy().array()));
			byte index = buffer.readByte();
			LoginMessage user = session.getLoginMessage(channel);
			if(user !=null || index == GlobalConstant.LOGIN){
				IDecoder decoder = decoders.get(index);
				if(decoder != null){
					return decoder.doDecode(ctx, channel, buffer);
				}else{
					LOG.info("[MessageDecoder]没有对应的decoder" + index);
					return null;
				}
			}
			LOG.info("[请登录]");
			return null;
		}
		return null;
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
