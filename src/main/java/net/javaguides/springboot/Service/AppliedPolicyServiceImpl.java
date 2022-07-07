package net.javaguides.springboot.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.Model.AppliedPolicy;
import net.javaguides.springboot.Repository.AppliedPolicyRepository;

@Service
public class AppliedPolicyServiceImpl implements AppliedPolicyService {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private AppliedPolicyRepository appliedPolicyRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AppliedPolicy> getAppliedPoliciesByUserId(long userId) {
		Query query = entityManager.createQuery("select a from AppliedPolicy a where a.userId = "+userId);
		List<AppliedPolicy> appliedPolicy = query.getResultList();
		return appliedPolicy;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AppliedPolicy> getAproovedPolicies(String status) {
		Query query = entityManager.createQuery("select a from AppliedPolicy a where a.appliedStatus = '"+status+"'");
		List<AppliedPolicy> appliedPolicy = query.getResultList();
		return appliedPolicy;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AppliedPolicy> getPendingPolicies(String status) {
		Query query = entityManager.createQuery("select a from AppliedPolicy a where a.appliedStatus = '"+status+"'");
		List<AppliedPolicy> appliedPolicy = query.getResultList();
		return appliedPolicy;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<AppliedPolicy> getDisaproovedPolicies(String status) {
		Query query = entityManager.createQuery("select a from AppliedPolicy a where a.appliedStatus = '"+status+"'");
		List<AppliedPolicy> appliedPolicy = query.getResultList();
		return appliedPolicy;
	}

	@Override
	public void saveAppliedPolicy(AppliedPolicy appliedPolicy) {
		this.appliedPolicyRepository.save(appliedPolicy);
	}

	@Override
	public void deleteAppliedPolicyByAppliedId(long appliedId) {
		this.appliedPolicyRepository.deleteById(appliedId);
	}

	@Override
	public AppliedPolicy getappliedPolicyByAppliedId(long appliedId) {
		Optional<AppliedPolicy> optional =appliedPolicyRepository.findById(appliedId);
		AppliedPolicy appliedPolicy= null;
		if(optional.isPresent()) {
			appliedPolicy = optional.get();
			}else {
				throw new RuntimeException("Applied Id not found :: " + appliedId);
			}
			return appliedPolicy;
	}
}
