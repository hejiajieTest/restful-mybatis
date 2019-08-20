package cn.ffcs.tsp.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.tsp.entity.ManualInfo;
import cn.ffcs.tsp.mapper.IManualInfoMapper;
import cn.ffcs.tsp.service.IManualInfoService;

@Service
public class ManualInfoServiceImpl implements IManualInfoService{

	@Resource
	private IManualInfoMapper manualMapper ;
	public List<ManualInfo> getManualInfoList() {
		List<ManualInfo> list = manualMapper.findAll();
		return list;
	}

}
