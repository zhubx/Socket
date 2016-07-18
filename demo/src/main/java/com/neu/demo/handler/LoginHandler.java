package com.neu.demo.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.bean.LoginMessage;
import com.neu.demo.cache.UserSession;
import com.neu.demo.dao.SysDao;
import com.neu.demo.response.LoginResponse;

public class LoginHandler implements IHandler{

	private static final Logger LOG = LoggerFactory.getLogger(LoginHandler.class);
	
	static Map<String,LoginMessage> map;
	static{
		map = new HashMap<String,LoginMessage>();
		LoginMessage m1 = new LoginMessage();
		m1.setUserid("1");
		m1.setUsername("zhubx");
		m1.setPassword("123");
		map.put(m1.getUsername(), m1);
	}
	
	
	SysDao sysDao;

	private LoginMessage user = null;
	
	public void doHandler(ChannelHandlerContext ctx, MessageEvent e) {
		// TODO Auto-generated method stub
		LOG.info("do LoginHandler");
		LoginMessage message = (LoginMessage) e.getMessage();
		user = map.get(message.getUsername());
		if(user != null){
			Channel channel = UserSession.getInstance().getSession(user.getUserid());
			if(channel !=null){
				channel.close();
				LOG.info("用户名为：" + user.getUsername() + "重复登录,断开原有连接");
			}
			UserSession.getInstance().saveSession(user.getUserid(),e.getChannel());
			LoginResponse response = new LoginResponse();
			response.setMessagetype((byte)0x8001);
			response.setResult((byte)0);
			response.setMessagelength((short)3);
			response.setOnlinnum((short)1);
			response.setOnlinuser(new ArrayList<String>());
			e.getChannel().write(response);
			LOG.info("用户名为：[" + user.getUsername() + "]登录成功");
		}else{
			LoginResponse response = new LoginResponse();
			response.setMessagetype((byte)0x8001);
			response.setResult((byte)1);
			response.setMessagelength((short)1);
			e.getChannel().write(response);
			LOG.info("用户名为：[" + message.getUsername() + "]鉴权失败");
		}
	}

}
