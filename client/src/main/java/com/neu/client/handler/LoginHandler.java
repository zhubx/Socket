package com.neu.client.handler;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.HeartBeat;
import com.neu.client.bean.LoginResponse;
import com.neu.client.cache.SysVariables;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginHandler extends ChannelHandlerAdapter implements IHandler{

	private static final Logger LOG = LoggerFactory.getLogger(LoginHandler.class);
	
	private String username;
	
	private String password;
	
	public LoginHandler(){}
	
	public LoginHandler(String username,String password){
		this.username = username;
		this.password = password;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//当连接成功后会调用此方法
					ByteBuf message = null;
					message = Unpooled.buffer(94);
					String userpassword = this.username + " " + this.password;
					message.writeByte(0x0001);
					short s = (short) userpassword.getBytes().length;
					message.writeShort(s);
					message.writeBytes(userpassword.getBytes());
					ctx.writeAndFlush(message);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	public void doHandler(ChannelHandlerContext ctx, Object msg) {
		// TODO Auto-generated method stub
		LoginResponse b = (LoginResponse) msg;
		System.out.println(b);
		if(b.getResult() == 0){
			SysVariables.channel = ctx.channel();
			//设置用户缓存
			SysVariables.response = b;
			ctx.executor().scheduleAtFixedRate(new HearBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
		}else if(b.getResult() ==1){
			SysVariables.loginResult = 1;
			LOG.info("[用户名或密码错误]");
		}else{
			SysVariables.loginResult = 2;
			LOG.error("[未知错误]");
		}
	}
	
	private class HearBeatTask implements Runnable{

		private final ChannelHandlerContext ctx;
		
		public HearBeatTask(final ChannelHandlerContext ctx){
			LOG.info("心跳线程启动...");
			this.ctx = ctx;
		}
		public void run() {
			// TODO Auto-generated method stub
			HeartBeat heart = new HeartBeat();
			heart.setCommandId((byte)0x0008);
			heart.setMsglength((short)0);
			ctx.channel().writeAndFlush(heart);
		}
		
	}
	

	
}
