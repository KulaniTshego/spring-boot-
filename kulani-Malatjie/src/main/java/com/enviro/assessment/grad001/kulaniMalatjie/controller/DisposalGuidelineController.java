package com.enviro.assessment.grad001.kulaniMalatjie.controller;

import com.enviro.assessment.grad001.kulaniMalatjie.model.DisposalGuideline;
import com.enviro.assessment.grad001.kulaniMalatjie.service.DisposalGuidelineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelineController {

    private final DisposalGuidelineService disposalGuidelineService;

    @Autowired
    public DisposalGuidelineController(DisposalGuidelineService disposalGuidelineService) {
        this.disposalGuidelineService = disposalGuidelineService;
    }

    // Get all disposal guidelines
    @GetMapping
    public List<DisposalGuideline> getAllDisposalGuidelines() {
        return disposalGuidelineService.getAllDisposalGuidelines();
    }

    // Get a disposal guideline by ID
    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuideline> getDisposalGuidelineById(@PathVariable Long id) {
        Optional<DisposalGuideline> disposalGuideline = disposalGuidelineService.getDisposalGuidelineById(id);
        return disposalGuideline.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new disposal guideline
    @PostMapping
    public ResponseEntity<DisposalGuideline> createDisposalGuideline(@Valid @RequestBody DisposalGuideline disposalGuideline) {
        DisposalGuideline createdDisposalGuideline = disposalGuidelineService.saveDisposalGuideline(disposalGuideline);
        return new ResponseEntity<>(createdDisposalGuideline, HttpStatus.CREATED);
    }

    // Delete a disposal guideline by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisposalGuideline(@PathVariable Long id) {
        disposalGuidelineService.deleteDisposalGuideline(id);
        return ResponseEntity.noContent().build();
    }
}
