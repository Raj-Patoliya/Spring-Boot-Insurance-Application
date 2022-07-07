package net.javaguides.springboot.Service;

import java.util.List;
import net.javaguides.springboot.Model.SubCategory;

public interface SubCategoryService {
	List<SubCategory> getAllSubCategories();
	void saveSubCategory(SubCategory subCategory);
	void deleteSubCategory(long subCatId);
	SubCategory editSubCategory(long subCatId);
	List<SubCategory> getSubCategoryBypCatId(String pCatId);
}
