package com.example.springSecurityApplication.repositories;

import com.example.springSecurityApplication.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
