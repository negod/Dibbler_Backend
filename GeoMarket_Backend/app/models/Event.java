/**
 * This contains information about the events that a company
 * wants to publish.
 */
package models;

import play.db.ebean.Model;

public class Event extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Column(name = "COMPANY_ID")
	private int companyId;
	@NotNull
	@Column(name = "CATEGORY_ID")
	private int categoryId;
	@NotNull
	@Column(name = "EVENT_TYPE_ID")
	private int eventTypeId;
	@NotNull
	@Column(name = "EVENT_TEXT_ID")
	private int eventTextId;
	@NotNull
	@Column(name = "STARTDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startdate;
	@NotNull
	@Column(name = "ENDDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date enddate;
	@Size(max = 120)
	@Column(name = "IMAGE")
	private String image;
	@NotNull
	@Size(min = 1, max = 38)
	@Column(name = "QR_CODE")
	private String qrCode;
	@NotNull
	@Column(name = "QR_STAT")
	private int qrStat;
	@Column(name = "MAX_REDEEM")
	private Integer maxRedeem;

}
