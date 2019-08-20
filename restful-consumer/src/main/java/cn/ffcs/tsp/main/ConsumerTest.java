package cn.ffcs.tsp.main;

import java.io.IOException;

public class ConsumerTest {

	public static void main(String[] args) throws IOException {
//		ApplicationContext ds = new ClassPathXmlApplicationContext("classpath:dubbo-provider.xml");
//	    // 阻塞当前进程，否则程序会直接停止
//	    try {
//			System.in.read();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		ctx.start();
//        System.out.println(ctx);
//        //查询所有
//		ManualInfoProviderImpl ds = (ManualInfoProviderImpl) ctx.getBean("manualInfoProviderImpl",ManualInfoProviderImpl.class);
//        List<ManualInfo> list = ds.getManualInfoList();
//        System.out.println(list.size());
//        System.in.read();
        StartConsumerJetty.getInstance().startJetty();
	}
}
