package com.example.votingbackend.service;

import com.example.votingbackend.model.Candidate;
import com.example.votingbackend.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
