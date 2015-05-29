package com.qkhl.util;

import java.util.UUID;

public class CreateInfo {

	public String upkey(String password,String phonenum,String createtime){
		String uk= password+phonenum+createtime;
		String nkey=MD5Utils.stringToMD5(uk);
		return nkey;
		
	}
	public String uuid(){
		UUID uuid = UUID.randomUUID();
		String uu = uuid.toString();
		return uu;
		
	}
	public String token(){
		return null;
		
	}
}
