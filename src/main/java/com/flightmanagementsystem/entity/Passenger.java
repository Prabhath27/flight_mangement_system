package com.flightmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="passenger")
public class Passenger extends User
{
	
	
	@NotBlank(message = "Passenger name must not be null")
	private String passengerName;
	@NotNull(message = "Age must not be null")
	@Min(value = 1,message = "Age should be minimum 1")
	@Max(value=100,message = "Age should be less than human age")
	private Integer age;
	@NotNull(message = "Passenger UIN must not be null")
	private Long passengerUIN;
	
	public Passenger() {}
	public Passenger(String passengerName, Integer age, Long passengerUIN) {
		super();
		this.passengerName = passengerName;
		this.age = age;
		this.passengerUIN = passengerUIN;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Long getPassengerUIN() {
		return passengerUIN;
	}
	public void setPassengerUIN(Long passengerUIN) {
		this.passengerUIN = passengerUIN;
	}
	@Override
	public String toString() {
		return "Passenger [passengerName=" + passengerName + ", age=" + age + ", passengerUIN=" + passengerUIN + "]";
	}
	
	

}
