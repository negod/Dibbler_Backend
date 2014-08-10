/**
 * Every User has a setting that decides look and feel
 * and the settings for the application
 */

package models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
*
* @author Joakikm Johansson (joakimjohansson@outlook.com)
*/

public class Setting extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Constraints.Required
	@Column(name = "USER_ID")
	private int userId;
	
	@Constraints.Required
	@Column(name = "MAP_AS_DEFAULT")
	private boolean mapAsDefault;
	
	@Constraints.Required
	@Column(name = "FOLLOWONTOP")
	private boolean followontop;
	
	@Constraints.Required
	@Column(name = "ALLOW_PUSH")
	private boolean allowPush;
	
	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(3)
	@Column(name = "LANGUAGE")
	private String language;

}
