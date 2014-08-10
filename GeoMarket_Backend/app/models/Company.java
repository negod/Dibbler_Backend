/**
 * This is all the information for a company.
 * Each Company has geometrical point that specifies it´s spatial positioning
 * A Company can have a mother company
 */

package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "company")
public class Company extends Model {

	private static final long serialVersionUID = 7495897286691526264L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(40)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "IDNR")
	private String idnr;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(40)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(40)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "STREET")
	private String street;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(10)
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "STREETNR")
	private String streetnr;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(20)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "CITY")
	private String city;

	@Constraints.MaxLength(20)
	@Size(max = 20)
	@Column(name = "STATE")
	private String state;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(3)
	@NotNull
	@Size(min = 1, max = 3)
	@Column(name = "COUNTRY")
	private String country;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(10)
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "POSTALCODE")
	private String postalcode;

	@Constraints.Required
	@NotNull
	@Lob
	@Column(name = "LOCATION")
	private byte[] location;

	@Column(name = "PARENTCOMPANY")
	@OneToOne(cascade = CascadeType.ALL)
	private Company parentcompany;

	@Column(name = "FOLLOWER_REQ_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private FollowerReq followeRequest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdnr() {
		return idnr;
	}

	public void setIdnr(String idnr) {
		this.idnr = idnr;
	}

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

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public byte[] getLocation() {
		return location;
	}

	public void setLocation(byte[] location) {
		this.location = location;
	}

	public Company getParentcompany() {
		return parentcompany;
	}

	public void setParentcompany(Company parentcompany) {
		this.parentcompany = parentcompany;
	}

	public FollowerReq getFolloweRequest() {
		return followeRequest;
	}

	public void setFolloweRequest(FollowerReq followeRequest) {
		this.followeRequest = followeRequest;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Finder<Long, Company> find = new Finder<Long, Company>(
			Long.class, Company.class);

}
