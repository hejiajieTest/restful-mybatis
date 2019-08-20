package cn.ffcs.tsp.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ffcs.tsp.provider.IManualInfoProvider;

public class Providers {

	private static Providers p =new Providers() ; 
	private static ApplicationContext ac = null ;
	
	private Providers(){
		
	}
	static{
		ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml") ;
	}
	
	public static Providers getInstance(){
		return p;
	}
	
	public IManualInfoProvider getManualInfo(){
		IManualInfoProvider manualInfoProvider = ac.getBean("manualInfoProvider",IManualInfoProvider.class);
		return manualInfoProvider;
	}
}
