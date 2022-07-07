
package net.javaguides.springboot.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.javaguides.springboot.Model.AdminLogin;
import net.javaguides.springboot.Model.AppliedPolicy;
import net.javaguides.springboot.Model.ParentCategory;
import net.javaguides.springboot.Model.Policy;
import net.javaguides.springboot.Model.SubCategory;
import net.javaguides.springboot.Service.AppliedPolicyService;
import net.javaguides.springboot.Service.CategoryService;
import net.javaguides.springboot.Service.PolicyService;
import net.javaguides.springboot.Service.SubCategoryService;
import net.javaguides.springboot.Service.UserService;

@Controller
public class AdminController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AppliedPolicyService appliedPolicyService;

	@GetMapping("/admin")
	public String adminLogin() {
		return "Admin/adminLogin";
	}
	@PostMapping("adminLogin")
	public String loginAuth(@ModelAttribute("admin")AdminLogin admin ) {
		if(admin.getUsername().equals("admin") && admin.getPassword().equals("123"))
		{	
			return "Admin/index";
		}
		else
		{
			return "Admin/adminLogin";
		}
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "Admin/index";
	}
	
//*******************************Parent Category*************************************************************************************************
	
	
	//Insert New Category
	@GetMapping("/addCategory")
	public String addCategory() {
		return "Admin/addCategory";
	}
	
	//Saving Category
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("parentCategory") ParentCategory parentCategory) 
	{
		categoryService.saveCategory(parentCategory);
		return "redirect:manageCategory";
	}
	
	//View Category List
	@GetMapping("/manageCategory")
	public String manageCategory(Model model)
	{
		model.addAttribute("categoryList", categoryService.getAllCategory());
		return "Admin/manageCategory";
	}
	//Delete category
	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(value ="id") long id)
	{
		categoryService.deleteCategory(id);

		return "redirect:/manageCategory";
	}
	@GetMapping("/editCategory/{id}")
	public String editCategory(@PathVariable(value ="id") long id,Model model)
	{
		ParentCategory parentCategory = categoryService.editCategory(id);
		model.addAttribute("parentCategory", parentCategory);
		return "Admin/editCategory";
	}
	@RequestMapping("/editCategory")
	public String editCategory(@ModelAttribute("parentCategory") ParentCategory parent,Model model) 
	{
		ParentCategory parentCategory= categoryService.editCategory(parent.getpId());
		model.addAttribute("parentCategory", parentCategory);
		return "Admin/editCategory";
	}
//Sub Category ********************************************************************************************************************
	
	//Showing all Sub category
	@GetMapping("/manageSubCategory")
	public String manageSubCategory(Model model) {
		model.addAttribute("subCatList",subCategoryService.getAllSubCategories() );
		return "Admin/manageSubCategory";
	}
	//Insert New category
	@GetMapping("/addSubCategory")
	public String addSubCategory(Model model) {
		model.addAttribute("catList", categoryService.getAllCategory());
		return "Admin/addSubCategory";
	}
	//Save category to database
	@PostMapping("/saveSubCategory")
	public String saveSubCategory(@RequestParam("pCatId") String pCatId,@RequestParam("subCatName") String subCatName,@RequestParam("subCatDesc") String subCatDesc,@RequestParam("image") MultipartFile image) throws IOException  
	{
		SubCategory subCategory = new SubCategory();
		subCategory.setpCatId(pCatId);
		subCategory.setSubCatName(subCatName);
		subCategory.setImage(java.util.Base64.getEncoder().encodeToString(image.getBytes()));
		subCategory.setSubCatDesc(subCatDesc);
		subCategoryService.saveSubCategory(subCategory);
		return "redirect:manageSubCategory";
	}
	//Delete Sub Category
	@GetMapping("/deleteSubCategory/{id}")
	public String deleteSubCategory(@PathVariable(value ="id") long id)
	{
		subCategoryService.deleteSubCategory(id);
		return "redirect:/manageSubCategory";
	}
	@PostMapping("/editSubCategory")
	public String editSubCategory(@ModelAttribute("subCatgory") SubCategory category,Model model)
	{
		SubCategory subCategory = subCategoryService.editSubCategory(category.getSubCatId());
		model.addAttribute("catList", categoryService.getAllCategory());
		model.addAttribute("subCategory", subCategory);
		return "Admin/editSubCategory";
	}
	
