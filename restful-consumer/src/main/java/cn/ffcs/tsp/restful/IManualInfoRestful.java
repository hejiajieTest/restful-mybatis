package cn.ffcs.tsp.restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cn.ffcs.tsp.entity.ManualInfo;

@Path(value = "/manualInfo")
public interface IManualInfoRestful {

	@GET
	@Path("/rest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getManualInfoList();
}
