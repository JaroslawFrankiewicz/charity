package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.services.HomeService;

import java.util.List;


@Controller
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }


//    @RequestMapping("/")
//    public String homeAction(Model model){
//        return "index";
//    }

    @ModelAttribute("institutions")
    List<Institution> institutionList() {
        return homeService.getAllInstitutions();
    }

    @GetMapping("/")
    public String homeAction(Model model){
        int numOfSacks = homeService.getNumberOfSacks();
        int numOfGifts = homeService.countGifts();
        model.addAttribute("numOfSacks", numOfSacks);
        model.addAttribute("numOfGifts", numOfGifts);
        return "index";
    }

}
