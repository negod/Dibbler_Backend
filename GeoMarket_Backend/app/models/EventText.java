/**
 * This contains the text for an event. 
 * has linguistic support for Swedish and Englis but can be expanded.
 * 
 */
package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "event_text")
public class EventText extends Model {

	private static final long serialVersionUID = 7840284306896847707L;

	@Id
	@Basic(optional = false)
	@Constraints.Required
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(20)
	@Column(name = "HEADING_ENG")
	private String headingEng;

	@Constraints.MaxLength(20)
	@Column(name = "HEADING_SWE")
	private String headingSwe;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(200)
	@Column(name = "BODY_ENG")
	private String bodyEng;

	@Constraints.MaxLength(200)
	@Column(name = "BODY_SWE")
	private String bodySwe;
	
	
	public static Finder<Long, EventText> find = new Finder<Long, EventText>(
			Long.class, EventText.class);

}
