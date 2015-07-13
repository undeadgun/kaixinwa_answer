package com.qkhl.util;

import java.util.UUID;


public class CreateInfo {

	public String upkey(String phonenum,String password){
		StringBuilder strb=new StringBuilder();
		strb.append(phonenum);strb.append(password);
		String nkey=MD5Utils.stringToMD5(strb.toString());
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
