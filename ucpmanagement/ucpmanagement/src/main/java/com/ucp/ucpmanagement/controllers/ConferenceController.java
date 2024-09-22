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
    @PreAuthorize("hasRole('PC_CHAIR') or hasRole('ADMIN')")
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

    // Search for conferences by name
    @GetMapping("/search")
    public ResponseEntity<List<Conference>> searchConferencesByName(@RequestParam String name) {
        List<Conference> conferences = conferenceService.searchConferencesByName(name);
        return ResponseEntity.ok(conferences);
    }

    // Update a conference
    // Only PC Chair or Admin can create a new conference
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
}