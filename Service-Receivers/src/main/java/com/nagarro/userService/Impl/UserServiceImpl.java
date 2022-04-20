package com.nagarro.userService.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.nagarro.userService.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	Map<String, Integer> nameIdMap = new HashMap<String, Integer>();

	public UserServiceImpl() {
		nameIdMap = new HashMap<String, Integer>();	
		nameIdMap.put("akshat",1);
		nameIdMap.put("aggarwal",2);
		nameIdMap.put("jane",3);
		nameIdMap.put("doe",4);
		nameIdMap.put("example",5);
		
	}
	
	@Override
	public int getUser(String name) {
		// TODO Auto-generated method stub
		int id = nameIdMap.get(name);
		return id;		
	}

	@Override
	@JmsListener(destination="serviceRequestAccepted")
	public void getNotificationFromProvider(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
	
	

}
