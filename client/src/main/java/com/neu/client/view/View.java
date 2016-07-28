package com.neu.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.ProtocolMsg;
import com.neu.client.cache.SysVariables;
import com.neu.client.client.Client;
import com.neu.client.decoder.IDecoder;
import com.neu.client.encoder.IEncoder;
import com.neu.client.handler.IHandler;

public class View extends JFrame implements ActionListener{

	private static final Logger LOG = LoggerFactory.getLogger(View.class);
	
	private int port;
	
	private String ip;
	
	private Map<Byte,IDecoder> decoders;
	
	private Map<String,IHandler> handlers;
	
	private Map<String,IEncoder<ProtocolMsg>> encoders;
	
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JPasswordField passwordField;
	
	public static JTextField msg;
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String username = name.getText().trim();
		System.out.println("[username]" + username);
		
		if (e.getActionCommand().equals("登陆")) {
			String password = new String(passwordField.getPassword());
			System.out.println("[password]" + password);
			
			//初始化客户端连接
			Client client = new Client();
			System.out.println("port:" + this.port);
			System.out.println("ip:" + this.ip);
			client.init(this.port,this.ip,username,password,decoders,handlers,encoders);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(SysVariables.channel !=null){
				LOG.info("登陆成功");
				SysVariables.user = username;
				this.setVisible(false);
				new MenuView();
			}else{
				msg = new JTextField(23);
				msg.setBounds(110, 180, 165, 26);
				if(SysVariables.loginResult ==1){
					LOG.error("用户名或密码错误");
					msg.setText("用户名或密码错误");
					msg.setEditable(false);
					this.add(msg);
					Client.shutDown();
				}else{
					LOG.error("未知错误");
					msg.setText("未知错误");
					msg.setEditable(false);
					this.add(msg);
					Client.shutDown();
				}
			}
			
				
				
				
		} else if (e.getActionCommand().equals("注册账号")) {
			name.setText("");
			passwordField.setText("");

		}
	}
	
	public View() {
		this.setTitle("登录页面");
		this.setLayout(null);
		 
		name = new JTextField(23);
		name.setBounds(110, 115, 165, 26);
		this.add(name);
		passwordField = new JPasswordField(16);
		passwordField.setBounds(110, 148, 165, 26);
		this.add(passwordField);
	 
		// 登陆：
		JButton landing = new JButton("登陆");
		landing.setBounds(300, 220, 70, 24);
		this.add(landing);
		landing.addActionListener(this);
		 
		 

		initJFrame();
	}
	
	private void initJFrame() {
		this.setLocation(420, 270);
		this.setSize(378, 279);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("image\\QQ4.jpg").getImage());
		this.setVisible(true);
		
	}
	
	public final void setPort(int port) {
		this.port = port;
	}

	public final void setIp(String ip) {
		this.ip = ip;
	}


	public final void setDecoders(Map<Byte, IDecoder> decoders) {
		this.decoders = decoders;
	}

	public final void setHandlers(Map<String, IHandler> handlers) {
		this.handlers = handlers;
	}

	public final void setEncoders(Map<String, IEncoder<ProtocolMsg>> encoders) {
		this.encoders = encoders;
	}
	
	
}
