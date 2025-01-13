//eslint-disable-next-line
import React from "react";
import { Link } from "react-router-dom";
import { useAuth } from "../hooks/AuthContext";// Assuming you have AuthContext to manage authentication

const Header = () => {
  const { isAuthenticated, logout } = useAuth();

  const handleLogout = () => {
    logout();
    window.location.href = "/"; 
  };

  return (
    <header>
      <h1>Online Voting Application</h1>
      <nav>
        {isAuthenticated ? (
          <>
            <Link to="/voter">Dashboard</Link>
            <Link to="/vote">Vote</Link> 
            <Link to="/" onClick={handleLogout}>Logout</Link>
          </>
        ) : (
          <Link to="/">Login</Link>
        )}
      </nav>
    </header>
  );
};

export default Header;