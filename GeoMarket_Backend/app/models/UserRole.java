/**
 * Relation table between User and Role
 * One user can have several roles and one role can have several users
 */

package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
@Table(name = "userrole")
public class UserRole extends Model {

	private static final long serialVersionUID = -6300638857870300792L;

	@Id
	@Constraints.Required
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@Column(name = "ROLE_ID")
	private int roleId;

	@Constraints.Required
	@Column(name = "USER_ID")
	private int userId;
	
	public static Finder<Long, UserRole> find = new Finder<Long, UserRole>(
			Long.class, UserRole.class);

}
