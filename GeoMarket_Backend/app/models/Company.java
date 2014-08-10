/**
 * This is all the information for a company.
 * Each Company has geometrical point that specifies it´s spatial positioning
 * A Company can have a mother company
 */

package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "company")
public class Company extends Model {

	private static final long serialVersionUID = 7495897286691526264L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(40)
	@NotNull
    @Size(min = 1, max = 40)
	@Column(name = "IDNR")
	private String idnr;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(40)
	@NotNull
    @Size(min = 1, max = 40)
	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(40)
	@NotNull
    @Size(min = 1, max = 40)
	@Column(name = "STREET")
	private String street;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(10)
	@NotNull
    @Size(min = 1, max = 10)
	@Column(name = "STREETNR")
	private String streetnr;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(20)
	@NotNull
    @Size(min = 1, max = 20)
	@Column(name = "CITY")
	private String city;

	@Constraints.MaxLength(20)
	@Size(max = 20)
	@Column(name = "STATE")
	private String state;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(3)
	@NotNull
    @Size(min = 1, max = 3)
	@Column(name = "COUNTRY")
	private String country;

	@Constraints.Required
	@Constraints.MinLength(1)
	@Constraints.MaxLength(10)
	@NotNull
    @Size(min = 1, max = 10)
	@Column(name = "POSTALCODE")
	private String postalcode;

	@Constraints.Required
	@NotNull
	@Lob
	@Column(name = "LOCATION")
	private byte[] location;

	@Column(name = "PARENTCOMPANY")
	private Integer parentcompany;

	@Column(name = "FOLLOWER_REQ_ID")
	private Integer followerReqId;
	
	
	public static Finder<Long, Company> find = new Finder<Long, Company>(
			Long.class, Company.class);

}
