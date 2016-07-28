package com.neu.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextAreaScroll extends JFrame{
 
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextAreaScroll s = new TextAreaScroll();
	}
	
	public TextAreaScroll(){
		JTextArea txaDisplay = new JTextArea(); 
		txaDisplay.setBounds(20, 20, 150, 150);
		JScrollPane scroll = new JScrollPane(txaDisplay); 
		JPanel contentPane = new JPanel();
//		把定义的JTextArea放到JScrollPane里面去 
		contentPane.add(scroll,BorderLayout.CENTER);
		this.setContentPane(contentPane);
		
		//分别设置水平和垂直滚动条自动出现 
		scroll.setHorizontalScrollBarPolicy( 
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		
		//分别设置水平和垂直滚动条总是出现 
//		scroll.setHorizontalScrollBarPolicy( 
//		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
//		scroll.setVerticalScrollBarPolicy( 
//		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 

		//分别设置水平和垂直滚动条总是隐藏
//		scroll.setHorizontalScrollBarPolicy( 
//		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
//		scroll.setVerticalScrollBarPolicy( 
//		JScrollPane.VERTICAL_SCROLLBAR_NEVER); 
		
		
		this.setTitle("滚动面板使用");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 250, 200);
        this.setVisible(true);
		
	}

}
