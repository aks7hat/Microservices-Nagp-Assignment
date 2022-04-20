package com.nagarro.service;

public interface AdminService {
	
	public String getUserAddress(int id);

	public boolean isServicePresent(String serviceName);
	
	public boolean isServiceAvailable(String serviceName , String address);
	
	public String getProvidersDetail(String address);
}
