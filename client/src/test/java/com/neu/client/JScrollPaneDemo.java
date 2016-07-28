package com.neu.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.neu.client.cache.SysVariables;

public class JScrollPaneDemo extends JFrame{
    private JPanel contentPane; 
    private JScrollPane scrollPane;
    private JTextArea textArea;
    public JScrollPaneDemo(){
    	
    	this.setLayout(null);
    	JTextField	onlinNum = new JTextField();
		onlinNum.setBounds(20, 20, 100, 26);
		this.add(onlinNum);
		onlinNum.setText("在线用户：");
		onlinNum.setEditable(false);
		onlinNum.setVisible(true);
		
		
         contentPane=new JPanel();
//         contentPane.setBounds(2, 2, 230, 170);
         contentPane.setBorder(new EmptyBorder(5,5,5,5));
         contentPane.setLayout(null);
         this.setContentPane(contentPane);
         contentPane.add(onlinNum,BorderLayout.LINE_START);
         scrollPane=new JScrollPane();
         scrollPane.setBounds(3, 50, 230, 170);
         contentPane.add(scrollPane,BorderLayout.CENTER);
         textArea=new JTextArea();
         scrollPane.setViewportView(textArea);
         this.setTitle("滚动面板使用");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setBounds(100, 100, 250, 200);
         this.setVisible(true);
     }
    public static void main(String []args){
        JScrollPaneDemo example=new JScrollPaneDemo(); 
    }
}