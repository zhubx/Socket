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
import com.neu.demo.bean.UpdateMessage;
import com.neu.demo.util.StringUtil;

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
		//首先将新增的用户信息推送给在线用户
		List<Channel> list = getAllChannel();
		UpdateMessage message = new UpdateMessage();
		message.setCommandId((byte)0x0010);
		message.setMsglength((short)31);
		message.setType((byte)1);
		message.setName(StringUtil.get30String(key.getUsername()));
		for(Channel c : list){
			c.write(message);
		}
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
		//将下线的用户信息推送给在线用户
		List<Channel> list = getAllChannel();
		UpdateMessage m = new UpdateMessage();
		m.setCommandId((byte)0x0010);
		m.setMsglength((short)31);
		m.setType((byte)-1);
		m.setName(StringUtil.get30String(message.getUsername()));
		for(Channel c : list){
			c.write(m);
		}
	}
	
	/**
	 * 返回有效channel
	 * @return
	 */
	public List<Channel> getAllChannel(){
		List<Channel> list = new ArrayList<Channel>();
		Set<Entry<LoginMessage, Channel>> set = sessions.entrySet();
		Iterator<Entry<LoginMessage, Channel>> iterator = set.iterator();
		while(iterator.hasNext()){
			Channel c = iterator.next().getValue();
			if(c !=null && c.isConnected()){
				list.add(c);
			}
		}
		return list;
	}
}
