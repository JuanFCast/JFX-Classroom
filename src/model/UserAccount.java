package model;

import java.time.LocalDate;

public class UserAccount {
	private String userName;
	private String password;
	private String profilePhoto;
	private String gender;
	private String career;
	private LocalDate birthday;
	private String browser;
	
	public UserAccount(String userName, String password, String profilePhoto, String gender, String career, LocalDate birthday,String browser) {
		 this.userName=userName;
		 this.password=password;
		 this.profilePhoto=profilePhoto;
		 this.gender=gender;
		 this.career=career;
		 this.birthday=birthday;
		 this.browser=browser;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}

}
