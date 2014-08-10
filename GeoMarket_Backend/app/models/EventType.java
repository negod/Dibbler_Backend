package models;

import play.db.ebean.Model;

public class EventType extends Model {

	@Id
	@NotNull
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "NAME_ENG")
	private String nameEng;
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "DESCRIPTION_ENG")
	private String descriptionEng;
	@Size(max = 20)
	@Column(name = "NAME_SWE")
	private String nameSwe;
	@Size(max = 100)
	@Column(name = "DESCRIPTION_SWE")
	private String descriptionSwe;

}
