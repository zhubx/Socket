package com.neu.demo.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;

/**
 * 用户
 * @author zhubx
 *
 */
public class UserKeySession {

	private UserKeySession(){}
	
	private static Map<String,Channel> map = new ConcurrentHashMap<String,Channel>();
	
	private static class UserKeySessionHolder{
		private static final UserKeySession instance = new UserKeySession();
	}
	
	public static UserKeySession getInstance(){
		return UserKeySessionHolder.instance;
	}
	
	public void put(String key,Channel channel){
		map.put(key, channel);
	}
	
	public Channel getChannel(String key){
		return map.get(key);
	}
	
	/**
	 * 返回有效channel
	 * @return
	 */
	public List<Channel> getAllChannel(){
		List<Channel> list = new ArrayList<Channel>();
		Set<Entry<String, Channel>> set = map.entrySet();
		Iterator<Entry<String, Channel>> iterator = set.iterator();
		while(iterator.hasNext()){
			Channel c = iterator.next().getValue();
			if(c !=null && c.isConnected()){
				list.add(c);
			}
		}
		return list;
	}
	
	public void remove(String key){
		map.remove(key);
	}
	
	public int size(){
		return map.size();
	}
}
