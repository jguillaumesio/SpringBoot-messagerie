package s4.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.models.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Integer> {
    List<Groupe> findByName(String name);
    List<Groupe> findById(int id);
    List<Groupe> findByOrganization_Id(int id);
}


