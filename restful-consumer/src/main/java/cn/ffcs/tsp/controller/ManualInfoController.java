package cn.ffcs.tsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;

import cn.ffcs.tsp.entity.ManualInfo;
import cn.ffcs.tsp.provider.IManualInfoProvider;
import cn.ffcs.tsp.restful.IManualInfoRestful;

@Controller
public class ManualInfoController implements IManualInfoRestful{

	@Autowired
	private ApplicationContext ac;
	@Reference
	private IManualInfoProvider manualInfoProvider ;
	
	
	@Override
	public String getManualInfoList() {
		System.out.println("@@@#####chenggong");
		List<ManualInfo> list = manualInfoProvider.getManualInfoList() ;
		String result = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss") ;
		return result;
	}

}
