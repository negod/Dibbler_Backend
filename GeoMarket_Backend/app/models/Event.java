/**
 * This contains information about the events that a company
 * wants to publish.
 */
package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@Constraints.Required
	@NotNull
	@Column(name = "COMPANY_ID")
	private int companyId;

	@Constraints.Required
	@NotNull
	@Column(name = "CATEGORY_ID")
	private int categoryId;

	@Constraints.Required
	@NotNull
	@Column(name = "EVENT_TYPE_ID")
	private int eventTypeId;

	@Constraints.Required
	@NotNull
	@Column(name = "EVENT_TEXT_ID")
	private int eventTextId;

	@Constraints.Required
	@NotNull
	@Column(name = "STARTDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startdate;

	@Constraints.Required
	@NotNull
	@Column(name = "ENDDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date enddate;

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

	public static Finder<Long, Event> find = new Finder<Long, Event>(
			Long.class, Event.class);

}
