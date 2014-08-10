package models;

import play.db.ebean.Model;

public class Session extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "USER_ID")
	private Integer userId;
	@Size(max = 40)
	@Column(name = "DEVICE_ID")
	private String deviceId;

}
