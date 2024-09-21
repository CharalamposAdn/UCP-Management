package com.ucp.ucpmanagement.repositories;

import com.ucp.ucpmanagement.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    List<Conference> findByNameContaining(String name); // For conference search by name
}