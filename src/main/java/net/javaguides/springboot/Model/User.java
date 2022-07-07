package net.javaguides.springboot.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "user")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	
	@Column(name ="name",nullable = false)
	private String name;
	
	@Column(name ="email",nullable = false)
	private String email;
	
	@Column(name ="mobile",nullable = false)
	private String mobile;
	
	@Column(name ="password",nullable = false)
	private String password;
	
	@Column(name ="age",nullable = false)
	private String age;
	
	@Column(name ="gender",nullable = false)
	private String gender;
	
	@Column(name ="content_type",nullable = true)
	private String content_type;
	
	@Lob
	private String photo;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name ="dateOfBirth",nullable = false)
	private String dateOfBirth;
	
	@Column(name ="status",nullable = false)
	private String status;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
