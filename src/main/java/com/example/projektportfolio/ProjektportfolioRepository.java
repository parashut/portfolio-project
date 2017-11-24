package com.example.projektportfolio;

import com.example.projektportfolio.models.Projektportfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjektportfolioRepository extends CrudRepository<Projektportfolio, Integer>{
    List<Projektportfolio> findAll();
    Projektportfolio findById(int id);
}
