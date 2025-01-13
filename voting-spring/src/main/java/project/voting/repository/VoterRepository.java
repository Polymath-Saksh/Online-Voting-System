package project.voting.repository;

import project.voting.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Integer> {

    Optional<Voter> findByEmail(String email); // For login
}