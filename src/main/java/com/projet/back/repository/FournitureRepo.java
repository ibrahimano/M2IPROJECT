package com.projet.back.repository;

import com.projet.back.models.Fourniture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FournitureRepo extends JpaRepository<Fourniture, Long> {
    List<Fourniture> findByTypeContaining(String type);

    List<Fourniture> findAll();

    Fourniture findById(long id);

    Fourniture save(Fourniture fourniture);

    void deleteById(long id);

    List<Fourniture> searchByType(String type);
}
