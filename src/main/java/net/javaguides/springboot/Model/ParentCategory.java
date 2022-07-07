package net.javaguides.springboot.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parent_category")
public class ParentCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pId;
	@Column(name = "catName",nullable = false)
	private String catName;
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
		this.pId = pId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	 
}
