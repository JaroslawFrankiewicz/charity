package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;


public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT sum(quantity) as quantitySum FROM donation",nativeQuery = true)
    int findDonationsQuantity();

    @Query(value = "SELECT COUNT(id) FROM donation",nativeQuery = true)
    int findDonationsCount();
}
