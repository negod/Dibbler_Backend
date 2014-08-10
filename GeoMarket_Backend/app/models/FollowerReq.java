/**
 * This contains information demands/wishes that a company
 * can demand on a follower that wants to follow the company.
 * For example loyalty programs. Has language support that can be extended
 */

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
