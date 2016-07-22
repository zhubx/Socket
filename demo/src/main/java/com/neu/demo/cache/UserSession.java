package com.neu.demo.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;

import com.neu.demo.bean.LoginMessage;

public class UserSession {

	private UserSession(){}
	
	private static class UserSessionHolder{
		private static final UserSession instance = new UserSession();
	}
	
	public static UserSession getInstance(){
		return UserSessionHolder.instance;
	}
	
	private static Map<LoginMessage,Channel> sessions = new ConcurrentHashMap<LoginMessage, Channel>();
	
	public void saveSession(LoginMessage key,Channel channel){
		sessions.put(key, channel);
	}
	
	/**
	 * 根据channel返回LoginMessage
	 * @param channel
	 * @return
	 */
	public LoginMessage getLoginMessage(Channel channel){
		Set<Entry<LoginMessage, Channel>> set = sessions.entrySet();
		Iterator<Entry<LoginMessage, Channel>>  iterator = set.iterator();
		
		while(iterator.hasNext()){
			Entry<LoginMessage, Channel> entry = iterator.next();
			if(entry.getValue() == channel){
				return entry.getKey();
			}
		}
		return null;
	}
	
	/**
	 * 根据用户id查询channel是否存在
	 * @param key
	 * @return
	 */
	public Channel getSession(String key){
		Iterator<LoginMessage> iterator = sessions.keySet().iterator();
		while(iterator.hasNext()){
			LoginMessage user = iterator.next();
			if(key.equals(user.getUserid())){
				return sessions.get(user);
			}
		}
		return null;
	}
	
	public short getOnlinnum(){
		return (short)sessions.size();
	}
	
	/**
	 * 获取所有在线用户用户名 ...用户ID也应该获取 
	 * @return
	 */
	public List<String> getOnlinuser(){
		Iterator<LoginMessage> iterator = sessions.keySet().iterator();
		List<String> list = new ArrayList<String>();
		while(iterator.hasNext()){
			list.add(iterator.next().getUsername());
		}
		return list;
	}
	
	public void remove(LoginMessage message){
		sessions.remove(message);
	}
	
}
