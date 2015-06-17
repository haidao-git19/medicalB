package com.netbull.shop.common.datacache;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LaunchData {

	/*private static Map<String,LaunchOrgan> launchOrgan = null; 
	private static Map<String,LaunchSys> launchSys = null;
	private static Map<String,LaunchAddr> launchAddr =  null;
	
	private static LaunchData instance = new LaunchData();
	private LaunchData(){
		readLaunchData();
	}
	
	public static LaunchData getInstance(){
		return instance;
	}
	
	private void readLaunchData(){
		launchOrgan = new HashMap<String,LaunchOrgan>(); 
		launchSys = new HashMap<String,LaunchSys>();
		launchAddr = new HashMap<String,LaunchAddr>();
		
		SecurityDao securityDao = (SecurityDao)SpringContextUtil.getWac().getBean("securityDao");
		List<LaunchOrgan> organList = securityDao.selectLaunchOrgan();
		for(LaunchOrgan organ : organList){
			launchOrgan.put(organ.getOrganId(), organ);
		}
		List<LaunchSys> sysList = securityDao.selectLaunchSys();
		for(LaunchSys sys : sysList){
			sys.setOrgan(launchOrgan.get(sys.getOrganId()));
			launchSys.put(sys.getSysId(), sys);
		}

		List<LaunchAddr> addrList = securityDao.selectLaunchAddr();
		for(LaunchAddr addr : addrList){
			addr.setOrgan(launchOrgan.get(addr.getOrganAddrId()));
		}
		
	}
	
	public Map<String,LaunchOrgan> getLaunchOrgan(){
		return launchOrgan;
	}
	
	public Map<String,LaunchSys> getLaunchSys(){
		return launchSys;
	}
	
	public Map<String,LaunchAddr> getLaunchAddr(){
		return launchAddr;
	}*/
}
