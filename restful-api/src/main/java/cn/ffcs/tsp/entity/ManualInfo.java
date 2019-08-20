package cn.ffcs.tsp.entity;

import java.io.Serializable;
import java.util.Date;

public class ManualInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id ;
	private String manualName ;
	private String manualDesc ;
	private String dowloadUrl ;
	private String autoEnterpriseCode ;
	private Date updateDate ;
	private String manualType ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getManualName() {
		return manualName;
	}
	public void setManualName(String manualName) {
		this.manualName = manualName;
	}
	public String getManualDesc() {
		return manualDesc;
	}
	public void setManualDesc(String manualDesc) {
		this.manualDesc = manualDesc;
	}
	public String getDowloadUrl() {
		return dowloadUrl;
	}
	public void setDowloadUrl(String dowloadUrl) {
		this.dowloadUrl = dowloadUrl;
	}
	public String getAutoEnterpriseCode() {
		return autoEnterpriseCode;
	}
	public void setAutoEnterpriseCode(String autoEnterpriseCode) {
		this.autoEnterpriseCode = autoEnterpriseCode;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getManualType() {
		return manualType;
	}
	public void setManualType(String manualType) {
		this.manualType = manualType;
	}
	
}
