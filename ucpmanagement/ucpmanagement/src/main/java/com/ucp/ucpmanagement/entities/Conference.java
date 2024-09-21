package com.ucp.ucpmanagement.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private ConferenceState state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // Relationship with User (PC Chairs)
    @ManyToMany
    @JoinTable(
        name = "conference_pc_chairs",
        joinColumns = @JoinColumn(name = "conference_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> pcChairs = new HashSet<>();

    // Relationship with User (PC Members)
    @ManyToMany
    @JoinTable(
        name = "conference_pc_members",
        joinColumns = @JoinColumn(name = "conference_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> pcMembers = new HashSet<>();

    // Relationship with Papers
    @OneToMany(mappedBy = "conference")
    private Set<Paper> papers = new HashSet<>();

    // Constructors, Getters, Setters
    public Conference() {
    }

    public Conference(String name, String description, ConferenceState state, Date createdDate) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ConferenceState getState() {
        return state;
    }

    public void setState(ConferenceState state) {
        this.state = state;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<User> getPcChairs() {
        return pcChairs;
    }

    public void setPcChairs(Set<User> pcChairs) {
        this.pcChairs = pcChairs;
    }

    public Set<User> getPcMembers() {
        return pcMembers;
    }

    public void setPcMembers(Set<User> pcMembers) {
        this.pcMembers = pcMembers;
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }
}
