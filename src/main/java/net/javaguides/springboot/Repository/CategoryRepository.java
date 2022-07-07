package net.javaguides.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Model.ParentCategory;

@Repository
public interface CategoryRepository extends JpaRepository<ParentCategory, Long>{

}
