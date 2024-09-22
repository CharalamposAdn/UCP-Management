package com.ucp.ucpmanagement.services;

import com.ucp.ucpmanagement.entities.Conference;
import com.ucp.ucpmanagement.entities.ConferenceState;
import com.ucp.ucpmanagement.entities.User;
import com.ucp.ucpmanagement.repositories.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    public Conference createConference(Conference conference) {
        conference.setState(ConferenceState.CREATED);
        return conferenceRepository.save(conference);
    }

    public Optional<Conference> getConferenceById(Long id) {
        return conferenceRepository.findById(id);
    }

    public List<Conference> searchConferencesByName(String name) {
        return conferenceRepository.findByNameContaining(name);
    }

    public Conference updateConference(Long id, Conference conferenceDetails) {
        Optional<Conference> optionalConference = conferenceRepository.findById(id);
        if (optionalConference.isPresent()) {
            Conference conference = optionalConference.get();
            conference.setName(conferenceDetails.getName());
            conference.setDescription(conferenceDetails.getDescription());
            conference.setState(conferenceDetails.getState());
            return conferenceRepository.save(conference);
        } else {
            throw new RuntimeException("Conference not found with id " + id);
        }
    }

    public void deleteConference(Long id) {
        conferenceRepository.deleteById(id);
    }

    //add PC Chair
    public Conference addPCChair(Long id, Long userId) {
        Optional<Conference> optionalConference = conferenceRepository.findById(id);
        if (optionalConference.isPresent()) {
            Conference conference = optionalConference.get();
            // Assume a function to fetch user by userId and add to PC Chairs
            User user = fetchUserById(userId); // Implement this logic
            conference.getPcChairs().add(user);
            return conferenceRepository.save(conference);
        } else {
            throw new RuntimeException("Conference not found with id " + id);
        }
    }

    //add PC Member
    public Conference addPCMember(Long id, Long userId) {
        Optional<Conference> optionalConference = conferenceRepository.findById(id);
        if (optionalConference.isPresent()) {
            Conference conference = optionalConference.get();
            // Assume a function to fetch user by userId and add to PC Members
            User user = fetchUserById(userId); // Implement this logic
            conference.getPcMembers().add(user);
            return conferenceRepository.save(conference);
        } else {
            throw new RuntimeException("Conference not found with id " + id);
        }
    }

    //start Submission Phase
    public Conference startSubmission(Long id) {
        return updateConferenceState(id, ConferenceState.SUBMISSION);
    }

    //start Reviewer Assignment Phase
    public Conference startReviewerAssignment(Long id) {
        return updateConferenceState(id, ConferenceState.ASSIGNMENT);
    }

    //start Review Phase
    public Conference startReview(Long id) {
        return updateConferenceState(id, ConferenceState.REVIEW);
    }

    //start Decision Phase
    public Conference startDecision(Long id) {
        return updateConferenceState(id, ConferenceState.DECISION);
    }

    //start Final Submission Phase
    public Conference startFinalSubmission(Long id) {
        return updateConferenceState(id, ConferenceState.FINAL_SUBMISSION);
    }

    //end Conference
    public Conference endConference(Long id) {
        return updateConferenceState(id, ConferenceState.FINAL);
    }

    //helper to update conference state
    private Conference updateConferenceState(Long id, ConferenceState newState) {
        Optional<Conference> optionalConference = conferenceRepository.findById(id);
        if (optionalConference.isPresent()) {
            Conference conference = optionalConference.get();
            conference.setState(newState);
            return conferenceRepository.save(conference);
        } else {
            throw new RuntimeException("Conference not found with id " + id);
        }
    }

    //dummy method to fetch a User by ID (you would implement this based on your system)
    private User fetchUserById(Long userId) {
    
        return new User(); 
    }
}
