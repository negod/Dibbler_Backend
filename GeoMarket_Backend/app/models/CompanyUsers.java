/**
 * This is a relation between a Company and User.
 * Every User/Role combination kan attach one or more companies.
 * One UserRole is restricted to a specific company.
 * One Person with different roles can have one company per role
 */
package models;

import play.db.ebean.Model;

public class CompanyUsers extends Model {

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
