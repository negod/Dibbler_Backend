/**
 * Session keeps track on the users movements and which
 * events that have been delivered to unregistered users.
 * A session must have an ID but not a User (Registered User)
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
import javax.persistence.OneToMany;
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
	@OneToMany
	private User user;

	@Constraints.MaxLength(40)
	@Size(max = 40)
	@Column(name = "DEVICE_ID")
	private String deviceId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
	private List<Movement> movements = new ArrayList<Movement>();

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "sessions")
	private List<Event> events = new ArrayList<Event>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public static Finder<Long, Session> find = new Finder<Long, Session>(
			Long.class, Session.class);

}
