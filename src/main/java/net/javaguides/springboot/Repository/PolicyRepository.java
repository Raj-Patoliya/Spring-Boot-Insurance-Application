package net.javaguides.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long>{
}

