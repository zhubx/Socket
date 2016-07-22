package com.neu.demo.handler;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.bean.LoginMessage;
import com.neu.demo.cache.UserKeySession;
import com.neu.demo.cache.UserSession;
import com.neu.demo.dao.SysDao;
import com.neu.demo.response.LoginResponse;

/**
 * 用户登录处理
 * @author zhubx
 *
 */
public class LoginHandler implements IHandler{

	private static final Logger LOG = LoggerFactory.getLogger(LoginHandler.class);
	
	private UserSession session = UserSession.getInstance();
	
	private UserKeySession keysession = UserKeySession.getInstance();
	
	static Map<String,LoginMessage> map;
	static{
		map = new HashMap<String,LoginMessage>();
		LoginMessage m1 = new LoginMessage();
		m1.setUserid("1");
		m1.setUsername("zhubx");
		m1.setPassword("123");
		map.put(m1.getUsername(), m1);
		
		LoginMessage m2 = new LoginMessage();
		m2.setUserid("2");
		m2.setUsername("xu");
		m2.setPassword("123");
		map.put(m2.getUsername(), m2);
	}
	
	
	SysDao sysDao;

	private LoginMessage user = null;
	
	public void doHandler(ChannelHandlerContext ctx, MessageEvent e) {
		// TODO Auto-generated method stub
		LOG.info("do LoginHandler");
		LoginMessage message = (LoginMessage) e.getMessage();
		user = map.get(message.getUsername());
		if(user != null){
			Channel channel = session.getSession(user.getUserid());
			if(channel !=null){
				channel.close();
				LOG.info("用户名为：" + user.getUsername() + "重复登录,断开原有连接");
			}
			//登录成功 更新数据库状态 
			// ...
			
			session.saveSession(user,e.getChannel());
			keysession.put(user.getUsername(), e.getChannel());
			LoginResponse response = new LoginResponse();
			response.setCommandId((byte)0x8001);
			response.setResult((byte)0);
			//response.setMessagelength((short)3);
			response.setOnlinnum(session.getOnlinnum());
			response.setOnlinuser(session.getOnlinuser());
			e.getChannel().write(response);
			LOG.info("用户名为：[" + user.getUsername() + "]登录成功");
		}else{
			LoginResponse response = new LoginResponse();
			response.setCommandId((byte)0x8001);
			response.setResult((byte)1);
			response.setMsglength((short)1);
			e.getChannel().write(response);
			LOG.info("用户名为：[" + message.getUsername() + "]鉴权失败");
		}
	}

}
