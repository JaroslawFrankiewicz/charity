package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;
import pl.coderslab.charity.validator.UserValidator;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
//public class UserController {
//    private final UserService userService;
//
//    private final SecurityService securityService;
//
//    private final UserValidator userValidator;
//
//    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
//        this.userService = userService;
//        this.securityService = securityService;
//        this.userValidator = userValidator;
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//
//        return "register";
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
//
//        userService.save(userForm);
//
//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//        return "redirect:/index";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login";
//    }
//
//    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
//    public String welcome(Model model) {
//        return "index";
//    }
//}

public class UserController {

//    private final HttpSession session;

    private final UserService userService;

    private final UserValidator userValidator;

    private final DonationRepository donationRepository;

    private final InstitutionRepository institutionRepository;

    public UserController(UserService userService, UserValidator userValidator, DonationRepository donationRepository, InstitutionRepository institutionRepository) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
    }

//    Rejestracja użytkownika

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping({"/register"})
    public String registrationUser(@ModelAttribute("newUser") @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.addNewUser(user);
        return "redirect:/login";

    }

    // Logowanie

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", new User());
        if (principal != null) {
            return "redirect:/admin/admin_index";
        }
        return "login";
    }


//    @PostMapping("/login")
//    public String postLogin(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
//        if (session.getAttribute("user") != null) {
//            return "redirect:/admin/index";
//        }
//        String username = user.getUsername();
//        String password = user.getPassword();
//
//        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
//            model.addAttribute("emptyField", true);
//            return "login";
//        }
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("wrongData", true);
//            return "login";
//        }
//
//        user = userService.findByUsername(username);
//
//        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
//            model.addAttribute("wrongData", true);
//        } else {
//            session.setAttribute("user", user);
//            session.setMaxInactiveInterval(60 * 15);
//            model.addAttribute("success", true);
//        }
//        return "login";
//    }

    @GetMapping("/admin/index")
    public String adminLogin(Model model) {
        model.addAttribute("admin", new User());
        return "admin/admin_index";
    }

    @PostMapping({"/admin/logout"})
    public String logout(@ModelAttribute User user) {
        userService.addNewUser(user);
        return "index";
    }

//    @GetMapping("/admin")
//    @ResponseBody
//    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
//        User entityUser = customUser.getUser();
//        return "Hello " + entityUser.getUsername();
//    }

    @ModelAttribute("institutions")
    List<Institution> institutionList() {
        return institutionRepository.findAll();
    }

    @GetMapping("/admin")
    public String homeAction(Model model){
        int numOfSacks = donationRepository.findDonationsQuantity();
        model.addAttribute("numOfSacks", numOfSacks);

        int numOfGifts = donationRepository.findDonationsCount();
        model.addAttribute("numOfGifts", numOfGifts);
        return "admin/admin_index";
    }
}
