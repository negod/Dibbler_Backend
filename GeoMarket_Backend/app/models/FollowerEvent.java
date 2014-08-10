/**
 * This is a relation between CompanyUser and Event.
 * Is used when an explicit offer to the followers is created.
 * In this case one FolloweEvent is created for each user.
 * A QR code (GUID) can also be generated in theese cases
 */
package models;

import play.db.ebean.Model;

public class FollowerEvent extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Column(name = "EVENT_ID")
	private int eventId;
	@NotNull
	@Column(name = "COMPANY_USER_ID")
	private int companyUserId;
	@Size(max = 40)
	@Column(name = "QR_CODE")
	private String qrCode;
	@Column(name = "REDEEMED")
	private Boolean redeemed;

}
