/**
 * This filter excludes certain results for the user.
 * these are created and administered by the user.
 */

package models;

import play.db.ebean.Model;

public class Filter extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@NotNull
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "COMPANY_ID")
	private Integer companyId;
	@Column(name = "CATEGORY_ID")
	private Integer categoryId;
	@Column(name = "EVENT_TYPE_ID")
	private Integer eventTypeId;
	@NotNull
	@Column(name = "ACTIVE")
	private boolean active;

}
