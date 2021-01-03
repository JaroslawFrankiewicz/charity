package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    private final HttpSession session;

    private final UserService userService;

    public UserController(HttpSession session, UserService userService) {
        this.session = session;
        this.userService = userService;
    }

    //Rejestracja użytkownika

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
        return "redirect:/admin/index";

    }

    // Logowanie

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", new User());
        if (principal != null) {
            return "redirect:/admin/index";
        }
        return "login";
    }


    @PostMapping("/login")
    public String postLogin(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        if (session.getAttribute("user") != null) {
            return "redirect:/admin/index";
        }
        String username = user.getUsername();
        String password = user.getPassword();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("emptyField", true);
            return "login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("wrongData", true);
            return "login";
        }

        user = userService.findByUsername(username);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            model.addAttribute("wrongData", true);
        } else {
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(60 * 15);
            model.addAttribute("success", true);
        }
        return "login";
    }

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

    @GetMapping("/admin")
    @ResponseBody
    public String admin(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "Hello " + entityUser.getUsername();
    }
}
