package models;

import play.db.ebean.Model;

public class CompanyUser extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "USERROLE_ID")
	private int userroleId;
	@Basic(optional = false)
	@NotNull
	@Column(name = "COMPANY_ID")
	private int companyId;
	@Size(max = 20)
	@Column(name = "FOLLOWER_REQ")
	private String followerReq;

}
