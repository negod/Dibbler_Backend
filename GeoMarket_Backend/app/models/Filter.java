/**
 * This filter excludes certain results for the user.
 * these are created and administered by the user.
 */

package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "filter")
public class Filter extends Model {

	private static final long serialVersionUID = 8969589619081521696L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@NotNull
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "COMPANY_ID")
	private Integer companyId;

	@Column(name = "CATEGORY_ID")
	private Integer categoryId;

	@Column(name = "EVENT_TYPE_ID")
	private Integer eventTypeId;

	@Constraints.Required
	@NotNull
	@Column(name = "ACTIVE")
	private boolean active;

	
	public static Finder<Long, Filter> find = new Finder<Long, Filter>(
			Long.class, Filter.class);
}
