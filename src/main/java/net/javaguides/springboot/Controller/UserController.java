package net.javaguides.springboot.Controller;

import java.io.IOException;
import java.time.LocalDate;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.javaguides.springboot.Model.AppliedPolicy;
import net.javaguides.springboot.Model.Login;
import net.javaguides.springboot.Model.User;
import net.javaguides.springboot.Service.AppliedPolicyService;
import net.javaguides.springboot.Service.CategoryService;
import net.javaguides.springboot.Service.PolicyService;
import net.javaguides.springboot.Service.SubCategoryService;
import net.javaguides.springboot.Service.UserService;

@Controller
public class UserController {
	
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
	
	
	public static boolean isLoggedIn(HttpServletRequest request,HttpServletResponse response) {
		Cookie ck[] =  request.getCookies();
		if(ck != null)
		{
			for(int i =0;i<ck.length;i++)
			{
				if(ck[i].getName().equals("email") ||ck[i].getName().equals("userId") )
				{
					return true;
				}
			}
			return false;
		}
		else
		{
			return false;
		}
	}
	@GetMapping("/")
	public String index(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		if(isLoggedIn(request, response) == true)
		{
			model.addAttribute("parentCategoryList", categoryService.getAllCategory());
			return "User/Home";
		}
		else
		{
			model.addAttribute("parentCategoryList", categoryService.getAllCategory());
			return "User/index";
		}
	}
	@RequestMapping("aboutus")
	public String about(Model model )
	{
		model.addAttribute("parentCategoryList", categoryService.getAllCategory());
		return "User/About";
	}
	@RequestMapping("userRegistration")
	public String userRegistration(Model model)
	{
		model.addAttribute("parentCategoryList", categoryService.getAllCategory());
		return"User/userRegistration";
	}
	

	@RequestMapping("saveUser")
	public String saveUser(
			@RequestParam("photo") MultipartFile photo,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("mobile") String mobile,
			@RequestParam("age") String age,
			@RequestParam("dateOfBirth") String dateOfBirth,
			@RequestParam("gender") String gender,
			@RequestParam("password") String password,HttpServletRequest request,HttpServletResponse response
			) throws IOException
		{
			User user = new User();
			user.setName(name);
			user.setAge(age);
			user.setEmail(email);
			user.setMobile(mobile);
			user.setDateOfBirth(dateOfBirth);
			user.setGender(gender);
			user.setPassword(password);
			user.setPhoto(java.util.Base64.getEncoder().encodeToString(photo.getBytes()));
			user.setStatus("Active");
			userService.saveUser(user);	
			
			if(isLoggedIn(request, response) == true)
			{
				return "redirect:profile";
			}
			else
			{
				return "redirect:/";
			}
	}	

