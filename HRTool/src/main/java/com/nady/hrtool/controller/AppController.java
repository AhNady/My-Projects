package com.nady.hrtool.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nady.hrtool.model.Offer;
import com.nady.hrtool.model.User;
import com.nady.hrtool.model.UserProfile;
import com.nady.hrtool.model.UserProfileType;
import com.nady.hrtool.service.OfferService;
import com.nady.hrtool.service.UserProfileService;
import com.nady.hrtool.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	OfferService offerService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	private Environment environment;

	/**
	 * List all the existing users (Employees and HR Admins(which already
	 * employees))
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userslist";
	}

	/**
	 * Retrieve the current user Information
	 */
	@RequestMapping(value = { "/", "/userInfo" }, method = RequestMethod.GET)
	public String getUserInfo(ModelMap model) {
		User user = userService.findByUserName(getPrincipal());
		model.addAttribute("currentUser", user);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userInfo";
	}

	/**
	 * Add a new user (Employee or HR Admin).
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "user";
	}

	/**
	 * Add a new user and check if the user already exist or not It also
	 * validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "user";
		}

		if (!userService.isUserNameUnique(user.getId(), user.getUserName())) {
			FieldError ssoError = new FieldError("user", "userName", messageSource.getMessage(
					"This User Name already exist", new String[] { user.getUserName() }, Locale.getDefault()));
			result.addError(ssoError);
			return "user";
		}
		userService.saveOrUpdateUser(user);
		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "usersuccess";
	}

	/**
	 * This method will provide the page to update a user.
	 */
	@RequestMapping(value = { "/edit-user-{userName}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String userName, ModelMap model) {
		User user = userService.findByUserName(userName);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "user";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{userName}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String userName) {

		if (result.hasErrors()) {
			return "user";
		}

		userService.saveOrUpdateUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "usersuccess";
	}

	/**
	 * This method will delete a user
	 */
	@RequestMapping(value = { "/delete-user-{userName}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String userName) {
		userService.deleteUserByUserName(userName);
		return "redirect:/list";
	}

	/**
	 * This method will provide the page to add a new offer
	 */
	@RequestMapping(value = { "/offer" }, method = RequestMethod.GET)
	public String newoffer(ModelMap model) {
		Offer offer = new Offer();
		model.addAttribute("offer", offer);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "offer";
	}

	/**
	 * Add a new offer and check if the offer already exist or not
	 */
	@RequestMapping(value = { "/offer" }, method = RequestMethod.POST)
	public String saveOffer(@Valid Offer offer, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "offer";
		}
		if (!offerService.isOfferNameUnique(offer.getId(), offer.getOfferName())) {
			FieldError ssoError = new FieldError("offer", "offerName", messageSource.getMessage(
					"This Offer already exist", new String[] { offer.getOfferName() }, Locale.getDefault()));
			result.addError(ssoError);
			return "offer";
		}

		offerService.saveOrUpdateOffer(offer);

		model.addAttribute("success",
				"Offer " + offer.getOfferName() + " " + offer.getOfferDescription() + " added successfully");
		model.addAttribute("loggedinuser", getPrincipal());

		return "offersuccess";
	}

	/**
	 * Search in the offer lists by offer name
	 */
	@RequestMapping(value = { "/offers" }, method = RequestMethod.POST)
	public String getOffer(@Valid Offer offer, BindingResult result, ModelMap model) {
		Offer searchOffer = offerService.findByOfferName(offer.getOfferName());
		List<Offer> offers = new ArrayList<>();
		offers.add(searchOffer);
		model.addAttribute("offer", offer);
		model.addAttribute("offers", offers);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "offerlist";
	}

	/**
	 * This method will provide the page to update an offer.
	 */
	@RequestMapping(value = { "/edit-offer-{offerName}" }, method = RequestMethod.GET)
	public String editOffer(@PathVariable String offerName, ModelMap model) {
		Offer offer = offerService.findByOfferName(offerName);
		model.addAttribute("offer", offer);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "offer";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating offer in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-offer-{offerName}" }, method = RequestMethod.POST)
	public String updateUser(@Valid Offer offer, BindingResult result, ModelMap model, @PathVariable String offerName) {

		if (result.hasErrors()) {
			return "offer";
		}

		offerService.saveOrUpdateOffer(offer);

		model.addAttribute("success", "Offer " + offer.getOfferName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "offersuccess";
	}

	/**
	 * Delete an offer
	 */
	@RequestMapping(value = { "/delete-offer-{offerName}" }, method = RequestMethod.GET)
	public String deleteOffer(@PathVariable String offerName) {
		offerService.deleteOfferByOfferName(offerName);
		return "redirect:/offers";
	}

	/**
	 * List all the Offers
	 */
	@RequestMapping(value = { "/offers" }, method = RequestMethod.GET)
	public String listOffers(ModelMap model) {
		Offer offer = new Offer();
		model.addAttribute("offer", offer);
		List<Offer> offers = offerService.findAllOffers();
		model.addAttribute("offers", offers);
		model.addAttribute("loggedinuser", getPrincipal());
		return "offerlist";
	}

	/**
	 * This method will provide UserProfile list to views and initialize a admin
	 * user for login and initialize a set of offers
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		initializeUserAndProfiles();
		initializeOffersData();
		List<UserProfile> userProfiles = userProfileService.findAll();
		return userProfiles;
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to Main page (User
	 * Info page)
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/userInfo";
		}
	}

	/**
	 * This method handles logout requests. Toggle the handlers if you are
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in],
	 * else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	/**
	 * Initialize Admin user to be used and the you can create more than user
	 */
	private void initializeUserAndProfiles() {
		User user = new User();
		user.setUserName(environment.getRequiredProperty("intial.admin.userName"));
		user.setFirstName(environment.getRequiredProperty("intial.admin.firstName"));
		user.setPassword(environment.getRequiredProperty("intial.admin.password"));
		user.setLastName(environment.getRequiredProperty("intial.admin.lastName"));
		user.setEmail(environment.getRequiredProperty("intial.admin.email"));
		user.setDepartment(environment.getRequiredProperty("intial.admin.department"));
		user.setGrade(environment.getRequiredProperty("intial.admin.grade"));
		user.setDirectManager(environment.getRequiredProperty("intial.admin.manager"));

		UserProfile userProfile = new UserProfile();
		userProfile.setType(UserProfileType.HRADMIN.getUserProfileType());
		UserProfile employeeUserProfile = new UserProfile();
		employeeUserProfile.setType(UserProfileType.EMPLOYEE.getUserProfileType());

		user.setUserProfile(userProfile);
		userProfileService.saveUserProfile(employeeUserProfile);
		userService.saveUser(user);

	}

	/**
	 * Initialize set of offers and then if your logged-in user is admin will be
	 * able to manage the offers(add,delete,edit)
	 */
	private void initializeOffersData() {
		Offer firstOffer = new Offer();
		firstOffer.setOfferName(environment.getRequiredProperty("intial.offer1.offerName"));
		firstOffer.setOfferDescription(environment.getRequiredProperty("intial.offer1.offerDescription"));

		Offer secondOffer = new Offer();
		secondOffer.setOfferName(environment.getRequiredProperty("intial.offer2.offerName"));
		secondOffer.setOfferDescription(environment.getRequiredProperty("intial.offer2.offerDescription"));

		offerService.saveOrUpdateOffer(firstOffer);
		offerService.saveOrUpdateOffer(secondOffer);
	}

}