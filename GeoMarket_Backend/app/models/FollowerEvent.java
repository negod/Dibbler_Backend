/**
 * This is a relation between CompanyUser and Event.
 * Is used when an explicit offer to the followers is created.
 * In this case one FolloweEvent is created for each user.
 * A QR code (GUID) can also be generated in these cases
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "follower_event")
public class FollowerEvent extends Model {

	private static final long serialVersionUID = -3798063037103807815L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@Column(name = "EVENT_ID")
	private int eventId;

	@Constraints.Required
	@Column(name = "COMPANY_USER_ID")
	private int companyUserId;

	@Constraints.MaxLength(40)
	@Column(name = "QR_CODE")
	private String qrCode;

	@Column(name = "REDEEMED")
	private Boolean redeemed;
	
	public static Finder<Long, FollowerEvent> find = new Finder<Long, FollowerEvent>(
			Long.class, FollowerEvent.class);

}
