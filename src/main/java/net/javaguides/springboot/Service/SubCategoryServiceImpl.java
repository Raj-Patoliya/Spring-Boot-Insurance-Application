package net.javaguides.springboot.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.Model.SubCategory;
import net.javaguides.springboot.Repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Override
	public List<SubCategory> getAllSubCategories() {
		return subCategoryRepository.findAll();
	}

	@Override
	public void saveSubCategory(SubCategory subCategory) {
		this.subCategoryRepository.save(subCategory);
		
	}

	@Override
	public void deleteSubCategory(long subCatId) {
		this.subCategoryRepository.deleteById(subCatId);
		
	}

	@Override
	public SubCategory editSubCategory(long subCatId) {
		Optional<SubCategory> optional =subCategoryRepository.findById(subCatId);
		SubCategory subCategory= null;
		if(optional.isPresent()) {
			subCategory = optional.get();
			}else {
				throw new RuntimeException("Sub-Category was not found for id :: " + subCatId);
			}
			return subCategory;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategory> getSubCategoryBypCatId(String pCatId)
	{
		Query query = entityManager.createQuery("select s from SubCategory s where s.pCatId = '"+pCatId+"'");
		List<SubCategory> subCategory= query.getResultList();
		return subCategory;
	}
}
