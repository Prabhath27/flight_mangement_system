package com.flightmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Admin")
public class Admin extends User {
	@NotBlank(message ="Username must not be blank")
	private String AdminName;
	@NotNull(message = "Age must not be null")
	@Min(value = 1,message = "Age should be minimum 1")
	@Max(value=100,message = "Age should be less than human age")
	private Integer age;
	@NotNull(message = "Admin UIN must not be null")
	private Long adminUIN;
	public Admin() {}
	public Admin(String adminName,Integer age,Long adminUIN) {
		super();
		AdminName = adminName;
		this.age = age;
		this.adminUIN = adminUIN;
	}
	public String getAdminName() {
		return AdminName;
	}
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Long getAdminUIN() {
		return adminUIN;
	}
	public void setAdminUIN(Long adminUIN) {
		this.adminUIN = adminUIN;
	}
	@Override
	public String toString() {
		return "Admin [AdminName=" + AdminName + ", age=" + age + ", AdminUIN=" + adminUIN + "]";
	}
	
	

}

