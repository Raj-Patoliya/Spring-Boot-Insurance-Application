package net.javaguides.springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.javaguides.springboot.Model.ParentCategory;
import net.javaguides.springboot.Repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public void saveCategory(ParentCategory parentCategory) {
		this.categoryRepository.save(parentCategory);
	}
	@Override
	public List<ParentCategory> getAllCategory() {
		return categoryRepository.findAll();
	}
	@Override
	public void deleteCategory(long id) {
		this.categoryRepository.deleteById(id);
		
	}
	@Override
	public ParentCategory editCategory(long id) {
		Optional<ParentCategory> optional =categoryRepository.findById(id);
		ParentCategory parentCategory= null;
		if(optional.isPresent()) {
			parentCategory = optional.get();
			}else {
				throw new RuntimeException("Employee not found for id :: " + id);
			}
			return parentCategory;
	}
	

}
