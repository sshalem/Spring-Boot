import React, { useReducer, useContext } from 'react';
import reducer from './reducer';
import axios from 'axios';
import { BASE_URL } from './url';
import {
  DISPLAY_ALERT,
  CLEAR_ALERT,
  REGISTER_USER_BEGIN,
  REGISTER_USER_SUCCESS,
  REGISTER_USER_ERROR,
  LOGIN_USER_BEGIN,
  LOGIN_USER_SUCCESS,
  LOGIN_USER_ERROR,
  TOGGLE_SIDEBAR,
  LOGOUT_USER,
} from './actions';

// If we refersh the page of dashboard ,
// what happens is that 'token' is remove beacuse of the refresh,
// (On the register.js)
// Thus I get the token from storage, and set it in the object 'initialStateOfAppContext'
// This way, IF token is preset in localStorage , when we refresh the page , we persist the token from storage
// Same goes for user logged in details
const tokenFromLocalStorage = localStorage.getItem('token');
const userDetailsFromLocalStorage = localStorage.getItem('userLoggedDetails');

// These are the variables of the states I pass to the useReducer hook
// We hanlde all the states in the reducer.js file
const initialStateOfAppContext = {
  isLoading: false,
  showAlert: false,
  alertText: '',
  alertType: '',
  register: null,
  user: userDetailsFromLocalStorage ? JSON.parse(userDetailsFromLocalStorage) : null,
  token: tokenFromLocalStorage,
  showSidebar: false,
};

const AppContext = React.createContext();

const AppProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialStateOfAppContext);

  const displayAlert = () => {
    dispatch({ type: DISPLAY_ALERT });
    clearAlert();
  };

  const clearAlert = () => {
    setTimeout(() => {
      dispatch({ type: CLEAR_ALERT });
    }, 2000);
  };

  const addToLocalStorage = (token, userLoginDetails) => {
    localStorage.setItem('token', token);
    localStorage.setItem('userLoggedDetails', JSON.stringify(userLoginDetails));
  };

  const removeFromLocalStorage = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('userLoggedDetails');
  };

  const registerUser = async (currentUser) => {
    dispatch({ type: REGISTER_USER_BEGIN });

    try {
      const response = await axios.post(`${BASE_URL}/auth/register`, currentUser);

      const { uuid, name, email, address, city, country } = response.data;
      dispatch({ type: REGISTER_USER_SUCCESS, payload: { uuid, name, email, address, city, country } });
    } catch (error) {
      dispatch({ type: REGISTER_USER_ERROR, payload: { msg: error.response.data } });
    }

    clearAlert();
  };

  const loginUser = async (currentUser) => {
    dispatch({ type: LOGIN_USER_BEGIN });
    try {
      const { data } = await axios.post(`${BASE_URL}/auth/login`, currentUser);
      const { userLoginDetails, jwtToken } = data;

      dispatch({ type: LOGIN_USER_SUCCESS, payload: { userLoginDetails, jwtToken } });
      addToLocalStorage(jwtToken, userLoginDetails);
    } catch (error) {
      console.log(error);
      dispatch({ type: LOGIN_USER_ERROR, payload: { msg: error.response.data.message } });
    }
    clearAlert();
  };

  const toggleSidebar = () => {
    dispatch({ type: TOGGLE_SIDEBAR });
  };

  const logoutUser = () => {
    dispatch({ type: LOGOUT_USER });
    removeFromLocalStorage();
  };

  const updateUser = async (currentUser) => {
    try {
      const { data } = await axios.put(`${BASE_URL}/auth/update/user`, currentUser, {
        headers: {
          Authorization: `Bearer ${state.token}`,
        },
      });
      console.log(data);
    } catch (error) {
      console.log(error.response);
    }
  };

  return (
    <AppContext.Provider
      value={{
        ...state,
        displayAlert,
        registerUser,
        loginUser,
        toggleSidebar,
        logoutUser,
        updateUser,
      }}>
      {children}
    </AppContext.Provider>
  );
};

const useAppContext = () => {
  return useContext(AppContext);
};

export { AppProvider, initialStateOfAppContext, useAppContext };
