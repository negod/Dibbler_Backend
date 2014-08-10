/**
 * Session keeps track on the users movements and which
 * events that have been delivered to unregistered users.
 * A session must have an ID but not a User (Registered User)
 */

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
