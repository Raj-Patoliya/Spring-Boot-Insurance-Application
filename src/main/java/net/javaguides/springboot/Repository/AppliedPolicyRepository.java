package net.javaguides.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.Model.AppliedPolicy;
@Repository
public interface AppliedPolicyRepository extends JpaRepository<AppliedPolicy, Long>{

}
