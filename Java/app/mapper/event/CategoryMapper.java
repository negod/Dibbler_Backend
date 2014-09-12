package mapper.event;

import mapper.GenericMapperLang;
import models.Category;
import constants.Language;
import dto.CategoryDto;

public class CategoryMapper extends GenericMapperLang<Category, CategoryDto> {

	private static final CategoryMapper instance = new CategoryMapper();

	private CategoryMapper() {

	}

	public static CategoryMapper getInstance() {
		return instance;
	}

	@Override
	public Category mapToEntity(CategoryDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDto mapToDto(Category entity, Language lang) {
		CategoryDto category = new CategoryDto();
		category.setName(getCorrectCategoryText(entity, lang));
		return category;
	}

	private String getCorrectCategoryText(Category category, Language lang) {
		switch (lang) {
		case EN:
			return category.getNameEnglish();
		case SE:
			return category.getNameSwedish();
		default:
			return category.getNameEnglish();
		}

	}

}
