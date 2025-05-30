package com.kevocodes.pnccontrollers.repositories;

import com.kevocodes.pnccontrollers.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
