package com.neu.client.decoder;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class MessageDecoder extends LengthFieldBasedFrameDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(MessageDecoder.class);
	
	private Map<Byte,IDecoder> decoders;
	
	public MessageDecoder(int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength,Map<Byte,IDecoder> decoders) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		this.decoders = decoders;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object decode(ChannelHandlerContext context, ByteBuf buf)
			throws Exception {
		// TODO Auto-generated method stub
		ByteBuf frame = (ByteBuf) super.decode(context, buf);
		if(frame == null){
			System.out.println("不能解析：");
			return null; 
		}
		byte a = frame.readByte();
		IDecoder decoder = decoders.get(a);
		if(decoder !=null){
			return decoder.doDecode(context, frame);
		}else{
			LOG.info("[没有对应的Decoder]");
		}
		return null;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
	

}
