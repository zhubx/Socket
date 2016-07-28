package com.neu.client.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.neu.client.bean.LoginResponse;

import io.netty.channel.Channel;

import com.neu.client.view.TalkView;


public class SysVariables {

	public static boolean clientStatus = false;
	
	public static Channel channel = null;
	
	public static int loginResult = -1;
	
	public static String user = null;
	
	public static LoginResponse response = null;
	
	public static Map<String,TalkView> session = new ConcurrentHashMap<String,TalkView>();
	
}
