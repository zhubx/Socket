package com.neu.client.handler;

import com.neu.client.bean.ServerMessage;
import com.neu.client.cache.SysVariables;
import com.neu.client.view.MenuView;
import com.neu.client.view.TalkView;

import io.netty.channel.ChannelHandlerContext;

public class ServerMsgHandler implements IHandler{

	@SuppressWarnings("static-access")
	public void doHandler(ChannelHandlerContext ctx, Object msg) {
		// TODO Auto-generated method stub
		ServerMessage message = (ServerMessage)msg;
		if(message.getType() ==1){
			if(!message.getSender().equals(SysVariables.user)){
				MenuView.messageArea.append(message.getSender() + ": " + message.getContent() + "\r\n");
			}
		}else{
			//获取对应会话窗口 如果没有新建
			TalkView view = SysVariables.session.get(message.getSender());
			if(view == null){
				view = new TalkView(message.getSender());
				SysVariables.session.put(message.getSender(), view);
				view.messageArea.append(message.getSender() + ": " + message.getContent() + "\r\n");
			}else{
				view.messageArea.append(message.getSender() + ": " + message.getContent() + "\r\n");
			}
		}
		
	}

}
