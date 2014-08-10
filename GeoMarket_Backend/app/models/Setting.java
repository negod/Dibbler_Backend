package models;

import play.db.ebean.Model;

public class Setting extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Column(name = "USER_ID")
	private int userId;
	@NotNull
	@Column(name = "MAP_AS_DEFAULT")
	private boolean mapAsDefault;
	@NotNull
	@Column(name = "FOLLOWONTOP")
	private boolean followontop;
	@NotNull
	@Column(name = "ALLOW_PUSH")
	private boolean allowPush;
	@NotNull
	@Size(min = 1, max = 3)
	@Column(name = "LANGUAGE")
	private String language;

}
