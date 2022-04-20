package com.nagarro.service.Impl;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.nagarro.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Override
	@JmsListener(destination="sendNotificationToProvider")
	public void notifyProvider(String provider) {
		// TODO Auto-generated method stub
		System.out.println("The Provider with name " + provider + " has been notified");		
	}

}
