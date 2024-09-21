package com.ucp.ucpmanagement.services;

import com.ucp.ucpmanagement.entities.Conference;
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
}