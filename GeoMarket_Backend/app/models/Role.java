/**
 * Role has predefined roles that a user can have. 
 * One user can have more tha one role.
 */

package models;

import play.db.ebean.Model;

public class Role extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "ROLE")
	private String role;
	@Size(max = 100)
	@Column(name = "DESCRIPTION")
	private String description;

}
