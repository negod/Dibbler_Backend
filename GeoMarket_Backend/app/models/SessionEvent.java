package models;

import play.db.ebean.Model;

public class SessionEvent extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Column(name = "SESSION_ID")
	private int sessionId;
	@NotNull
	@Column(name = "EVENT_ID")
	private int eventId;

}
