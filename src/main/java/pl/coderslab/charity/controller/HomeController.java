package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @Autowired
    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @ModelAttribute("institutions")
    List<Institution> institutionList() {
        return institutionRepository.findAll();
    }

    @GetMapping("/")
    public String homeAction(Model model){
        int numOfSacks = donationRepository.findDonationsQuantity();
        model.addAttribute("numOfSacks", numOfSacks);

        int numOfGifts = donationRepository.findDonationsCount();
        model.addAttribute("numOfGifts", numOfGifts);
        return "index";
    }

}
