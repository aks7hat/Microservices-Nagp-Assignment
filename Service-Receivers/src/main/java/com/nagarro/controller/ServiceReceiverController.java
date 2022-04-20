package com.nagarro.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nagarro.userService.UserService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping(value="/user")
public class ServiceReceiverController {
	
	@Autowired
    RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Resource
	UserService userService;

	
	@PostMapping("/requestService/{serviceName}")
	@HystrixCommand(fallbackMethod = "requestServiceFallback")
	public String requestService(@PathVariable String serviceName , @RequestParam String userName)
	{
		int id = userService.getUser(userName);
		String url = "/admin/serviceRequest/"+serviceName+"?userId=" + id ;
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("adminService", false);
		String res = restTemplate.postForObject(instance.getHomePageUrl() + url, null ,String.class);
		return res;
		
	}	
	
	@SuppressWarnings("unused")
	public String requestServiceFallback(@PathVariable String serviceName , @RequestParam String userName) {
		return "Hey " + userName + " Your request for the service " + serviceName + " can not be provided at this time!! Please try again later";
	}

}
