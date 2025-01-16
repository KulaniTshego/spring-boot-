package com.enviro.assessment.grad001.kulaniMalatjie.service;

import com.enviro.assessment.grad001.kulaniMalatjie.model.WasteCategory;
import com.enviro.assessment.grad001.kulaniMalatjie.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {

    @Autowired
    private WasteCategoryRepository repository;

    public List<WasteCategory> getAllWasteCategories() {
        return repository.findAll();
    }

    public Optional<WasteCategory> getWasteCategory(Long id) {
        return repository.findById(id);
    }

    public WasteCategory saveWasteCategory(WasteCategory wasteCategory) {
        return repository.save(wasteCategory);
    }

    public void deleteWasteCategory(Long id) {
        repository.deleteById(id);
    }
}

