package com.example.projektportfolio;

import com.example.projektportfolio.models.PageInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageInfoRepository extends CrudRepository<PageInfo, Integer> {
    List<PageInfo> findAll();
}
