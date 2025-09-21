package com.example.votingbackend.service;

import com.example.votingbackend.model.Vote;
import com.example.votingbackend.repository.VoteRepository;
import com.example.votingbackend.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoterRepository voterRepository;

    public Vote castVote(Vote vote) throws Exception {
        // Ensure voter exists
        if (!voterRepository.existsById(vote.getVoterId())) {
            throw new Exception("Invalid voter ID. Voter does not exist.");
        }

        // Ensure voter hasn't already voted for this candidate
        if (voteRepository.existsByVoterIdAndCandidateId(vote.getVoterId(), vote.getCandidateId())) {
            throw new Exception("You have already voted.");
        }

        // Set the vote timestamp
        vote.setVoteDate(LocalDateTime.now());

        // Save the vote
        return voteRepository.save(vote);
    }
}
