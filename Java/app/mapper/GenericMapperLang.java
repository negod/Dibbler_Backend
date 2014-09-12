package mapper;

import java.util.ArrayList;
import java.util.List;

import constants.Language;

public abstract class GenericMapperLang<E, D> {

	public List<E> mapToEntityList(List<D> dtoList) {
		List<E> entityList = new ArrayList<E>();
		for (D dto : dtoList) {
			entityList.add(mapToEntity(dto));
		}
		return entityList;
	}

	public List<D> mapToDtoList(List<E> entityList, Language lang) {
		List<D> dtoList = new ArrayList<D>();
		for (E dto : entityList) {
			dtoList.add(mapToDto(dto, lang));
		}
		return dtoList;
	}

	public abstract E mapToEntity(D dto);

	public abstract D mapToDto(E entity, Language lang);

}
