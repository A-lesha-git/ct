package ru.ct.objects;

public class NavBarPage {
	private String name;
	private String hrefParams;
	private int timeout;
	private boolean isForbided;
	private boolean isFilterPage;
	
	public String getName() {
		return name;
	}
	public NavBarPage setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getHrefParams() {
		return hrefParams;
	}
	public NavBarPage setHrefParams(String hrefParams) {
		this.hrefParams = hrefParams;
		return this;
	}
	public int getTimeout() {
		return timeout;
	}
	public NavBarPage setTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}
	
	public boolean getIsForbided() {
		return isForbided;
	}
	public NavBarPage setIsForbided(boolean isForbided) {
		this.isForbided = isForbided;
		return this;
	}
	
	public boolean getIsFilterPage() {
		return isFilterPage;
	}
	public NavBarPage setIsFilterPage(boolean isFilterPage) {
		this.isFilterPage = isFilterPage;
		return this;
	}
	@Override
	public String toString() {
		return "NavBarPage [name=" + name + ", hrefParams=" + hrefParams + ", timeout=" + timeout + ", isForbided="
				+ isForbided + ", isFilterPage=" + isFilterPage + "]";
	}
	
}
