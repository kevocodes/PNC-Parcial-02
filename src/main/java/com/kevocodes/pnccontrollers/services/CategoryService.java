package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.dtos.SaveBookDTO;
import com.kevocodes.pnccontrollers.domain.dtos.SaveCategoryDTO;
import com.kevocodes.pnccontrollers.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category findCategoryById(String id);
    void deleteById(String id);
    void save(SaveCategoryDTO info);
}
