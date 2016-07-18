package com.neu.demo.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;

public class UserSession {

	private UserSession(){}
	
	private static class UserSessionHolder{
		private static final UserSession instance = new UserSession();
	}
	
	public static UserSession getInstance(){
		return UserSessionHolder.instance;
	}
	
	private static Map<String,Channel> sessions = new ConcurrentHashMap<String, Channel>();
	
	public void saveSession(String key,Channel channel){
		sessions.put(key, channel);
	}
	
	public Channel getSession(String key){
		return sessions.get(key);
	}
	
}
