package net.javaguides.springboot.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="sub_category")
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long subCatId;
	@Column(name="pCatId",nullable = false)
	private String pCatId;
	@Column(name="subCatName",nullable = false)
	private String subCatName;
	@Column(name="subCatDesc",nullable = false)
	private String subCatDesc;
	@Lob
	private String image;
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSubCatDesc() {
		return subCatDesc;
	}

	public void setSubCatDesc(String subCatDesc) {
		this.subCatDesc = subCatDesc;
	}

	public long getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(long subCatId) {
		this.subCatId = subCatId;
	}

	public String getpCatId() {
		return pCatId;
	}
	public void setpCatId(String pCatId) {
		this.pCatId = pCatId;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
}
