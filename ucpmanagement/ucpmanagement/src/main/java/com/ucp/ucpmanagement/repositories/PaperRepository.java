package com.ucp.ucpmanagement.repositories;

import com.ucp.ucpmanagement.entities.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findByTitleContaining(String title);  // Search papers by title
}
