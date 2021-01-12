package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;


public interface DonationRepository extends JpaRepository<Donation, Long> {

//    @Query(nativeQuery = true, value = "SELECT SUM(quantity) FROM donation;")
//    Integer countBags();

//    @Query("select sum(d.quantity) from Donation d")
//    Integer TotalBags();
//
//    @Query(nativeQuery = true, value = "SELECT COUNT(*) from donation;")
//    Integer countAllDonations();

//    @Query("select count(d) from Donation d")
//    Integer TotalDonations();

    @Query(value = "SELECT sum(quantity) as quantitySum FROM donation",nativeQuery = true)
    int findDonationsQuantity();

    @Query(value = "SELECT COUNT(id) FROM donation",nativeQuery = true)
    int findDonationsCount();
}
