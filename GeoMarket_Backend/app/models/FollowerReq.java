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
	@Column(name = "NAME_ENG")
	private String nameEng;

	@Constraints.MaxLength(40)
	@Column(name = "NAME_SWE")
	private String nameSwe;
	
	public static Finder<Long, FollowerReq> find = new Finder<Long, FollowerReq>(
			Long.class, FollowerReq.class);

}
