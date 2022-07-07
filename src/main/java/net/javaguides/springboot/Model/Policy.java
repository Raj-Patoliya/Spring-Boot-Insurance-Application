package net.javaguides.springboot.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "policy")
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long polId;
	
	@Column(name = "polName",nullable = false)
	private String polName;
	
	@Column(name="pCatId",nullable = false)
	private String pCatId;
	
	@Column(name="subCatId",nullable = false)
	private String subCatId;

	@Column(name="pol_desc",nullable = false)
	private String polDesc;

	@Lob
	private String image;
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getpCatId() {
		return pCatId;
	}

	public void setpCatId(String pCatId) {
		this.pCatId = pCatId;
	}

	public String getPolDesc() {
		return polDesc;
	}

	public void setPolDesc(String polDesc) {
		this.polDesc = polDesc;
	}

	@Column(name="assured",nullable = false)
	private String assured;
	
	@Column(name="premium",nullable = false)
	private String premium;
	
	@Column(name="tenure",nullable = false)
	private String tenure;
	
	@Column(name = "status")
	private String status;

	public long getPolId() {
		return polId;
	}

	public void setPolId(long polId) {
		this.polId = polId;
	}

	public String getPolName() {
		return polName;
	}

	public void setPolName(String polName) {
		this.polName = polName;
	}

	public String getPCatId() {
		return pCatId;
	}

	public void setPCatId(String pCatId) {
		this.pCatId = pCatId;
	}

	public String getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(String subCatId) {
		this.subCatId = subCatId;
	}

	public String getAssured() {
		return assured;
	}

	public void setAssured(String assured) {
		this.assured = assured;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
