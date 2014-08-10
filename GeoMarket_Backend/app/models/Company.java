/**
 * This is all the information for a company.
 * Each Company has geometrical point that specifies it´s spatial positioning
 * A Company can have a mother company
 */

package models;

import play.db.ebean.Model;

@Table(name = "company")
public class Company extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "IDNR")
	private String idnr;
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "COMPANY_NAME")
	private String companyName;
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "STREET")
	private String street;
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "STREETNR")
	private String streetnr;
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "CITY")
	private String city;
	@Size(max = 20)
	@Column(name = "STATE")
	private String state;
	@NotNull
	@Size(min = 1, max = 3)
	@Column(name = "COUNTRY")
	private String country;
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "POSTALCODE")
	private String postalcode;
	@NotNull
	@Lob
	@Column(name = "LOCATION")
	private Object location;
	@Column(name = "PARENTCOMPANY")
	private Integer parentcompany;
	@Column(name = "FOLLOWER_REQ_ID")
	private Integer followerReqId;

}
