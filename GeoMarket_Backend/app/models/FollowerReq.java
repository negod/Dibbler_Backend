/**
 * This contains information demands/wishes that a company
 * can demand on a follower that wants to follow the company.
 * For example loyalty programs. Has language support that can be extended
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
@Table(name = "follower_req")
public class FollowerReq extends Model {

	private static final long serialVersionUID = 8791458503307109923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@Column(name = "MANDATORY")
	private boolean mandatory;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(40)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "NAME_ENG")
	private String nameEng;

	@Constraints.MaxLength(40)
	@Size(max = 40)
	@Column(name = "NAME_SWE")
	private String nameSwe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getNameSwe() {
		return nameSwe;
	}

	public void setNameSwe(String nameSwe) {
		this.nameSwe = nameSwe;
	}

	public static Finder<Long, FollowerReq> find = new Finder<Long, FollowerReq>(
			Long.class, FollowerReq.class);

}
