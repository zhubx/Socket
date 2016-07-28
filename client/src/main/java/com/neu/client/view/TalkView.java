package com.neu.client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.neu.client.bean.O2OMessage;
import com.neu.client.cache.SysVariables;


public class TalkView extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	public static JTextArea messageArea;
	
	public static JTextArea text;
	
	public static JPanel contentPane; 
	
	public static JScrollPane scrollPane;
	
	public static JButton send;
	
	private String incept;
	
	public TalkView(String name){
		this.incept = name;
		this.setTitle(name);
		this.setName(name);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		this.setLayout(null);
		
		JTextField n1 = new JTextField();
		n1.setBounds(20, 20, 400, 26);
		contentPane.add(n1);
		n1.setText("对话记录");
		n1.setEditable(false);
		/*****************************/
		messageArea = new JTextArea(5, 10);
		messageArea.setEditable(false);
		scrollPane=new JScrollPane();
        scrollPane.setBounds(20, 47, 400, 150);
        contentPane.add(scrollPane,BorderLayout.CENTER);
        scrollPane.setViewportView(messageArea);
        
        JTextField n2 = new JTextField();
		n2.setBounds(20, 198, 400, 26);
		contentPane.add(n2);
		n2.setText("对话框");
		n2.setEditable(false);
		/*****************************/
		text = new JTextArea(5, 10);
		text.setBounds(20, 225, 400, 150);
		contentPane.add(text);
		
		send = new JButton("Enter");
		send.setBounds(20, 376, 400, 24);
		contentPane.add(send);
		send.addActionListener(this);
		
		
		initJFrame();
	}
	
	
	private void initJFrame() {
		this.setLocation(500, 30);
		this.setSize(450, 450);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				SysVariables.session.remove(incept);
				super.windowClosed(e);
			}
			
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		String message = text.getText().trim();
		O2OMessage m = new O2OMessage();
		m.setCommandId((byte)0x0003);
		m.setMessage(message);
		short len = (short) (60 + message.getBytes().length);
		m.setMsglength(len);
		m.setSender(SysVariables.user);
		m.setIncept(incept);
		SysVariables.channel.write(m);
		messageArea.append(SysVariables.user + ": " + message + "\r\n");
		text.setText("");
	}

	
}
