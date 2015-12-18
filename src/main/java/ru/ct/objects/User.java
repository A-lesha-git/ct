package ru.ct.objects;

import java.util.Arrays;

import ru.ct.objects.User;

public class User {
	private int idAccount;
	private String name; 
	private String phoneMob;
	private String email;
	private String site;
	private String loginName;
	private String psw;
	private String type;
	private String[] sites;
	private String startDate;
	private String endDate;
 	
	public int getIdAccount() {
		return idAccount;
	}
	public User setIdAccount(int idAccount) {
		this.idAccount = idAccount;
		return this;
	}
		
	public String getStartDate() {
		return startDate;
	}
	public User setStartDate(String startDate) {
		this.startDate = startDate;
		return this;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public User setEndDate(String endDate) {
		this.endDate = endDate;
		return this;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public User setLoginName(String loginName) {
		this.loginName = loginName;
		return this;
	}
	
	public String getPSW() {
		return psw;
	}
	@Override
	public String toString() {
		return "User [idAccount=" + idAccount + ", name=" + name + ", phoneMob=" + phoneMob + ", email=" + email
				+ ", site=" + site + ", loginName=" + loginName + ", psw=" + psw + ", type=" + type + ", sites="
				+ Arrays.toString(sites) + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	public User setPSW(String psw) {
		this.psw = psw;
		return this;
	}
	
	public String getName() {
		return name;
	}
	public User setName(String name) {
		this.name = name;
		return this;
	}
	
	
	public String getEmail() {
		return email;
	}
	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getSite() {
		return site;
	}
	public User setSite(String site) {
		this.site = site;
		return this;
	}
	
	public String getPhoneMob() {
		return phoneMob;
	}

	public User setPhoneMob(String phoneMob) {
		this.phoneMob = phoneMob;
		return this;
	}

	public String getType() {
		return type;
	}

	public User setType(String type) {
		// type передает в строке число, в зависимости от числа возвращается тип аккаунта
		int typeNum = Integer.parseInt(type);
		switch (typeNum) {
			case 1:
				type="admin";
				break;
			case 2:
				type="agent";
				break;
			case 3:
				type="organization";	
				break;
	
			default:
				break;
		}
		this.type = type;
		return this;
	}
	
	public String[] getSites() {
		return sites;
	}
	
	public User setSites(String[] sites) {
		this.sites = sites;
		return this;
	}

}