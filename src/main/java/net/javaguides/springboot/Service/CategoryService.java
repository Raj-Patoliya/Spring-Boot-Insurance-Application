package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.ParentCategory;

public interface CategoryService {
	List<ParentCategory> getAllCategory();
	void saveCategory(ParentCategory parentCategory);
	void deleteCategory(long id);
	ParentCategory editCategory(long id);
}
