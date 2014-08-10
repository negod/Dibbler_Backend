/**
 * This filter excludes certain results for the user.
 * these are created and administered by the user.
 */

package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	@ManyToOne
	private User user;

	@Column(name = "COMPANY_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private Company company;

	@Column(name = "CATEGORY_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;

	@Column(name = "EVENT_TYPE_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private EventType eventType;

	@Constraints.Required
	@NotNull
	@Column(name = "ACTIVE")
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static Finder<Long, Filter> find = new Finder<Long, Filter>(
			Long.class, Filter.class);
}
