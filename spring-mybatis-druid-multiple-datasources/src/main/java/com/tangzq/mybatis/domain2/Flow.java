package com.tangzq.mybatis.domain2;

import java.util.Date;

/**
 * @author zhuc
 * @create 2013-3-11 下午3:42:19
 */
public class Flow {

	private String id;

	/**
	 * 投保主键ID(UUID)
	 */
	private String insuranceId;

	/**
	 * 节点代码
	 */
	private Integer codeId;

	/**
	 * 操作时间
	 */
	private Date operatingTime;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the insuranceId
	 */
	public String getInsuranceId() {
		return insuranceId;
	}

	/**
	 * @return the codeId
	 */
	public Integer getCodeId() {
		return codeId;
	}

	/**
	 * @return the operatingTime
	 */
	public Date getOperatingTime() {
		return operatingTime;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param insuranceId the insuranceId to set
	 */
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	/**
	 * @param codeId the codeId to set
	 */
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	/**
	 * @param operatingTime the operatingTime to set
	 */
	public void setOperatingTime(Date operatingTime) {
		this.operatingTime = operatingTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Flow [id=" + id + ", insuranceId=" + insuranceId + ", codeId=" + codeId + ", operatingTime="
				+ operatingTime + "]";
	}

}
