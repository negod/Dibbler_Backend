/**
 * Contains information about our registered users.
 * Table contains web and "native app" users.
 * A user must have a role
 */

package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "USERNAME")
	private String username;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(60)
	@NotNull
	@Size(min = 1, max = 60)
	@Column(name = "EMAIL")
	private String email;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(128)
	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "PASSWORD")
	private String password;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(128)
	@NotNull
	@Size(min = 1, max = 128)
	@Column(name = "SALT")
	private String salt;

	@Constraints.MaxLength(40)
	@Size(max = 40)
	@Column(name = "GOOGLE_ID")
	private String googleId;

	@Constraints.MaxLength(40)
	@Size(max = 40)
	@Column(name = "FACEBOOK_ID")
	private String facebookId;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(1)
	@NotNull
	@Size(min = 1, max = 1)
	@Column(name = "GENDER")
	private String gender;

	@Constraints.Required
	@NotNull
	@Column(name = "AGE")
	private int age;

	@Constraints.MaxLength(100)
	@Size(max = 100)
	@Column(name = "IMAGE")
	private String image;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
	List<Role> roles = new ArrayList<Role>();

	@ManyToOne(cascade = CascadeType.ALL)
	private List<Session> sessions;

	@ManyToOne(cascade = CascadeType.ALL)
	private List<Filter> filters;

	@OneToOne(cascade = CascadeType.ALL)
	private Setting setting;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public static Finder<Long, User> find = new Finder<Long, User>(Long.class,
			User.class);

}
