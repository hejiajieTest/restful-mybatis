package cn.ffcs.tsp.dubboproviderImpl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;

import cn.ffcs.tsp.entity.ManualInfo;
import cn.ffcs.tsp.provider.IManualInfoProvider;
import cn.ffcs.tsp.service.IManualInfoService;

@Service
public class ManualInfoProviderImpl implements IManualInfoProvider{

	@Resource
	private IManualInfoService manualInfoService;
	@Override
	public List<ManualInfo> getManualInfoList() {
		List<ManualInfo> list = manualInfoService.getManualInfoList() ;
		return list;
	}

}
