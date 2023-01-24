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
  UPDATE_USER_BEGIN,
  UPDATE_USER_SUCCESS,
  UPDATE_USER_ERROR,
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

  // axios instance
  const authFetch = axios.create({
    baseURL: `${BASE_URL}`,
  });

  // Add a request interceptor
  authFetch.interceptors.request.use(
    (request) => {
      // Do something before request is sent
      request.headers.common['Authorization'] = `Bearer ${state.token}`;
      return request;
    },
    (error) => {
      // Do something with request error
      return Promise.reject(error);
    }
  );

  // Add a response interceptor
  authFetch.interceptors.response.use(
    (response) => {
      // Any status code that
      // lie within the range of 2xx
      // cause this function to trigger.
      // Do something with response data
      return response;
    },
    (error) => {
      // Any status codes that
      // falls outside the range of 2xx
      // cause this function to trigger.
      // Do something with response error
      console.log(error.response);
      if (error.response.status === 401) {
        console.log('Unauthorized error , verify token is send with request');
        console.log('User is Logged Out');
        logoutUser();
      }
      return Promise.reject(error);
    }
  );

  const displayAlert = () => {
    dispatch({ type: DISPLAY_ALERT });
    clearAlert();
  };

  const clearAlert = () => {
    setTimeout(() => {
      dispatch({ type: CLEAR_ALERT });
    }, 2000);
  };

  const addUserToLocalStorage = (userLoginDetails) => {
    localStorage.setItem('userLoggedDetails', JSON.stringify(userLoginDetails));
  };

  const addTokenToLocalStorage = (token) => {
    localStorage.setItem('token', token);
  };

  const removeFromLocalStorage = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('userLoggedDetails');
  };

  const registerUser = async (currentUser) => {
    dispatch({ type: REGISTER_USER_BEGIN });
    try {
      // 'baseURL' and 'headers' already declared in custom instance of axios
      const response = await axios.post(`${BASE_URL}/auth/register`, currentUser);

      const { uuid, name, email, address, city, country } = response.data;
      dispatch({ type: REGISTER_USER_SUCCESS, payload: { uuid, name, email, address, city, country } });
    } catch (error) {
      console.log(error);
      console.log(error.response);
      dispatch({ type: REGISTER_USER_ERROR, payload: { msg: error.response.data } });
    }
    clearAlert();
  };

  const loginUser = async (currentUser) => {
    dispatch({ type: LOGIN_USER_BEGIN });
    try {
      // 'baseURL' and 'headers' already declared in custom instance of axios
      const { data } = await axios.post(`${BASE_URL}/auth/login`, currentUser);
      const { userLoginDetails, jwtToken } = data;

      dispatch({ type: LOGIN_USER_SUCCESS, payload: { userLoginDetails, jwtToken } });
      addTokenToLocalStorage(jwtToken);
      addUserToLocalStorage(userLoginDetails);
    } catch (error) {
      console.log(error.response);
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
    // 'baseURL' and 'headers' already declared in custom instance of axios
    dispatch({ type: UPDATE_USER_BEGIN });
    try {
      const { data } = await authFetch.put(`/auth/updateUser`, currentUser);
      const userLoginDetails = data;

      dispatch({ type: UPDATE_USER_SUCCESS, payload: { userLoginDetails } });
      addUserToLocalStorage(userLoginDetails);
    } catch (error) {
      if (error.response.status !== 401) {
        dispatch({ type: UPDATE_USER_ERROR, payload: { msg: error.response.data.message } });
      }
    }
    clearAlert();
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
