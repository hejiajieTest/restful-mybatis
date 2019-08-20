package cn.ffcs.tsp.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import cn.ffcs.tsp.util.PropertyUtil;

public class StartJetty {

	private static StartJetty st = new StartJetty();
	
	private static boolean status = false;
	
	private StartJetty(){}
	
	static{
	}
	
	public static StartJetty getInstance(){
		return st ;
	}
	
	public static boolean getStatus(){
		return status ;
	}
	
	public void startJetty(){
		    Server server = new Server(2308);
	        WebAppContext webapp = new WebAppContext();
	        webapp.setContextPath(PropertyUtil.getProp().getProperty("server.name"));
	        webapp.setResourceBase(PropertyUtil.getProp().getProperty("server.resource"));
	        webapp.setDescriptor(PropertyUtil.getProp().getProperty("server.descriptor"));
	        webapp.setParentLoaderPriority(true);
	        webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
	        server.setHandler(webapp);
	        try {
	            System.out.println("===========================================================");
	            server.start();
	            System.out.println("===========================================================");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
