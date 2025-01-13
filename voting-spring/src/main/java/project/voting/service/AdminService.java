package project.voting.service;

import project.voting.entity.Candidate;
import project.voting.entity.Voter;
import project.voting.repository.CandidateRepository;
import project.voting.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    // Voter Management
    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    public Voter updateVoter(Integer voterId, Voter updatedVoter) {
        Voter voter = voterRepository.findById(voterId).orElseThrow(() -> 
            new IllegalArgumentException("Voter not found")
        );
        voter.setName(updatedVoter.getName());
        voter.setEmail(updatedVoter.getEmail());
        voter.setPassword(updatedVoter.getPassword());
        voter.setIsAdmin(updatedVoter.getIsAdmin());
        return voterRepository.save(voter);
    }

    public void deleteVoter(Integer voterId) {
        voterRepository.deleteById(voterId);
    }

    // Candidate Management
    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidate(Integer candidateId, Candidate updatedCandidate) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> 
            new IllegalArgumentException("Candidate not found")
        );
        candidate.setName(updatedCandidate.getName());
        candidate.setParty(updatedCandidate.getParty());
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Integer candidateId) {
        candidateRepository.deleteById(candidateId);
    }

    // View Results
    public List<Candidate> getResults() {
        return candidateRepository.findAll(); // Vote counts are already stored in Candidate entity
    }
}
