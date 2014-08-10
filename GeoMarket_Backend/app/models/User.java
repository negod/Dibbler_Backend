/**
 * Contains information about our registered users.
 * Table contains web and "native app" users.
 * A user must have a role
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
@Table(name = "user")
public class User extends Model {

	private static final long serialVersionUID = -5035256714007966909L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(40)
	@Column(name = "USERNAME")
	private String username;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(60)
	@Column(name = "EMAIL")
	private String email;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(128)
	@Column(name = "PASSWORD")
	private String password;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(128)
	@Column(name = "SALT")
	private String salt;

	@Constraints.Required
	@Column(name = "SETTING_ID")
	private int settingId;

	@Constraints.MaxLength(40)
	@Column(name = "GOOGLE_ID")
	private String googleId;

	@Constraints.MaxLength(40)
	@Column(name = "FACEBOOK_ID")
	private String facebookId;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(1)
	@Column(name = "GENDER")
	private String gender;

	@Constraints.Required
	@Column(name = "AGE")
	private int age;

	@Constraints.MaxLength(100)
	@Column(name = "IMAGE")
	private String image;
	
	public static Finder<Long, User> find = new Finder<Long, User>(
			Long.class, User.class);

}
