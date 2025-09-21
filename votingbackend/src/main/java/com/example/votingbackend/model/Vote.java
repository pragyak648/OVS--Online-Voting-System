package com.example.votingbackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to Voter
    @Column(name = "voter_id", nullable = false)
    private Long voterId;

    // Foreign key to Candidate
    @Column(name = "candidate_id", nullable = false)
    private Long candidateId;

    @Column(name = "vote_date", nullable = false)
    private LocalDateTime voteDate;

    // Constructors
    public Vote() {}

    public Vote(Long voterId, Long candidateId) {
        this.voterId = voterId;
        this.candidateId = candidateId;
        this.voteDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVoterId() { return voterId; }
    public void setVoterId(Long voterId) { this.voterId = voterId; }

    public Long getCandidateId() { return candidateId; }
    public void setCandidateId(Long candidateId) { this.candidateId = candidateId; }

    public LocalDateTime getVoteDate() { return voteDate; }
    public void setVoteDate(LocalDateTime voteDate) { this.voteDate = voteDate; }
}
