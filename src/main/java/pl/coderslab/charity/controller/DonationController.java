package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DonationController {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public DonationController(DonationRepository donationRepository, CategoryRepository categoryRepository,
                              InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }
    @ModelAttribute("donation")
    public Donation donationForm() {
        return new Donation();
    }

    @ModelAttribute("institutions")
    public List<Institution> addInstitutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryRepository.findAll();
    }


    @GetMapping("/form")
    public String addDonation() {
        return "form";
    }

    @PostMapping("/form")
    String donateForm(@Valid @ModelAttribute("donation") Donation donation, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "form";
        donationRepository.save(donation);
        return "form-confirmation";
    }
}
