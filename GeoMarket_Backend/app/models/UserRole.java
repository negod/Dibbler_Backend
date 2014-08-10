package models;

import javax.persistence.Column;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

public class UserRole extends Model {

	
	private static final long serialVersionUID = -6300638857870300792L;
	
	@Id
	@Constraints.Required
	@Column(name = "ID")
	private Integer id;
	@Constraints.Required
	@Column(name = "ROLE_ID")
	private int roleId;
	@Constraints.Required
	@Column(name = "USER_ID")
	private int userId;

}
