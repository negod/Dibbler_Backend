/**
 * This is a relation between a Company and User.
 * Every User/Role combination kan attach one or more companies.
 * One UserRole is restricted to a specific company.
 * One Person with different roles can have one company per role
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "company_user")
public class CompanyUsers extends Model {

	private static final long serialVersionUID = -4313563197369460798L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@Column(name = "USERROLE_ID")
	private int userroleId;

	@Constraints.Required
	@Column(name = "COMPANY_ID")
	private int companyId;

	@Constraints.MaxLength(20)
	@Column(name = "FOLLOWER_REQ")
	private String followerReq;
	
	
	public static Finder<Long, CompanyUsers> find = new Finder<Long, CompanyUsers>(
			Long.class, CompanyUsers.class);

}
