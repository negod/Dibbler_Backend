/**
 * This is a relation table between Session and Event
 * This is for checking that the same event is delivered twice
 * to the same unregistered user
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "session_event")
public class SessionEvent extends Model {

	private static final long serialVersionUID = 4933372471394631227L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@NotNull
	@Column(name = "SESSION_ID")
	private int sessionId;

	@Constraints.Required
	@NotNull
	@Column(name = "EVENT_ID")
	private int eventId;
	
	public static Finder<Long, SessionEvent> find = new Finder<Long, SessionEvent>(
			Long.class, SessionEvent.class);

}
