package com.nagarro.providerService;

public interface ProviderService {
	
	public String addServiceToQueue(int userId , String serviceName , String address , String providerName);
	
	public String response(String userId , String status , String serviceName , String providerName);
	
	public void notifyProvider(String provider);

}
