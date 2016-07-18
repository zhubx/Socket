package com.neu.demo;

public class SplitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aa:bb:cc:dd:";
		String[] str = s.split(":", 3);
		for(int i=0;i<str.length;i++){
			System.out.println(str[i]);
		}
	}

}
