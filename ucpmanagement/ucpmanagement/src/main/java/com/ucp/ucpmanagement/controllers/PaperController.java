package com.ucp.ucpmanagement.controllers;

import com.ucp.ucpmanagement.entities.Paper;
import com.ucp.ucpmanagement.services.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/papers")
public class PaperController {

    @Autowired
    private PaperService paperService;

    // Create a new paper
    @PostMapping
    public ResponseEntity<Paper> createPaper(@RequestBody Paper paper) {
        Paper createdPaper = paperService.createPaper(paper);
        return ResponseEntity.ok(createdPaper);
    }

    // Get a paper by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paper>> getPaperById(@PathVariable Long id) {
        Optional<Paper> paper = paperService.getPaperById(id);
        return ResponseEntity.ok(paper);
    }

    // Update a paper
    @PutMapping("/{id}")
    public ResponseEntity<Paper> updatePaper(@PathVariable Long id, @RequestBody Paper paperDetails) {
        Paper updatedPaper = paperService.updatePaper(id, paperDetails);
        return ResponseEntity.ok(updatedPaper);
    }

    // Submit a paper (change its state to SUBMITTED)
    @PostMapping("/{id}/submit")
    public ResponseEntity<Paper> submitPaper(@PathVariable Long id) {
        Paper submittedPaper = paperService.submitPaper(id);
        return ResponseEntity.ok(submittedPaper);
    }

    // Search papers by title
    @GetMapping("/search")
    public ResponseEntity<List<Paper>> searchPapersByTitle(@RequestParam String title) {
        List<Paper> papers = paperService.searchPapersByTitle(title);
        return ResponseEntity.ok(papers);
    }

    // Delete a paper
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaper(@PathVariable Long id) {
        paperService.deletePaper(id);
        return ResponseEntity.noContent().build();
    }
}