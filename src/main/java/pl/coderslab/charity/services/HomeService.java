package pl.coderslab.charity.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
public class HomeService {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @Autowired
    public HomeService(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    public int getNumberOfSacks() {
        return donationRepository.countAllDonations();
    }

    public int countGifts(){
        List<Donation> gifts = donationRepository.findAll();
        return gifts.size();
    }
    
    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }
}