	@PostMapping("editProfile")
	public String editProfile(@RequestParam("userId") long userId,Model model)
	{
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "User/editProfile";
	}
	
	
	@RequestMapping("login")
	public String userLogin()
	{
		return"User/login";
	}
	@SuppressWarnings("unused")
	@PostMapping("userAuth")
	public String userAuth(@ModelAttribute("login") Login login,HttpServletRequest request,HttpServletResponse response) {
		User user = userService.getUserLoginAuth(login.getEmail(), login.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user.getName());
		System.out.println(user.getUserId());
		if(user != null)
		{
			Cookie email = new Cookie("email", user.getEmail());
			response.addCookie(email);
			Cookie userId = new Cookie("userId", String.valueOf(user.getUserId()));
			response.addCookie(userId);			
			return "redirect:/";
		}
		else{
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response,HttpServletRequest request)
	{
		if(isLoggedIn(request, response) != false)
		{
			Cookie ck[] = request.getCookies();
			for(int i = 0;i<ck.length;i++) 
			{
				Cookie cookie = ck[i];
				if(ck[i].getName().equals("email") || ck[i].getName().equals("userId") )
				{
					System.out.println(cookie.getValue());
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
			return "redirect:/";
		}
		else
		{
			return "redirect:/";		
		}
	}
	
	@GetMapping("userHome")
	public String Home(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		if(isLoggedIn(request, response) == true)
		{
			model.addAttribute("parentCategoryList", categoryService.getAllCategory());
			return "User/Home";
		}
		else
		{
			model.addAttribute("parentCategoryList", categoryService.getAllCategory());
			return "redirect:/";
		}
	}
	
	@GetMapping("profile")
	public String profile(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		long userId = 0;
		if(isLoggedIn(request, response) == true)
		{
			Cookie ck[] =  request.getCookies();
			if(ck != null)
			{
				for(int i =0;i<ck.length;i++)
				{
					if(ck[i].getName().equals("userId") )
					{
							userId = Long.parseLong(ck[i].getValue());
					}
				}
			}
			model.addAttribute("user", userService.getUserById(userId));
			model.addAttribute("parentCategoryList", categoryService.getAllCategory());
			return "User/viewProfile";
		}
		else
		{
			return "redirect:login";
		}
	}
	
	@RequestMapping("userSubCatList")
	public String userSubCatList(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("pCatId") String pCatID)
	{
			model.addAttribute("parentCategoryList", categoryService.getAllCategory());
			model.addAttribute("subCatgoryList", subCategoryService.getSubCategoryBypCatId(pCatID));
			return "User/userSubCatList";
	}
	@RequestMapping("userPolicyList")
	public String userPolicyList(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("subCatId") String subCatId)
	{
			 model.addAttribute("parentCategoryList", categoryService.getAllCategory());	
			  model.addAttribute("policyList",policyService.getPolicyBysubCatId(subCatId));
			return "User/userPolicyList";
	}
	 
	@RequestMapping("userSinglePolicy")
	public String userSinglePolicy(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("polId") long polId)
	{
			 model.addAttribute("parentCategoryList", categoryService.getAllCategory());	
			  model.addAttribute("singlePolicy", policyService.editPolicy(polId));
			return "User/userSinglePolicy";
	}
	
	@PostMapping("applyForPolicy")
	public String applyForPolicy(@ModelAttribute("appliedPolicy") AppliedPolicy appliedPolicy,HttpServletRequest request,HttpServletResponse response)
	{
		if(isLoggedIn(request, response) == true)
		{
			long userId = 0;
			Cookie ck[] =  request.getCookies();
			if(ck != null)
			{
				for(int i =0;i<ck.length;i++)
				{
					if(ck[i].getName().equals("userId"))
					{
						userId =  Long.parseLong(ck[i].getValue());
					}
				}
			}
			User user = userService.getUserById(userId);
			LocalDate localDate = LocalDate.now();
			
			appliedPolicy.setUserId(userId);
			appliedPolicy.setUserName(user.getName());
			appliedPolicy.setAppliedStatus("Pending");
			appliedPolicy.setPaymentStatus("Pending");	
			appliedPolicy.setApplyDate(localDate.toString());
			
			appliedPolicyService.saveAppliedPolicy(appliedPolicy);
			return "User/Home";
		}
		else
		{
			return "redirect:login";
		}
	}
	@GetMapping("viewAppliedPolicy")
	public String viewAppliedPolicy(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		if(isLoggedIn(request, response) == true)
		{
			long userId = 0;
			Cookie ck[] =  request.getCookies();
			if(ck != null)
			{
				for(int i =0;i<ck.length;i++)
				{
					if(ck[i].getName().equals("userId"))
					{
						userId =  Long.parseLong(ck[i].getValue());
					}
				}
			}
			model.addAttribute("parentCategoryList", categoryService.getAllCategory());
			model.addAttribute("appliedPolicy", appliedPolicyService.getAppliedPoliciesByUserId(userId));
			return "User/viewAppliedPolicy";
		}
		else
		{
			return "redirect:login";
		}
	}
}
