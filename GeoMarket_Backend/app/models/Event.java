/**
 * This contains information about the events that a company
 * wants to publish.
 */
package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

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
	@Column(name = "COMPANY_ID")
	private int companyId;

	@Constraints.Required
	@Column(name = "CATEGORY_ID")
	private int categoryId;

	@Constraints.Required
	@Column(name = "EVENT_TYPE_ID")
	private int eventTypeId;

	@Constraints.Required
	@Column(name = "EVENT_TEXT_ID")
	private int eventTextId;

	@Constraints.Required
	@Column(name = "STARTDATE")
	@CreatedTimestamp
	private Timestamp startdate;

	@Constraints.Required
	@Column(name = "ENDDATE")
	@CreatedTimestamp
	private Timestamp enddate;

	@Constraints.MaxLength(120)
	@Column(name = "IMAGE")
	private String image;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(38)
	@Column(name = "QR_CODE")
	private String qrCode;

	@Constraints.Required
	@Column(name = "QR_STAT")
	private int qrStat;

	@Column(name = "MAX_REDEEM")
	private Integer maxRedeem;
	
	
	public static Finder<Long, Event> find = new Finder<Long, Event>(
			Long.class, Event.class);

}