//Policy Operations********************************************************************************************************************	
	
	@GetMapping("/policySelectCategory")
	public String policySelectCategory(Model model) {
		model.addAttribute("categoryList", categoryService.getAllCategory());	
		return "Admin/policySelectCategory";
	}
	@PostMapping("/policyAdd")
	public String policyAdd(@ModelAttribute("subCatgory") SubCategory category,Model model)
	{
		List<SubCategory> subCategory= subCategoryService.getSubCategoryBypCatId(category.getpCatId());
		model.addAttribute("subCategory", subCategory);
		model.addAttribute("categoryList", category);
		return "Admin/addPolicy";
	}
	@PostMapping("savePolicy")
	public String savePolicy(
			@RequestParam("polName") String polName,
			@RequestParam("tenure") String tenure,
			@RequestParam("assured") String assured,
			@RequestParam("premium") String premium,
			@RequestParam("pCatId") String pCatId,
			@RequestParam("subCatId") String subCatId,
			@RequestParam("polDesc") String polDesc,
			@RequestParam("image") MultipartFile image) throws IOException
	{
		Policy policy = new Policy();
		policy.setAssured(assured);
		policy.setpCatId(pCatId);
		policy.setPolDesc(polDesc);
		policy.setPolName(polName);
		policy.setPremium(premium);
		policy.setSubCatId(subCatId);
		policy.setTenure(tenure);
		policy.setImage(java.util.Base64.getEncoder().encodeToString(image.getBytes()));
		policy.setStatus("Active");
		policyService.savePolicy(policy);
		return "redirect:managePolicy";
	}
	
	@PostMapping("editPolicy")
	public String editPolicy(@RequestParam("polId")long polId,Model model)
	{
		Policy policy =policyService.editPolicy(polId);
		List<SubCategory> subCategory= subCategoryService.getSubCategoryBypCatId(policy.getPCatId());
		System.out.println(policy.getPCatId());
		model.addAttribute("subCategory", subCategory);
		model.addAttribute("policy", policy);
		return "Admin/editPolicy";
	}
	
	@GetMapping("/deletePolicy/{id}")
	public String deletePolicy(@PathVariable(value ="id") long id)
	{
		policyService.deletePolicy(id);
		return "redirect:/managePolicy";
	}

	@GetMapping("/managePolicy")
	public String managePolicy(Model model) {
		model.addAttribute("policyList", policyService.getallPolicy());
		return "Admin/managePolicy";
	}
	
	@GetMapping("/aproovedPolicy")
	public String aproovedPolicy(Model model) {
		model.addAttribute("appliedPolicy", appliedPolicyService.getAproovedPolicies("Approved"));
		return "Admin/aproovedPolicy";
	}
	@GetMapping("/disaproovedPolicy")
	public String disaproovedPolicy(Model model) {
		model.addAttribute("appliedPolicy", appliedPolicyService.getDisaproovedPolicies("Disapproved"));
		return "Admin/disaproovedPolicy";
	}
	@GetMapping("/pendingPolicy")
	public String pendingPolicy(Model model) {
		model.addAttribute("appliedPolicy", appliedPolicyService.getPendingPolicies("Pending"));
		return "Admin/pendingPolicy";
	}
	@RequestMapping("appliedPolicyStatus")
	public String appliedPolicyStatus(@RequestParam("appliedId") long appliedId,@RequestParam("appliedStatus") String appliedStatus)
	{
		AppliedPolicy appliedPolicy = appliedPolicyService.getappliedPolicyByAppliedId(appliedId);
		appliedPolicy.setAppliedStatus(appliedStatus);
		appliedPolicyService.saveAppliedPolicy(appliedPolicy);
		return
				"redirect:pendingPolicy";
	}
//******************  User's Operations *************************************************************************************************************
	
	@GetMapping("/manageUser")
	public String manageUser(Model model) {
		model.addAttribute("userList",userService.getAllUsers());
		return "Admin/manageUser";
	}
	@RequestMapping("userStatus")
	public String userStatus(@RequestParam("userId") long id,@RequestParam("status") String status)
	{
		if(status.equals("Active"))
		{
			userService.changeStatus(id, "Deactive");
			return "redirect:manageUser";
		}
		else
		{
			userService.changeStatus(id, "Active");
			return "redirect:manageUser";			
		}
	}
	
	@PostMapping("payment")
	public String payment(@RequestParam("appliedId") long appliedId) {
		AppliedPolicy appliedPolicy = appliedPolicyService.getappliedPolicyByAppliedId(appliedId);
		appliedPolicy.setPaymentStatus("Paid");
		appliedPolicyService.saveAppliedPolicy(appliedPolicy);
		return "User/Razorpay";
	}
}
