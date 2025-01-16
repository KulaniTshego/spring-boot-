package com.enviro.assessment.grad001.kulaniMalatjie.controller;

import com.enviro.assessment.grad001.kulaniMalatjie.model.RecyclingTip;
import com.enviro.assessment.grad001.kulaniMalatjie.service.RecyclingTipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recycling-tips")
public class RecyclingTipController {

    private final RecyclingTipService recyclingTipService;

    @Autowired
    public RecyclingTipController(RecyclingTipService recyclingTipService) {
        this.recyclingTipService = recyclingTipService;
    }

    // Get all recycling tips
    @GetMapping
    public List<RecyclingTip> getAllRecyclingTips() {
        return recyclingTipService.getAllRecyclingTips();
    }

    // Get a recycling tip by ID
    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTip> getRecyclingTipById(@PathVariable Long id) {
        Optional<RecyclingTip> recyclingTip = recyclingTipService.getRecyclingTipById(id);
        return recyclingTip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new recycling tip
    @PostMapping
    public ResponseEntity<RecyclingTip> createRecyclingTip(@Valid @RequestBody RecyclingTip recyclingTip) {
        RecyclingTip createdRecyclingTip = recyclingTipService.saveRecyclingTip(recyclingTip);
        return new ResponseEntity<>(createdRecyclingTip, HttpStatus.CREATED);
    }

    // Delete a recycling tip by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecyclingTip(@PathVariable Long id) {
        recyclingTipService.deleteRecyclingTip(id);
        return ResponseEntity.noContent().build();
    }
}
