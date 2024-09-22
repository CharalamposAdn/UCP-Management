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


}