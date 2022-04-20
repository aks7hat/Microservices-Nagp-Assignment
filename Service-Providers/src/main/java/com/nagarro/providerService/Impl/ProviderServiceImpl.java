package com.nagarro.providerService.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.nagarro.entity.ProvidersModel;
import com.nagarro.providerService.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService{
	
	@Autowired 
	private JmsTemplate jmsTemplate;
	
	List<ProvidersModel> providerList = new ArrayList<>();

	@Override
	public String response(String userId, String status, String serviceName, String providerName) {
		// TODO Auto-generated method stub
		if(status.equalsIgnoreCase("accepted")) {
			String message = "Your Request with userId " + userId + " for the service " + serviceName + " has been " + status + " by " + providerName;
			jmsTemplate.convertAndSend("serviceRequestAccepted", message);
			return "User Notified";
		}
		else {
			System.out.println("Your Service Request has been declined");
			return "Service Declined";
		}
	}

	@Override
	public String addServiceToQueue(int userId, String serviceName, String address, String providerName) {
		// TODO Auto-generated method stub
		ProvidersModel provider = new ProvidersModel();
		provider.setUserId(userId);
		provider.setServiceName(serviceName);
		provider.setAddress(address);
		provider.setProvider(providerName);
		
		providerList.add(provider);
		
		notifyProvider(providerName);
		return "Service Added to the providers Queue!! He will respond to it shortly";
	}

	@Override
	public void notifyProvider(String provider) {
		// TODO Auto-generated method stub
		jmsTemplate.convertAndSend("sendNotificationToProvider", provider);
	}



	
	
	

}
