/**
 * Every User has a setting that decides look and feel
 * and the settings for the application
 */

package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
*
* @author Joakikm Johansson (joakimjohansson@outlook.com)
*/

@Entity
@Table(name = "setting")
public class Setting extends Model {
	
	private static final long serialVersionUID = -4592697686166860695L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Constraints.Required
	@NotNull
	@Column(name = "USER_ID")
	private int userId;
	
	@Constraints.Required
	@NotNull
	@Column(name = "MAP_AS_DEFAULT")
	private boolean mapAsDefault;
	
	@Constraints.Required
	@NotNull
	@Column(name = "FOLLOWONTOP")
	private boolean followontop;
	
	@Constraints.Required
	@NotNull
	@Column(name = "ALLOW_PUSH")
	private boolean allowPush;
	
	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(3)
	@NotNull
    @Size(min = 1, max = 3)
	@Column(name = "LANGUAGE")
	private String language;
	
	public static Finder<Long, Setting> find = new Finder<Long, Setting>(
			Long.class, Setting.class);

}
