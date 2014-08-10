package models;

import play.db.ebean.Model;

public class EventText extends Model {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "HEADING_ENG")
	private String headingEng;
	@Size(max = 20)
	@Column(name = "HEADING_SWE")
	private String headingSwe;
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "BODY_ENG")
	private String bodyEng;
	@Size(max = 200)
	@Column(name = "BODY_SWE")
	private String bodySwe;

}
