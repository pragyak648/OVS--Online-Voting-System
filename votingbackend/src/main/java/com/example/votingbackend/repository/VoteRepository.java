package com.example.votingbackend.repository;

import com.example.votingbackend.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    // Checks if voter already voted for a candidate
    boolean existsByVoterIdAndCandidateId(Long voterId, Long candidateId);
}
