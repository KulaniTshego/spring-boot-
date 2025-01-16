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

    @Autowired
    private WasteCategoryService service;

    @GetMapping
    public List<WasteCategory> getAllWasteCategories() {
        return service.getAllWasteCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getWasteCategory(@PathVariable Long id) {
        Optional<WasteCategory> wasteCategory = service.getWasteCategory(id);
        return wasteCategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WasteCategory> createWasteCategory(@Valid @RequestBody WasteCategory wasteCategory) {
        WasteCategory savedWasteCategory = service.saveWasteCategory(wasteCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWasteCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategory> updateWasteCategory(@PathVariable Long id, @Valid @RequestBody WasteCategory wasteCategory) {
        if (service.getWasteCategory(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        wasteCategory.setId(id);
        WasteCategory updatedWasteCategory = service.saveWasteCategory(wasteCategory);
        return ResponseEntity.ok(updatedWasteCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteCategory(@PathVariable Long id) {
        if (service.getWasteCategory(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteWasteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
