// eslint-disable-next-line no-unused-vars
import React, { createContext, useState, useContext, useCallback } from "react";
import PropTypes from 'prop-types';

const AuthContext = createContext();

// eslint-disable-next-line react-refresh/only-export-components
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(() => {
    const token = localStorage.getItem("token");
    return !!token;
  });
  
  const [token, setToken] = useState(() => localStorage.getItem("token"));

  const login = useCallback((newToken) => {
    if (!newToken) {
      console.error('No token provided to login function');
      return;
    }
    localStorage.setItem("token", newToken);
    setToken(newToken);
    setIsAuthenticated(true);
  }, []);

  const logout = useCallback(() => {
    localStorage.removeItem("token");
    setToken(null);
    setIsAuthenticated(false);
  }, []);

  const getToken = useCallback(() => {
    return localStorage.getItem("token");
  }, []);

  return (
    <AuthContext.Provider value={{ 
      isAuthenticated, 
      login, 
      logout,
      getToken,
      token 
    }}>
      {children}
    </AuthContext.Provider>
  );
};

AuthProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

export default AuthProvider;