package com.neu.client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.neu.client.bean.O2MMessage;
import com.neu.client.cache.SysVariables;
import com.neu.client.client.Client;


public class MenuView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	public static JTextField onlinNum;
	
	public static JTextArea text;
	
	public static JTextArea messageArea;
	
	public static JPanel contentPane; 
	
	public static JScrollPane scrollPane;
	
	public static JButton send;
	
	public MenuView(){
		
		contentPane=new JPanel();
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		
		this.setLayout(null);
		
		onlinNum = new JTextField();
		onlinNum.setBounds(20, 20, 330, 26);
		this.add(onlinNum);
		onlinNum.setText("在线用户数：" + SysVariables.response.getOnlinnum() + "  当前用户： " + SysVariables.user);
		onlinNum.setEditable(false);
		
		contentPane.add(onlinNum);
		
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
		    contentPane.add(landing);
		}
		
		
		JTextField n1 = new JTextField();
		n1.setBounds(20, 348, 100, 26);
		contentPane.add(n1);
		n1.setText("群发记录");
		n1.setEditable(false);
		/*****************************/
		messageArea = new JTextArea(5, 10);
		messageArea.setEditable(false);
		scrollPane=new JScrollPane();
        scrollPane.setBounds(20, 372, 330, 100);
        contentPane.add(scrollPane,BorderLayout.CENTER);
        scrollPane.setViewportView(messageArea);
		
		
		JTextField n2 = new JTextField();
		n2.setBounds(20, 473, 100, 26);
		contentPane.add(n2);
		n2.setText("群发对话框");
		n2.setEditable(false);
		/*****************************/
		text = new JTextArea(5, 10);
		text.setBounds(20, 500, 330, 100);
		contentPane.add(text);
		
		send = new JButton("Enter");
		send.setBounds(20, 600, 330, 24);
		contentPane.add(send);
		send.addActionListener(this);
		
		initJFrame();
	}
	
	
	private void initJFrame() {
		this.setLocation(700, 30);
		this.setSize(378, 679);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("image\\QQ4.jpg").getImage());
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				Client.shutDown();
				super.windowClosed(e);
			}
			
		});
	}


	public void actionPerformed(ActionEvent e) {
		String message = text.getText().trim();
		O2MMessage m = new O2MMessage();
		m.setCommandId((byte)0x0005);
		m.setMessage(message);
		short len = (short) (30 + message.getBytes().length);
		m.setMsglength(len);
		m.setSender(SysVariables.user);
		SysVariables.channel.write(m);
		messageArea.append(SysVariables.user + ": " + message + "\r\n");
		text.setText("");
	}
	
	
	
}
