package com.nagarro.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.providerService.ProviderService;

@RestController
@RequestMapping(value="/serviceProvider")
public class ServiceProviderController {
	
	@Resource
	ProviderService providerService;
	
	@PostMapping("/request/{serviceName}/{address}/{provider}")
	public String provideService(@RequestParam int userId ,
			@PathVariable String serviceName , @PathVariable String address,
			@PathVariable String provider)
	{
		String res = providerService.addServiceToQueue(userId, serviceName, address, provider);
		return "Your service request of " + serviceName + " with userId id " + userId + " on address " + address + " is received and " + provider + " has been notified";
		
	}

	
	@PostMapping("/responseToRequest")
	public String respondToRequest(@RequestParam(name="status") String status ,@RequestParam(name="userId") String userId,
								   @RequestParam(name="serviceName") String serviceName , @RequestParam(name="providerName") String providerName)
	{
		String res = providerService.response(userId, status, serviceName , providerName);
		return res;
	}
}
