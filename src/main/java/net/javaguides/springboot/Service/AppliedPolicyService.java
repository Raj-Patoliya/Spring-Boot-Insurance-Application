package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.AppliedPolicy;

public interface AppliedPolicyService {

	List<AppliedPolicy> getAppliedPoliciesByUserId(long userId);
	List<AppliedPolicy> getAproovedPolicies(String status);
	List<AppliedPolicy> getPendingPolicies(String status);
	List<AppliedPolicy> getDisaproovedPolicies(String status);
	void saveAppliedPolicy(AppliedPolicy appliedPolicy);
	void deleteAppliedPolicyByAppliedId(long appliedId);
	AppliedPolicy getappliedPolicyByAppliedId(long appliedId);
}
