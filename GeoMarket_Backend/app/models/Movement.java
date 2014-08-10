/**
 * Movement saves the positions that are delivered
 * together with a request. All movements corralates to a session.
 * The corralation is because we eant to trace not registered users.
 */

package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * 
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
@Table(name = "movement")
public class Movement extends Model {

	private static final long serialVersionUID = -2959793521947591629L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@Column(name = "SESSION_ID")
	private int sessionId;

	@Constraints.Required
	@Column(name = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	@Constraints.Required
	@Lob
	@Column(name = "GEOM")
	private byte[] geom;
	
	public static Finder<Long, Movement> find = new Finder<Long, Movement>(
			Long.class, Movement.class);

}
