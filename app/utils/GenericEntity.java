package utils;

import java.util.List;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

public class GenericEntity<T> extends Model {

	private static final long serialVersionUID = -6832365994706942079L;
	
	Class<T> clazz;
	private final Finder<Long, T> find;

	public GenericEntity() {
		find = new Finder<Long, T>(Long.class, this.clazz);
	}

	public T findById(Long id) {
		return find.byId(id);
	}

	public List<T> findListByArg(String column, String arg) {
		return find.where().like(column, arg).findList();
	}

	public T findByArg(String column, String arg) {
		return find.where().like(column, arg).findUnique();
	}

	public List<T> findAll() {
		return find.all();
	}

}
