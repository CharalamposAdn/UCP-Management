package com.ucp.ucpmanagement.controllers;

import com.ucp.ucpmanagement.entities.Conference;
import com.ucp.ucpmanagement.services.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    // Create a new conference
    @PostMapping
    public ResponseEntity<Conference> createConference(@RequestBody Conference conference) {
        Conference createdConference = conferenceService.createConference(conference);
        return ResponseEntity.ok(createdConference);
    }

    // Get a conference by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Conference>> getConferenceById(@PathVariable Long id) {
        Optional<Conference> conference = conferenceService.getConferenceById(id);
        return ResponseEntity.ok(conference);
    }

    // Search for conferences by name
    @GetMapping("/search")
    public ResponseEntity<List<Conference>> searchConferencesByName(@RequestParam String name) {
        List<Conference> conferences = conferenceService.searchConferencesByName(name);
        return ResponseEntity.ok(conferences);
    }

    // Update a conference
    @PutMapping("/{id}")
    public ResponseEntity<Conference> updateConference(@PathVariable Long id, @RequestBody Conference conferenceDetails) {
        Conference updatedConference = conferenceService.updateConference(id, conferenceDetails);
        return ResponseEntity.ok(updatedConference);
    }

    // Delete a conference
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    // Add a PC Chair to the conference
    @PostMapping("/{id}/pc-chair")
    public ResponseEntity<Conference> addPCChair(@PathVariable Long id, @RequestParam Long userId) {
        Conference updatedConference = conferenceService.addPCChair(id, userId);
        return ResponseEntity.ok(updatedConference);
    }

    // Add a PC Member to the conference
    @PostMapping("/{id}/pc-member")
    public ResponseEntity<Conference> addPCMember(@PathVariable Long id, @RequestParam Long userId) {
        Conference updatedConference = conferenceService.addPCMember(id, userId);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Submission Phase
    @PostMapping("/{id}/start-submission")
    public ResponseEntity<Conference> startSubmission(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startSubmission(id);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Reviewer Assignment Phase
    @PostMapping("/{id}/start-assignment")
    public ResponseEntity<Conference> startReviewerAssignment(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startReviewerAssignment(id);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Review Phase
    @PostMapping("/{id}/start-review")
    public ResponseEntity<Conference> startReview(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startReview(id);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Decision Making Phase
    @PostMapping("/{id}/start-decision")
    public ResponseEntity<Conference> startDecision(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startDecision(id);
        return ResponseEntity.ok(updatedConference);
    }

    // Start Final Submission Phase
    @PostMapping("/{id}/start-final-submission")
    public ResponseEntity<Conference> startFinalSubmission(@PathVariable Long id) {
        Conference updatedConference = conferenceService.startFinalSubmission(id);
        return ResponseEntity.ok(updatedConference);
    }

    // End the Conference
    @PostMapping("/{id}/end-conference")
    public ResponseEntity<Conference> endConference(@PathVariable Long id) {
        Conference updatedConference = conferenceService.endConference(id);
        return ResponseEntity.ok(updatedConference);
    }
}
