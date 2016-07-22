package com.neu.demo.encoder;

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.response.LoginResponse;

public class LoginResponseEncoder implements IEncoder{

	private static final Logger LOG = LoggerFactory.getLogger(LoginResponseEncoder.class);
			
	public ChannelBuffer doEncoder(ChannelHandlerContext ctx, Channel channel,
			Object msg) {
		// TODO Auto-generated method stub
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
			LoginResponse response  = (LoginResponse) msg;
			buffer.writeByte(response.getCommandId());
			
			if(response.getResult() == 0){
				short length = 0;
				
				List<String> users = response.getOnlinuser();
				StringBuffer sb = new StringBuffer();
				if(users != null && users.size()>0){
					for(String str : users){
						sb.append(str + " ");
					}
					sb.toString();
				}
				length = (short) (3 + sb.toString().getBytes().length);
				
				buffer.writeShort(length);
				buffer.writeByte(response.getResult());
				buffer.writeShort(response.getOnlinnum());
				buffer.writeBytes(sb.toString().getBytes());
			}else{
				buffer.writeShort(response.getMsglength());
				buffer.writeShort(response.getResult());
			}
			 
			LOG.info(channel + "[Encoder--LoginResponse],userid:"+response.getUserid()+",result:"+response.getResult());
		return buffer;
	}

}
