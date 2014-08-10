package models;

import play.db.ebean.Model;

@Table(name = "company")
public class Company extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "IDNR")
	private String idnr;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "COMPANY_NAME")
	private String companyName;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "STREET")
	private String street;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "STREETNR")
	private String streetnr;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "CITY")
	private String city;
	@Size(max = 20)
	@Column(name = "STATE")
	private String state;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 3)
	@Column(name = "COUNTRY")
	private String country;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "POSTALCODE")
	private String postalcode;
	@Basic(optional = false)
	@NotNull
	@Lob
	@Column(name = "LOCATION")
	private Object location;
	@Column(name = "PARENTCOMPANY")
	private Integer parentcompany;
	@Column(name = "FOLLOWER_REQ_ID")
	private Integer followerReqId;

}
