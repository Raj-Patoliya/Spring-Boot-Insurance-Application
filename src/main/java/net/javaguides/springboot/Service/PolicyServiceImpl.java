package net.javaguides.springboot.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.Model.Policy;
import net.javaguides.springboot.Repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired EntityManager entityManager;
	@Autowired PolicyRepository policyRepository;
	@Override
	public List<Policy> getallPolicy() {
		return policyRepository.findAll();
	}
	@Override
	public void deletePolicy(long polId) {
		this.policyRepository.deleteById(polId);
	}
	@Override
	public void savePolicy(Policy policy) {
		this.policyRepository.save(policy);
		
	}
	@Override
	public Policy editPolicy(long polId) {
		Optional<Policy> optional = policyRepository.findById(polId);
		Policy policy= null;
		if(optional.isPresent())
		{
			policy = optional.get();
		}
		else
		{
			throw new RuntimeException("Employee not found for id :: " + polId);
		}
		return policy;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Policy> getPolicyBysubCatId(String subCatId) {
		Query query = entityManager.createQuery("select p from Policy p where p.subCatId = '"+subCatId+"'");
		List<Policy> policy = query.getResultList();
		return policy;
	}
}
