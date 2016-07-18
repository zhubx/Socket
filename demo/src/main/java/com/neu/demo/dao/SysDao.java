package com.neu.demo.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.neu.demo.bean.LoginMessage;

@SuppressWarnings("deprecation")
public class SysDao extends SqlMapClientDaoSupport{

	public LoginMessage getLoginMessage(LoginMessage msg){
		return (LoginMessage)this.getSqlMapClientTemplate().queryForList("", "");
	}
	
}
