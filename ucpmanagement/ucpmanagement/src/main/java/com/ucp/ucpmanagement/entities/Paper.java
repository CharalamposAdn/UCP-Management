package com.ucp.ucpmanagement.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String abstractText;

    private String content;  // PDF, TeX, or other file types

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

    // Constructors, Getters, Setters
    public Paper() {
    }

    public Paper(String title, String abstractText, String content, PaperState state, Date createdDate) {
        this.title = title;
        this.abstractText = abstractText;
        this.content = content;
        this.state = state;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PaperState getState() {
        return state;
    }

    public void setState(PaperState state) {
        this.state = state;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<User> getCoAuthors() {
        return coAuthors;
    }

    public void setCoAuthors(Set<User> coAuthors) {
        this.coAuthors = coAuthors;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Set<User> getReviewers() {
        return reviewers;
    }

    public void setReviewers(Set<User> reviewers) {
        this.reviewers = reviewers;
    }
}