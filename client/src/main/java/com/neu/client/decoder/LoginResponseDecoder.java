package com.neu.client.decoder;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.LoginResponse;
import com.neu.client.util.GlobalConstant;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class LoginResponseDecoder implements IDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(LoginResponseDecoder.class);
	
	public Object doDecode(ChannelHandlerContext ctx, ByteBuf frame) {
		// TODO Auto-generated method stub
		LOG.info("[LoginResponseDecoder]");
		LoginResponse response = new LoginResponse();
		frame.readShort();
		byte result = frame.readByte();
		response.setResult(result);
		
		if(result == 0){
			response.setOnlinnum(frame.readShort());
			String userList = frame.readBytes(frame.readableBytes()).toString(Charset.forName(GlobalConstant.CHARSET_UTF8));
			List<String> list = new ArrayList<String>();
			String[] str = userList.split(" ");
			for(int i=0;i<str.length;i++){
				list.add(str[i].trim());
			}
			response.setUserlist(list);
		}
		return response;
	}

}
