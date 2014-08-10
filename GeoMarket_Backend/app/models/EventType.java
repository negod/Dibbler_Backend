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

import play.data.validation.Constraints;
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

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(20)
	@NotNull
    @Size(min = 1, max = 20)
	@Column(name = "NAME_ENG")
	private String nameEng;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(100)
	@NotNull
    @Size(min = 1, max = 100)
	@Column(name = "DESCRIPTION_ENG")
	private String descriptionEng;

	@Constraints.MaxLength(20)
	@Size(max = 20)
	@Column(name = "NAME_SWE")
	private String nameSwe;

	@Constraints.MaxLength(100)
	@Size(max = 100)
	@Column(name = "DESCRIPTION_SWE")
	private String descriptionSwe;
	
	public static Finder<Long, EventType> find = new Finder<Long, EventType>(
			Long.class, EventType.class);

}
