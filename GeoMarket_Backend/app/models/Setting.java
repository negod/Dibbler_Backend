/**
 * Every User has a setting that decides look and feel
 * and the settings for the application
 */

package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isMapAsDefault() {
		return mapAsDefault;
	}

	public void setMapAsDefault(boolean mapAsDefault) {
		this.mapAsDefault = mapAsDefault;
	}

	public boolean isFollowontop() {
		return followontop;
	}

	public void setFollowontop(boolean followontop) {
		this.followontop = followontop;
	}

	public boolean isAllowPush() {
		return allowPush;
	}

	public void setAllowPush(boolean allowPush) {
		this.allowPush = allowPush;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static Finder<Long, Setting> find = new Finder<Long, Setting>(
			Long.class, Setting.class);

}
