package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.services.UserService;
import pl.coderslab.charity.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

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
        return "login";

    }

    // Logowanie

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", new User());
        if (principal != null) {
            return "redirect:/user/user_index";
        }
        return "login";
    }
    @GetMapping("/Login")
    public String login(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/user/admin_index";
        } else if (request.isUserInRole("ROLE_USER")) {
            return "redirect:/user/user_index";

        }
        return "redirect:/index";
    }

    @GetMapping("/user/index")
    public String adminLogin(Model model) {
        model.addAttribute("admin", new User());
        return "user/user_index";
    }

    @PostMapping({"/user/logout"})
    public String logout(@ModelAttribute User user) {
        userService.addNewUser(user);
        return "index";
    }


    @ModelAttribute("institutions")
    List<Institution> institutionList() {
        return institutionRepository.findAll();
    }

    @GetMapping("/user")
    public String homeAction(Model model){
        int numOfSacks = donationRepository.findDonationsQuantity();
        model.addAttribute("numOfSacks", numOfSacks);

        int numOfGifts = donationRepository.findDonationsCount();
        model.addAttribute("numOfGifts", numOfGifts);
        return "user/user_index";
    }
}