/**
 * This contains information about the events that a company
 * wants to publish.
 */
package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "event")
public class Event extends Model {

	private static final long serialVersionUID = -6426827362089475472L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	// @Column(name = "COMPANY_ID" )
	@OneToOne(cascade = CascadeType.ALL)
	private Company company;

	// @Column(name = "CATEGORY_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;

	// @Column(name = "EVENT_TYPE_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private EventType eventType;

	// @Column(name = "EVENT_TEXT_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private EventText eventText;

	@Constraints.Required
	@NotNull
	@Column(name = "STARTDATE")
	@Temporal(TemporalType.DATE)
	private Timestamp startdate;

	@Constraints.Required
	@NotNull
	@Column(name = "ENDDATE")
	@Temporal(TemporalType.DATE)
	private Timestamp enddate;

	@Constraints.MaxLength(120)
	@Size(max = 120)
	@Column(name = "IMAGE")
	private String image;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(38)
	@NotNull
	@Size(min = 1, max = 38)
	@Column(name = "QR_CODE")
	private String qrCode;

	@Constraints.Required
	@NotNull
	@Column(name = "QR_STAT")
	private int qrStat;

	@Column(name = "MAX_REDEEM")
	private Integer maxRedeem;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Session> sessions = new ArrayList<Session>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public EventText getEventText() {
		return eventText;
	}

	public void setEventText(EventText eventText) {
		this.eventText = eventText;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public int getQrStat() {
		return qrStat;
	}

	public void setQrStat(int qrStat) {
		this.qrStat = qrStat;
	}

	public Integer getMaxRedeem() {
		return maxRedeem;
	}

	public void setMaxRedeem(Integer maxRedeem) {
		this.maxRedeem = maxRedeem;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public static Finder<Long, Event> find = new Finder<Long, Event>(
			Long.class, Event.class);

}
