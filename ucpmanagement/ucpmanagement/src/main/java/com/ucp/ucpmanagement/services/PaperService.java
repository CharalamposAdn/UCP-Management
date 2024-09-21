package com.ucp.ucpmanagement.services;

import com.ucp.ucpmanagement.entities.Paper;
import com.ucp.ucpmanagement.entities.PaperState;
import com.ucp.ucpmanagement.repositories.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    // Create a new paper
    public Paper createPaper(Paper paper) {
        paper.setCreatedDate(new Date());
        return paperRepository.save(paper);
    }

    // Get a paper by ID
    public Optional<Paper> getPaperById(Long id) {
        return paperRepository.findById(id);
    }

    // Update paper information
    public Paper updatePaper(Long id, Paper paperDetails) {
        Optional<Paper> optionalPaper = paperRepository.findById(id);
        if (optionalPaper.isPresent()) {
            Paper paper = optionalPaper.get();
            paper.setTitle(paperDetails.getTitle());
            paper.setAbstractText(paperDetails.getAbstractText());
            paper.setContent(paperDetails.getContent());
            paper.setCoAuthors(paperDetails.getCoAuthors());
            return paperRepository.save(paper);
        } else {
            throw new RuntimeException("Paper not found with id " + id);
        }
    }

    // Submit a paper (changes state to SUBMITTED)
    public Paper submitPaper(Long id) {
        Optional<Paper> optionalPaper = paperRepository.findById(id);
        if (optionalPaper.isPresent()) {
            Paper paper = optionalPaper.get();
            paper.setState(PaperState.SUBMITTED);
            return paperRepository.save(paper);
        } else {
            throw new RuntimeException("Paper not found with id " + id);
        }
    }

    // Search papers by title
    public List<Paper> searchPapersByTitle(String title) {
        return paperRepository.findByTitleContaining(title);
    }

    // Delete a paper
    public void deletePaper(Long id) {
        paperRepository.deleteById(id);
    }
}