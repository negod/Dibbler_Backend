package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name = "category")
public class Category extends Model {

	private static final long serialVersionUID = 2353715873245995344L;

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME_ENG")
	private String nameEnglish;
	@Column(name = "NAME_SWE")
	private String nameSwedish;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getNameSwedish() {
		return nameSwedish;
	}

	public void setNameSwedish(String nameSwedish) {
		this.nameSwedish = nameSwedish;
	}

	public static Finder<Long, Category> find = new Finder<Long, Category>(
			Long.class, Category.class);

}
