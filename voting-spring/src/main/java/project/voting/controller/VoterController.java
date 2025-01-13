package project.voting.controller;

import project.voting.entity.Voter;
import project.voting.service.VoterService;
import project.voting.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/voters")
public class VoterController {

    @Autowired
    private VoterService voterService;

    @Autowired
    private JwtTokenService jwtTokenService;

    // Register a voter
    @PostMapping("/register")
    public ResponseEntity<?> registerVoter(@RequestBody Voter voter) {
        // Check if email already exists
        Optional<Voter> existingVoter = voterService.findByEmail(voter.getEmail());
        if (existingVoter.isPresent()) {
            return ResponseEntity.status(409).body("Email is already registered."); // Conflict
        }
        
        Voter registeredVoter = voterService.registerVoter(voter);
        return ResponseEntity.status(201).body(registeredVoter); // Created
    }

    // Login (Authenticate voter and issue JWT token)
    @PostMapping("/login")
    public ResponseEntity<?> loginVoter(@RequestBody Voter voter) {
        Optional<Voter> existingVoter = voterService.findByEmail(voter.getEmail());
        
        if (existingVoter.isEmpty() || 
            !existingVoter.get().getPassword().equals(voter.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password."); // Unauthorized
        }
        
        // Generate JWT token for authenticated voter
        String token = jwtTokenService.generateToken(existingVoter.get());
        return ResponseEntity.ok(token); // Return JWT token
    }

    // Update voter details
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVoter(
            @PathVariable Integer id, 
            @RequestBody Voter voter,
            @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Voter loggedInVoter = jwtTokenService.getVoterFromToken(token);
        // Inside the updateVoter method, just before checking the ID
        System.out.println("Logged-in voter ID: " + loggedInVoter.getId());
        System.out.println("Voter ID from URL: " + id);

        
        if (loggedInVoter == null || loggedInVoter.getId() != id) {
            return ResponseEntity.status(403).body("Access denied."); // Forbidden
        }
        
        // Ensure that the id in the path matches the id in the request body
        voter.setId(id);  // Set the correct ID from the URL into the voter object

        Voter updatedVoter = voterService.updateVoter(voter);
        return ResponseEntity.ok(updatedVoter);
    }

    // Get logged-in voter details
    @GetMapping("/me")
    public ResponseEntity<?> getLoggedInVoterDetails(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Voter loggedInVoter = jwtTokenService.getVoterFromToken(token);
        
        if (loggedInVoter == null) {
            return ResponseEntity.status(401).body("Invalid or expired token."); // Unauthorized
        }
        
        // Fetch voter details from the database to ensure freshness
        Optional<Voter> voter = voterService.findByEmail(loggedInVoter.getEmail());
        if (voter.isPresent()) {
            return ResponseEntity.ok(voter.get());
        }

        return ResponseEntity.status(404).body("Voter not found."); // Not Found
    }
}
