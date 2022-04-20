package com.nagarro.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nagarro.service.AdminService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;	
	
	@Resource
	AdminService adminService;
	
	@PostMapping("/serviceRequest/{serviceName}")
	@HystrixCommand(fallbackMethod = "respondToServiceFallback")
	public String respondToService(@RequestParam int userId , @PathVariable String serviceName) {
		String address = adminService.getUserAddress(userId);
		boolean isPresent = adminService.isServicePresent(serviceName);
		boolean isAvailable = adminService.isServiceAvailable(serviceName , address);
		String providerDetail = adminService.getProvidersDetail(address);
		if(isPresent && isAvailable && providerDetail != null) {
			String url = "/serviceProvider/request/"+ serviceName + "/" + address + "/" + providerDetail + "?userId=" +userId;
			InstanceInfo instance = eurekaClient.getNextServerFromEureka("serviceProviders", false);
			String res = restTemplate.postForObject(instance.getHomePageUrl() + url,null, String.class);
			return res;
		}
		else {
			return "Your Requested service cannot be provided!! Please try again with something else.";
		}
			
	}
	
	public String respondToServiceFallback(@RequestParam int userId , @PathVariable String serviceName) {
		return "No provider is free at the moment!! Try again later";
	}


}
