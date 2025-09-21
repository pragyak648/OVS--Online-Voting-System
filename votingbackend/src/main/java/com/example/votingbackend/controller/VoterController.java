package com.example.votingbackend.controller;

import com.example.votingbackend.model.LoginRequest;
import com.example.votingbackend.model.Voter;
import com.example.votingbackend.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voters")
@CrossOrigin(origins = "*")
public class VoterController {

    @Autowired
    private VoterRepository voterRepository;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Voter voter = voterRepository.findByEmail(request.getEmail());
        if (voter != null && voter.getPassword().equals(request.getPassword()) && voter.isApproved()) {
            return "Login successful";
        } else {
            return "Invalid credentials or not approved";
        }
    }

   /*
   @PostMapping("/login")
   public String login(@RequestBody Voter loginVoter) {
       try {
           Voter voter = voterRepository.findByEmailAndPasswordAndIsApprovedTrue(
                   loginVoter.getEmail(), loginVoter.getPassword()
           );
           if (voter != null) {
               return "Login successful";
           } else {
               return "Invalid credentials or you are not approved yet.";
           }
       } catch (Exception e) {
           return "Internal server error: " + e.getMessage();
       }
   }
   */
}   // ✅ this was missing
