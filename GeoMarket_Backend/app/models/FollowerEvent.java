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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@NotNull
	@Column(name = "EVENT_ID")
	private int eventId;

	@Constraints.Required
	@NotNull
	@Column(name = "COMPANY_USER_ID")
	private int companyUserId;

	@Constraints.MaxLength(40)
	@Size(max = 40)
	@Column(name = "QR_CODE")
	private String qrCode;

	@Column(name = "REDEEMED")
	private Boolean redeemed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(int companyUserId) {
		this.companyUserId = companyUserId;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public Boolean getRedeemed() {
		return redeemed;
	}

	public void setRedeemed(Boolean redeemed) {
		this.redeemed = redeemed;
	}

	public static Finder<Long, FollowerEvent> find = new Finder<Long, FollowerEvent>(
			Long.class, FollowerEvent.class);

}
