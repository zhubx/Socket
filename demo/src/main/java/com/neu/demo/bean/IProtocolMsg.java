package com.neu.demo.bean;

import java.nio.charset.Charset;

import com.neu.demo.util.GlobalConstant;

public interface IProtocolMsg {

	/**
     * UTF-8字符集,系统默认字符集
     */
    public static final Charset PROTOCOL_CHARSET = Charset.forName(GlobalConstant.CHARSET_UTF8);

    /**
     * GBK字符集
     */
    public static final Charset GBK_CHARSET = Charset.forName(GlobalConstant.CHARSET_GBK);
    
    /**
     * 消息长度 2字节
     */
    public static final short MSG_LEN = 2;

    /**
     * Command Id 长度1字节
     */
    public static final byte COMMAND_ID_LEN = 1;
    
    
    
}
