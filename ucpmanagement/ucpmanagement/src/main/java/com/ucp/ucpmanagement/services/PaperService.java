package com.ucp.ucpmanagement.services;

import com.ucp.ucpmanagement.entities.*;
import com.ucp.ucpmanagement.repositories.PaperRepository;
import com.ucp.ucpmanagement.repositories.ConferenceRepository;
import com.ucp.ucpmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new paper
    @Transactional
    public Paper createPaper(Paper paper, Long conferenceId, String username) throws Exception {
        // Find the conference
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new Exception("Conference not found"));

        // Find the user (author)
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("Author not found"));

        // Ensure the paper title is unique in the conference
        if (paperRepository.existsByTitleAndConference(paper.getTitle(), conference)) {
            throw new Exception("A paper with this title already exists in the conference.");
        }

        // Set up the paper data
        paper.setConference(conference);
        paper.setAuthor(author); // Set the requestor as the author
        paper.setCreatedDate(new java.util.Date());
        paper.setState(PaperState.CREATED); // Assuming there's a CREATED state

        return paperRepository.save(paper);
    }

    // Update a paper
    @Transactional
    public Paper updatePaper(Long paperId, Paper updatedPaper) throws Exception {
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new Exception("Paper not found"));

        paper.setTitle(updatedPaper.getTitle());
        paper.setAbstractText(updatedPaper.getAbstractText());
        paper.setContent(updatedPaper.getContent());

        return paperRepository.save(paper);
    }

    // Add a co-author
    @Transactional
    public Paper addCoAuthor(Long paperId, Long coAuthorId) throws Exception {
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new Exception("Paper not found"));

        User coAuthor = userRepository.findById(coAuthorId)
                .orElseThrow(() -> new Exception("Co-author not found"));

        Set<User> coAuthors = paper.getCoAuthors();
        coAuthors.add(coAuthor);
        paper.setCoAuthors(coAuthors);

        return paperRepository.save(paper);
    }

    // Submit a paper
    @Transactional
    public Paper submitPaper(Long paperId) throws Exception {
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new Exception("Paper not found"));

        Conference conference = paper.getConference();
        if (!conference.getState().equals(ConferenceState.SUBMISSION)) {
            throw new Exception("Conference is not in submission phase.");
        }

        if (paper.getContent() == null || paper.getContent().isEmpty()) {
            throw new Exception("Paper content cannot be empty.");
        }

        paper.setState(PaperState.SUBMITTED);
        return paperRepository.save(paper);
    }

    // Assign a reviewer
    @Transactional
    public Paper assignReviewer(Long paperId, Long reviewerId) throws Exception {
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new Exception("Paper not found"));

        Conference conference = paper.getConference();
        if (!conference.getState().equals(ConferenceState.ASSIGNMENT)) {
            throw new Exception("Conference is not in assignment phase.");
        }

        User reviewer = userRepository.findById(reviewerId)
                .orElseThrow(() -> new Exception("Reviewer not found"));

        if (!reviewer.hasRoleForConference("PC_MEMBER", conference)) {
            throw new Exception("User is not a member of the PC.");
        }

        if (paper.getReviewers().size() >= 2) {
            throw new Exception("The paper already has the maximum number of reviewers.");
        }

        paper.getReviewers().add(reviewer);
        return paperRepository.save(paper);
    }
}