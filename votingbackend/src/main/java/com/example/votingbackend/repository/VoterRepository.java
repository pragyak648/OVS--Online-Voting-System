package com.example.votingbackend.repository;

import com.example.votingbackend.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Voter findByEmail(String email);   // 👈 add this
}

