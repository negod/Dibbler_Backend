/**
 * Role has predefined roles that a user can have. 
 * One user can have more tha one role.
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "role")
public class Role extends Model {

	private static final long serialVersionUID = -3315794746090641981L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "ROLE")
	private String role;

	@Size(max = 100)
	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	List<User> users = new ArrayList<User>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public static Finder<Long, Role> find = new Finder<Long, Role>(Long.class,
			Role.class);

}