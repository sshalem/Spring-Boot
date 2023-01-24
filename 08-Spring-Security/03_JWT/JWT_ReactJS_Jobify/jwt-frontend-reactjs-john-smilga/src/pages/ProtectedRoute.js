import React from 'react';
import { useAppContext } from '../context/appContext';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ children }) => {
  const { token } = useAppContext();

  if (!token) {
    return <Navigate to="/landing" />;
  }
  return children;

  // the children that we return is actually the shared layout
};

export default ProtectedRoute;
