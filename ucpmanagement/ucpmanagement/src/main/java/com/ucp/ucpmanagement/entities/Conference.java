package com.ucp.ucpmanagement.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
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


}
