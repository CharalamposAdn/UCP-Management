package com.ucp.ucpmanagement.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    @ManyToMany(mappedBy = "pcChairs")
    private Set<Conference> conferencesAsPcChair = new HashSet<>();

    @ManyToMany(mappedBy = "pcMembers")
    private Set<Conference> conferencesAsPcMember = new HashSet<>();

    @OneToMany(mappedBy = "author")
    private Set<Paper> authoredPapers = new HashSet<>();

    @ManyToMany(mappedBy = "reviewers")
    private Set<Paper> papersToReview = new HashSet<>();

     // Check if the user has a specific role (e.g., PC_MEMBER, PC_CHAIR) for a given conference
     public boolean hasRoleForConference(String role, Conference conference) {
        switch (role) {
            case "PC_CHAIR":
                return conferencesAsPcChair.contains(conference);
            case "PC_MEMBER":
                return conferencesAsPcMember.contains(conference);
            case "AUTHOR":
                return authoredPapers.stream().anyMatch(paper -> paper.getConference().equals(conference));
            default:
                return false;
        }
    }

    // Add a role for a specific conference
    public void addRoleForConference(String role, Conference conference) {
        switch (role) {
            case "PC_CHAIR":
                conferencesAsPcChair.add(conference);
                break;
            case "PC_MEMBER":
                conferencesAsPcMember.add(conference);
                break;
            case "AUTHOR":
                authoredPapers.add(new Paper(this, conference));
                break;
        }
    }



}