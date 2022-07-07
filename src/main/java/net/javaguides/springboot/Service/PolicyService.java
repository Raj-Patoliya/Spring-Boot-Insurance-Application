package net.javaguides.springboot.Service;

import java.util.List;

import net.javaguides.springboot.Model.Policy;

public interface PolicyService {
List<Policy> getallPolicy();
void deletePolicy(long polId);
void savePolicy(Policy policy);
Policy editPolicy(long polId);
List<Policy> getPolicyBysubCatId(String subCatId);
}
