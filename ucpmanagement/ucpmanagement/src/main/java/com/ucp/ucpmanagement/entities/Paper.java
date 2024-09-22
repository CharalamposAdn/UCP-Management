package com.ucp.ucpmanagement.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Paper {

    public Paper(User user, Conference conference2) {
        //TODO Auto-generated constructor stub
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String abstractText;

    private String content;  

    @Enumerated(EnumType.STRING)
    private PaperState state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // Relationship with User (Author)
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    // Co-authors
    @ManyToMany
    @JoinTable(
        name = "paper_coauthors",
        joinColumns = @JoinColumn(name = "paper_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> coAuthors = new HashSet<>();

    // Relationship with Conference
    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

    // Reviewers
    @ManyToMany
    @JoinTable(
        name = "paper_reviewers",
        joinColumns = @JoinColumn(name = "paper_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> reviewers = new HashSet<>();

    
}