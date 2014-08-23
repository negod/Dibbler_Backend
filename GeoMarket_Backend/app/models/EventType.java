/**
 * This contains categories for the type of events that is 
 * created and published. Har linguistic support for swedish and
 * English but can expand.
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

import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "event_type")
public class EventType extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getDescriptionEng() {
		return descriptionEng;
	}

	public void setDescriptionEng(String descriptionEng) {
		this.descriptionEng = descriptionEng;
	}

	public String getNameSwe() {
		return nameSwe;
	}

	public void setNameSwe(String nameSwe) {
		this.nameSwe = nameSwe;
	}

	public String getDescriptionSwe() {
		return descriptionSwe;
	}

	public void setDescriptionSwe(String descriptionSwe) {
		this.descriptionSwe = descriptionSwe;
	}

	public static Finder<Long, EventType> find = new Finder<Long, EventType>(
			Long.class, EventType.class);

}
