package com.neu.client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScrollTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScrollTest t = new ScrollTest();
	}
	
	
	public ScrollTest(){
		JPanel panel = new JPanel();
		panel.setBounds(20, 50, 80, 80);
        panel.setLayout(new BorderLayout());
        JTextArea text = new JTextArea(5, 10);
        text.setBounds(20, 20, 80, 80);
        text.setEditable(true);
        text.setAutoscrolls(true);
        Dimension d = new Dimension(20, 20);
        text.setMinimumSize(d);
        
        JScrollPane pane = new JScrollPane(text);
        panel.add(pane,BorderLayout.CENTER);
        

        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);
        this.setBounds(100, 100, 250, 200);
        this.setVisible(true);
	}

}
