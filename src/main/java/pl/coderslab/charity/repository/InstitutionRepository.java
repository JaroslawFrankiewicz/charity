package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entity.Institution;

import java.util.List;


public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query("select i from Institution i order by i.id desc ")
    List<Institution> findAllOderByIdDesc();

}
