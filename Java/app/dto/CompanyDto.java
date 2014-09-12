package dto;

public class CompanyDto {
	
	private static final long serialVersionUID = -6426827362089475472L;
	
	private String companyName;
	private String street;
	private String streetnr;
	private String city;
	private String state;
	private String country;
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetnr() {
		return streetnr;
	}
	public void setStreetnr(String streetnr) {
		this.streetnr = streetnr;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
