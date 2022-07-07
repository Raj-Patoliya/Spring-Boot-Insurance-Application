package net.javaguides.springboot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "applied_policy")
public class AppliedPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long appliedId;
	private long userId;
	private long polId;
	private String subCatId;
	private String pCatId;
	private String polName;
	private String paymentStatus;
	private String appliedStatus;
	private String applyDate;
	private String userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getAppliedId() {
		return appliedId;
	}
	public void setAppliedId(long appliedId) {
		this.appliedId = appliedId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPolId() {
		return polId;
	}
	public void setPolId(long polId) {
		this.polId = polId;
	}
	public String getSubCatId() {
		return subCatId;
	}
	public void setSubCatId(String subCatId) {
		this.subCatId = subCatId;
	}
	public String getpCatId() {
		return pCatId;
	}
	public void setpCatId(String pCatId) {
		this.pCatId = pCatId;
	}
	public String getPolName() {
		return polName;
	}
	public void setPolName(String polName) {
		this.polName = polName;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getAppliedStatus() {
		return appliedStatus;
	}
	public void setAppliedStatus(String appliedStatus) {
		this.appliedStatus = appliedStatus;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
}
