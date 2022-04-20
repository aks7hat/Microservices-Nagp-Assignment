package com.nagarro.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.nagarro.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired 
	private JmsTemplate jmsTemplate;
	
	ArrayList<String> totalServices = new ArrayList<>();
	
	Map<String, List<String>> servicePerAddress = new HashMap<String, List<String>>();
	
	Map<Integer, String> addressMap = new HashMap<Integer , String>();
	
	Map<String, List<String>> addressPerProvider = new HashMap<String, List<String>>();
	
	public AdminServiceImpl()
	{
		/**
		 * List of Total Services Present
		 */
		totalServices = new ArrayList<String>();
		totalServices.add("electricians");
		totalServices.add("yoga trainers");
		totalServices.add("interior designers");
		totalServices.add("salon");
		totalServices.add("cleaners");
		totalServices.add("plumbers");
		
		
		/**
		 * Services present as per the address list
		 */
		servicePerAddress = new HashMap<String, List<String>>();
		List<String> service = new ArrayList<String>();
		service.add("electricians");
		service.add("yoga trainers");
		service.add("salon");
		
		servicePerAddress.put("address1", service);
		
		
		service = new ArrayList<String>();
		service.add("interior designers");
		service.add("salon");
		service.add("electricians");
		
		servicePerAddress.put("address2", service);
		
		
		service = new ArrayList<String>();
		service.add("cleaners");
		service.add("salon");
		service.add("electricians");
		
		servicePerAddress.put("address3", service);
		
		service = new ArrayList<String>();
		service.add("cleaners");
		service.add("salon");
		service.add("electricians");
		
		servicePerAddress.put("address4", service);
		
		service = new ArrayList<String>();
		service.add("interior designers");
		service.add("salon");
		service.add("electricians");
		
		servicePerAddress.put("address5", service);
		
		/**
		 * Address of the users per id list
		 */
		addressMap = new HashMap<Integer , String>();
		addressMap.put(1, "address1");
		addressMap.put(2, "address2");
		addressMap.put(3, "address3");
		addressMap.put(4, "address4");
		addressMap.put(5, "address5");		
		/**
		 * List of providers according to address
		 */
		addressPerProvider = new HashMap<String, List<String>>();
		List<String> providers = new ArrayList<String>();
		providers.add("Mahesh");
		providers.add("Suresh");
		providers.add("Rajesh");
		
		addressPerProvider.put("address1", providers);
		
		providers = new ArrayList<String>();
		providers.add("Raman");
		providers.add("Rohit");	
		providers.add("Umesh");
		
		addressPerProvider.put("address2", providers);	
		
		providers = new ArrayList<String>();
		providers.add("Rishabh");
		providers.add("Shreyas");
		providers.add("Ramesh");
		
		addressPerProvider.put("address3", providers);
		
		providers = new ArrayList<String>();
		providers.add("Rishabh");
		providers.add("Shreyas");
		providers.add("Ramesh");
		
		addressPerProvider.put("address4", providers);
		
		providers = new ArrayList<String>();
		providers.add("Raman");
		providers.add("Rohit");	
		providers.add("Umesh");
		
		addressPerProvider.put("address5", providers);
		
	}

	@Override
	public String getUserAddress(int id) {
		// TODO Auto-generated method stub
		String address = addressMap.get(id);
		return address;
	}

	@Override
	public boolean isServicePresent(String serviceName) {
		// TODO Auto-generated method stub
		return (StringUtils.isNotEmpty(serviceName) && totalServices.contains(serviceName)) ? true : false;		
	}

	@Override
	public boolean isServiceAvailable(String serviceName , String address) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotEmpty(serviceName)) {
			List<String> servicesAvailable = servicePerAddress.get(address);
			return (Objects.nonNull(servicesAvailable) && servicesAvailable.contains(serviceName)) ? true : false;
		}
		return false;
	}

	@Override
	public String getProvidersDetail(String address) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotEmpty(address)) {
			List<String> providerDetails = addressPerProvider.get(address);
			Random rand = new Random(); 			
			return StringUtils.isNotEmpty(providerDetails.get(0)) ? providerDetails.get(rand.nextInt(providerDetails.size())) : null;
		}
		else {
			return null;
		}
	}
	
}
