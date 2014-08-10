/**
 * Role has predefined roles that a user can have. 
 * One user can have more tha one role.
 */

package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "role")
public class Role extends Model {

	private static final long serialVersionUID = -3315794746090641981L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(30)
	@NotNull
    @Size(min = 1, max = 30)
	@Column(name = "ROLE")
	private String role;
	
	@Constraints.MaxLength(100)
	@Size(max = 100)
	@Column(name = "DESCRIPTION")
	private String description;
	
	public static Finder<Long, Role> find = new Finder<Long, Role>(
			Long.class, Role.class);

}
