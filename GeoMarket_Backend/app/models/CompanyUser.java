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
@Table(name = "company_user")
public class CompanyUser extends Model {

	private static final long serialVersionUID = -4313563197369460798L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Role role;

	@Constraints.Required
	@NotNull
	@Column(name = "COMPANY_ID")
	private int companyId;

	@Constraints.MaxLength(20)
	@Size(max = 20)
	@Column(name = "FOLLOWER_REQ")
	private String followerReq;
	
	
	public static Finder<Long, CompanyUser> find = new Finder<Long, CompanyUser>(
			Long.class, CompanyUser.class);

}
