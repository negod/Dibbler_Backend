package models;

import play.db.ebean.Model;

public class Movement extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Column(name = "SESSION_ID")
	private int sessionId;
	@NotNull
	@Column(name = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	@NotNull
	@Lob
	@Column(name = "GEOM")
	private Object geom;

}
