/**
 * Session keeps track on the users movements and which
 * events that have been delivered to unregistered users.
 * A session must have an ID but not a User (Registered User)
 */

package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
*
* @author Joakikm Johansson (joakimjohansson@outlook.com)
*/

@Entity
@Table(name = "session")
public class Session extends Model {

	private static final long serialVersionUID = 4977528395807182928L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_ID")
	private Integer userId;
	
	@Constraints.MaxLength(40)
	@Size(max = 40)
	@Column(name = "DEVICE_ID")
	private String deviceId;
	
	public static Finder<Long, Session> find = new Finder<Long, Session>(
			Long.class, Session.class);

}
