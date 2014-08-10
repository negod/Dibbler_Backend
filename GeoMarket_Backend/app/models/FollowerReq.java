package models;

import play.db.ebean.Model;

public class FollowerReq extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Column(name = "MANDATORY")
	private boolean mandatory;
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "NAME_ENG")
	private String nameEng;
	@Size(max = 40)
	@Column(name = "NAME_SWE")
	private String nameSwe;

}
