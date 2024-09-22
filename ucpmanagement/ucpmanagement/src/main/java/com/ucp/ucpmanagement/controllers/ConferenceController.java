package com.ucp.ucpmanagement.controllers;

import com.ucp.ucpmanagement.entities.Conference;
import com.ucp.ucpmanagement.services.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    // Create a new conference
    // Only PC Chair or Admin can create a new conference
    @PreAuthorize("hasRole('ADMIN') or hasRole('PC_CHAIR')")
    @PostMapping
    public ResponseEntity<Conference> createConference(@RequestBody Conference conference) {
        Conference createdConference = conferenceService.createConference(conference);
        return ResponseEntity.ok(createdConference);
    }

    // Get a conference by ID
    // Anyone with PC Chair, PC Member, or Admin role can view conferences
    @PreAuthorize("hasAnyRole('PC_CHAIR', 'PC_MEMBER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Conference>> getConferenceById(@PathVariable Long id) {
        Optional<Conference> conference = conferenceService.getConferenceById(id);
        return ResponseEntity.ok(conference);
    }

    // Search for conferences by name (available to all roles)
    @PreAuthorize("hasAnyRole('PC_CHAIR', 'PC_MEMBER', 'ADMIN', 'USER')")
    @GetMapping("/search")
    public ResponseEntity<List<Conference>> searchConferencesByName(@RequestParam String name) {
        List<Conference> conferences = conferenceService.searchConferencesByName(name);
        return ResponseEntity.ok(conferences);
    }

    // Update a conference
    // Only PC Chair or Admin can update a conference
    @PreAuthorize("hasRole('PC_CHAIR') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Conference> updateConference(@PathVariable Long id, @RequestBody Conference conferenceDetails) {
        Conference updatedConference = conferenceService.updateConference(id, conferenceDetails);
        return ResponseEntity.ok(updatedConference);
    }

    // Delete a conference
    // Only PC Chair or Admin can delete a conference
    @PreAuthorize("hasRole('PC_CHAIR') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    // Add a PC Chair to the conference
    // Only Admins can assign PC Chairs
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/pc-chair")
    public ResponseEntity<Conference> addPCChair(@PathVariable Long id, @RequestParam Long userId) {
        Conference updatedConference = conferenceService.addPCChair(id, userId);
        return ResponseEntity.ok(updatedConference);
    }

    // Add a PC Member to the conference
    // Only Admins or PC Chairs can assign PC Members
    @PreAuthorize("hasRole('ADMIN') or hasRole('PC_CHAIR')")
    @PostMapping("/{id}/pc-member")
    public ResponseEntity<Conference> addPCMember(@PathVariable Long id, @RequestParam Long userId) {
        Conference updatedConference = conferenceService.addPCMember(id, userId);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Submission Phase
    // Only PC Chair can start the submission phase
    @PreAuthorize("hasRole('PC_CHAIR')")
    @PostMapping("/{id}/start-submission")
    public ResponseEntity<Conference> startSubmission(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startSubmission(id);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Reviewer Assignment Phase
    // Only PC Chair can start the reviewer assignment phase
    @PreAuthorize("hasRole('PC_CHAIR')")
    @PostMapping("/{id}/start-assignment")
    public ResponseEntity<Conference> startReviewerAssignment(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startReviewerAssignment(id);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Review Phase
    // Only PC Chair can start the review phase
    @PreAuthorize("hasRole('PC_CHAIR')")
    @PostMapping("/{id}/start-review")
    public ResponseEntity<Conference> startReview(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startReview(id);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Decision Making Phase
    // Only PC Chair can start the decision-making phase
    @PreAuthorize("hasRole('PC_CHAIR')")
    @PostMapping("/{id}/start-decision")
    public ResponseEntity<Conference> startDecision(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startDecision(id);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Final Submission Phase
    // Only PC Chair can start the final submission phase
    @PreAuthorize("hasRole('PC_CHAIR')")
    @PostMapping("/{id}/start-final-submission")
    public ResponseEntity<Conference> startFinalSubmission(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startFinalSubmission(id);
        return ResponseEntity.ok(updatedConference);
    }

    // End the Conference
    // Only PC Chair or Admin can end the conference
    @PreAuthorize("hasRole('PC_CHAIR') or hasRole('ADMIN')")
    @PostMapping("/{id}/end-conference")
    public ResponseEntity<Conference> endConference(@PathVariable Long id) {
        Conference updatedConference = conferenceService.endConference(id);
        return ResponseEntity.ok(updatedConference);
    }
}