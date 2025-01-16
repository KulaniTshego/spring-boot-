package com.enviro.assessment.grad001.kulaniMalatjie.controller;

import com.enviro.assessment.grad001.kulaniMalatjie.model.WasteCategory;
import com.enviro.assessment.grad001.kulaniMalatjie.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {

    private final WasteCategoryService wasteCategoryService;

    @Autowired
    public WasteCategoryController(WasteCategoryService wasteCategoryService) {
        this.wasteCategoryService = wasteCategoryService;
    }

    @GetMapping
    public List<WasteCategory> getAllWasteCategories() {
        return wasteCategoryService.getAllWasteCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getWasteCategoryById(@PathVariable Long id) {
        Optional<WasteCategory> wasteCategory = wasteCategoryService.getWasteCategoryById(id);
        return wasteCategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WasteCategory> createWasteCategory(@Valid @RequestBody WasteCategory wasteCategory) {
        WasteCategory createdWasteCategory = wasteCategoryService.saveWasteCategory(wasteCategory);
        return new ResponseEntity<>(createdWasteCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteCategory(@PathVariable Long id) {
        wasteCategoryService.deleteWasteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
