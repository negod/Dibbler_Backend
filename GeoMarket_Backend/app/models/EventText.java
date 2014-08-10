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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Constraints.MaxLength(20)
	@Size(max = 20)
	@NotNull
	@Column(name = "HEADING_SWE")
	private String headingSwe;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(200)
	@Size(max = 200)
	@Column(name = "BODY_SWE")
	private String bodySwe;

	@Constraints.MaxLength(200)
	@Size(min = 1, max = 200)
	@Column(name = "BODY_ENG")
	private String bodyEng;

	@Constraints.MaxLength(20)
	@Size(min = 1, max = 20)
	@Column(name = "HEADING_ENG")
	private String headingEng;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeadingSwe() {
		return headingSwe;
	}

	public void setHeadingSwe(String headingSwe) {
		this.headingSwe = headingSwe;
	}

	public String getBodySwe() {
		return bodySwe;
	}

	public void setBodySwe(String bodySwe) {
		this.bodySwe = bodySwe;
	}

	public String getBodyEng() {
		return bodyEng;
	}

	public void setBodyEng(String bodyEng) {
		this.bodyEng = bodyEng;
	}

	public String getHeadingEng() {
		return headingEng;
	}

	public void setHeadingEng(String headingEng) {
		this.headingEng = headingEng;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Finder<Long, EventText> find = new Finder<Long, EventText>(
			Long.class, EventText.class);

}
