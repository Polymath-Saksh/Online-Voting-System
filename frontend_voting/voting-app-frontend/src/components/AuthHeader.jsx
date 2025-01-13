//eslint-disable-next-line
import React from "react";
import { Link } from "react-router-dom";

const AuthHeader = () => {
  return (
    <header>
      <h1>Online Voting Application</h1>
      <nav>
        <Link to="/login">Login</Link>
        <Link to="/register">Register</Link>
      </nav>
    </header>
  );
};

export default AuthHeader;
