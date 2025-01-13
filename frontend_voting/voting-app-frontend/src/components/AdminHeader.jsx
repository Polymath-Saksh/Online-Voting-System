// eslint-disable-next-line
import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../hooks/AuthContext"; // Assuming you have AuthContext to manage authentication

const AdminHeader = () => {
  const { isAuthenticated, logout } = useAuth();

  const handleLogout = () => {
    logout();
    window.location.href = "/"; // Redirect to home after logout
  };

  return (
    <header>
      <h1>Admin Panel - Online Voting Application</h1>
      <nav>
        <Link to="/admin">Dashboard</Link>
        <Link to="/admin/voters">Voter Management</Link>
        <Link to="/admin/candidates">Candidate Management</Link>
        <Link to="/admin/results">View Results</Link>
        {isAuthenticated && (
          <Link to="/" onClick={handleLogout}>Logout</Link>
        )}
      </nav>
    </header>
  );
};

export default AdminHeader;
