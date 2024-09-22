package com.ucp.ucpmanagement.controllers;

import com.ucp.ucpmanagement.entities.Paper;
import com.ucp.ucpmanagement.services.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/papers")
public class PaperController {

    @Autowired
    private PaperService paperService;

    // Create a new paper
    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping("/conference/{conferenceId}")
    public ResponseEntity<Paper> createPaper(@PathVariable Long conferenceId, @RequestBody Paper paper, @RequestParam String username) {
        try {
            Paper createdPaper = paperService.createPaper(paper, conferenceId, username);
            return ResponseEntity.ok(createdPaper);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Update a paper
    @PreAuthorize("hasRole('AUTHOR')")
    @PutMapping("/{paperId}")
    public ResponseEntity<Paper> updatePaper(@PathVariable Long paperId, @RequestBody Paper updatedPaper) {
        try {
            Paper updated = paperService.updatePaper(paperId, updatedPaper);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Add a co-author
    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping("/{paperId}/add-coauthor")
    public ResponseEntity<Paper> addCoAuthor(@PathVariable Long paperId, @RequestParam Long coAuthorId) {
        try {
            Paper updatedPaper = paperService.addCoAuthor(paperId, coAuthorId);
            return ResponseEntity.ok(updatedPaper);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Submit a paper
    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping("/{paperId}/submit")
    public ResponseEntity<Paper> submitPaper(@PathVariable Long paperId) {
        try {
            Paper submittedPaper = paperService.submitPaper(paperId);
            return ResponseEntity.ok(submittedPaper);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Assign a reviewer
    @PreAuthorize("hasRole('PC_CHAIR')")
    @PostMapping("/{paperId}/assign-reviewer")
    public ResponseEntity<Paper> assignReviewer(@PathVariable Long paperId, @RequestParam Long reviewerId) {
        try {
            Paper updatedPaper = paperService.assignReviewer(paperId, reviewerId);
            return ResponseEntity.ok(updatedPaper);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
