// eslint-disable-next-line 
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./hooks/AuthContext";
import Header from "./components/Header"; // Voter Header
import AdminHeader from "./components/AdminHeader"; // Admin Header
import AuthHeader from "./components/AuthHeader"; // New Auth Header
import ResultsPage from "./pages/admin/ResultsPage";
import CandidatesPage from "./pages/admin/CandidatesPage";
import VotersPage from "./pages/admin/VotersPage";
import VotePage from "./pages/voter/VotePage";
import VoterDashboard from "./pages/voter/VoterDashboard";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import AdminDashboard from "./pages/admin/AdminDashboard";
import HomePage from "./components/HomePage";

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          {/* Home Route for Redirection */}
          <Route path="/" element={<HomePage />} />

          {/* Admin Routes */}
          <Route
            path="/admin"
            element={
              <>
                <AdminHeader />
                <AdminDashboard />
              </>
            }
          />
          <Route
            path="/admin/results"
            element={
              <>
                <AdminHeader />
                <ResultsPage />
              </>
            }
          />
          <Route
            path="/admin/candidates"
            element={
              <>
                <AdminHeader />
                <CandidatesPage />
              </>
            }
          />
          <Route
            path="/admin/voters"
            element={
              <>
                <AdminHeader />
                <VotersPage />
              </>
            }
          />

          {/* Voter Routes */}
          <Route
            path="/voter"
            element={
              <>
                <Header />
                <VoterDashboard />
              </>
            }
          />
          <Route
            path="/vote"
            element={
              <>
                <Header />
                <VotePage />
              </>
            }
          />

          {/* Auth Routes */}
          <Route
            path="/login"
            element={
              <>
                <AuthHeader />
                <LoginPage />
              </>
            }
          />
          <Route
            path="/register"
            element={
              <>
                <AuthHeader />
                <RegisterPage />
              </>
            }
          />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;
