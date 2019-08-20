package cn.ffcs.tsp.main;

import java.net.InetAddress;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import cn.ffcs.tsp.util.PropertyUtil;

public class StartConsumerJetty {

	private static StartConsumerJetty st = new StartConsumerJetty();
	
	private static boolean status = false;
	
	private StartConsumerJetty(){}
	
	static{
	}
	
	public static StartConsumerJetty getInstance(){
		return st ;
	}
	
	public static boolean getStatus(){
		return status ;
	}
	
	public void startJetty(){
		    Server server = new Server(2302);
	        WebAppContext webapp = new WebAppContext();
	        webapp.setContextPath(PropertyUtil.getProp().getProperty("server.name"));
	        webapp.setResourceBase(PropertyUtil.getProp().getProperty("server.resource"));
	        webapp.setDescriptor(PropertyUtil.getProp().getProperty("server.descriptor"));
	        webapp.setParentLoaderPriority(true);
	        webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
	        server.setHandler(webapp);
	        try {
	            System.out.println("===========================================================");
	            System.out.println(InetAddress.getLocalHost().getHostAddress());
	            server.start();
	            System.out.println("===========================================================");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
