package com.ucp.ucpmanagement.repositories;

import com.ucp.ucpmanagement.entities.Conference;
import com.ucp.ucpmanagement.entities.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    boolean existsByTitleAndConference(String title, Conference conference);
    List<Paper> findByTitleContaining(String title);  // Search papers by title
}
