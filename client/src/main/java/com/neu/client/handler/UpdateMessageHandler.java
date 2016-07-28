package com.neu.client.handler;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.UpdateMessage;
import com.neu.client.cache.SysVariables;
import com.neu.client.view.MenuView;
import com.neu.client.view.TalkView;

import io.netty.channel.ChannelHandlerContext;

public class UpdateMessageHandler implements IHandler{

	private static final Logger LOG = LoggerFactory.getLogger(UpdateMessageHandler.class);
			
	public void doHandler(ChannelHandlerContext ctx, Object msg) {
		LOG.info("[UpdateMessageHandler]");
		UpdateMessage message = (UpdateMessage)msg;
		SysVariables.response.setOnlinnum((short) (SysVariables.response.getOnlinnum() + message.getType()));
		MenuView.onlinNum.setText("在线用户数：" + SysVariables.response.getOnlinnum() + "  当前用户： " + SysVariables.user);
		if(message.getType() ==1){
			SysVariables.response.getUserlist().add(message.getName());
			UpdateView();
			//MenuView.contentPane.remove(new );
		}else{
			SysVariables.response.getUserlist().remove(message.getName());
			UpdateView();
		}
		
	}
	
	/**
	 * 更新面板
	 */
	private void UpdateView(){
		//保存记录
		//String history = MenuView.messageArea.getText();
		MenuView.contentPane.removeAll();
		MenuView.contentPane.add(MenuView.onlinNum);
		List<String> userList = SysVariables.response.getUserlist();
		JButton landing;
		int l = 50;
		for(final String str : userList){
		    landing = new JButton(str);
		    l = l + 24;
		    landing.setBounds(10, l, 350, 24);
		    if(!str.equals(SysVariables.user)){
		    	//添加监听事件  双击后新建对话窗口 
			    landing.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						SysVariables.session.put(str, new TalkView(str));
					}
			    	
				});
		    }
		    MenuView.contentPane.add(landing);
		}
		JTextField n1 = new JTextField();
		n1.setBounds(20, 348, 100, 26);
		MenuView.contentPane.add(n1);
		n1.setText("群发记录");
		n1.setEditable(false);
		
		MenuView.contentPane.add(MenuView.scrollPane,BorderLayout.CENTER);
		
		JTextField n2 = new JTextField();
		n2.setBounds(20, 473, 100, 26);
		MenuView.contentPane.add(n2);
		n2.setText("群发对话框");
		n2.setEditable(false);
		
		MenuView.contentPane.add(MenuView.text);
		
		MenuView.contentPane.add(MenuView.send);
		MenuView.contentPane.updateUI();
	}

}
