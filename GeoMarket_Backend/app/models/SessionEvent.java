
/**
 * This is a relation table between Session and Event
 * This is for checking that the same event is delivered twice
 * to the same unregistered user
 */
package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
*
* @author Joakikm Johansson (joakimjohansson@outlook.com)
*/

public class SessionEvent extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Constraints.Required
	@Column(name = "SESSION_ID")
	private int sessionId;
	@Constraints.Required
	@Column(name = "EVENT_ID")
	private int eventId;

}
