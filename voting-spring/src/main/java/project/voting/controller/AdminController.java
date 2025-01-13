package project.voting.controller;

import project.voting.entity.Candidate;
import project.voting.entity.Voter;
import project.voting.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import project.voting.security.JwtTokenService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtTokenService jwtTokenService;

    // Utility method to validate admin role
    private boolean isAdmin(String token) {
        Voter voter = jwtTokenService.getVoterFromToken(token);
        return voter != null && voter.getIsAdmin(); // Ensure isAdmin() exists in the Voter entity
    }

    // Voter Management
    @GetMapping("/voters")
    public ResponseEntity<List<Voter>> getAllVoters(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtTokenService.validateToken(token) || !isAdmin(token)) {
            return ResponseEntity.status(403).body(null); // 403 Forbidden for non-admin users
        }
        return ResponseEntity.ok(adminService.getAllVoters());
    }

    @PutMapping("/voters/{id}")
    public ResponseEntity<Voter> updateVoter(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id,
            @RequestBody Voter updatedVoter) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtTokenService.validateToken(token) || !isAdmin(token)) {
            return ResponseEntity.status(403).build(); // 403 Forbidden for non-admin users
        }
        return ResponseEntity.ok(adminService.updateVoter(id, updatedVoter));
    }

    @DeleteMapping("/voters/{id}")
    public ResponseEntity<String> deleteVoter(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtTokenService.validateToken(token) || !isAdmin(token)) {
            return ResponseEntity.status(403).build(); // 403 Forbidden for non-admin users
        }
        adminService.deleteVoter(id);
        return ResponseEntity.ok("Voter deleted successfully");
    }

    // Candidate Management
    @PostMapping("/candidates")
    public ResponseEntity<Candidate> addCandidate(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Candidate candidate) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtTokenService.validateToken(token) || !isAdmin(token)) {
            return ResponseEntity.status(403).build(); // 403 Forbidden for non-admin users
        }
        return ResponseEntity.ok(adminService.addCandidate(candidate));
    }

    @PutMapping("/candidates/{id}")
    public ResponseEntity<Candidate> updateCandidate(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id,
            @RequestBody Candidate updatedCandidate) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtTokenService.validateToken(token) || !isAdmin(token)) {
            return ResponseEntity.status(403).build(); // 403 Forbidden for non-admin users
        }
        return ResponseEntity.ok(adminService.updateCandidate(id, updatedCandidate));
    }

    @DeleteMapping("/candidates/{id}")
    public ResponseEntity<String> deleteCandidate(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Integer id) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtTokenService.validateToken(token) || !isAdmin(token)) {
            return ResponseEntity.status(403).build(); // 403 Forbidden for non-admin users
        }
        adminService.deleteCandidate(id);
        return ResponseEntity.ok("Candidate deleted successfully");
    }

    // View Results
    @GetMapping("/results")
    public ResponseEntity<List<Candidate>> getResults(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtTokenService.validateToken(token) || !isAdmin(token)) {
            return ResponseEntity.status(403).body(null); // 403 Forbidden for non-admin users
        }
        return ResponseEntity.ok(adminService.getResults());
    }
}
