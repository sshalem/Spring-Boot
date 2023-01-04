import React, { useState, useEffect } from 'react';
import { Logo, FormRow, Alert } from '../components';
import Wrapper from '../assets/wrappers/RegisterPage';
import { useAppContext } from '../context/appContext';
import { useNavigate } from 'react-router-dom';

const initialState = {
  name: '',
  email: '',
  address: '',
  jobLocation: '',
  city: '',
  country: '',
  password: '',
  isMember: true,
};

const Register = () => {
  const [values, setValues] = useState(initialState);

  // useNavigate
  const navigate = useNavigate();

  // global context and  later
  const { register, token, isLoading, showAlert, displayAlert, registerUser, loginUser } = useAppContext();

  const toggleMember = () => {
    setValues({ ...values, isMember: !values.isMember });
  };

  const handleChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value });
  };

  const onSubmit = (e) => {
    e.preventDefault();

    const { name, email, address, jobLocation, city, country, password, isMember } = values;

    if (
      !email ||
      !password ||
      (!isMember && !name) ||
      (!isMember && !address) ||
      (!isMember && !jobLocation) ||
      (!isMember && !city) ||
      (!isMember && !country)
    ) {
      displayAlert();
      return;
    }

    if (isMember) {
      loginUser({ email, password });
    } else {
      registerUser({ name, email, address, jobLocation, city, country, password });
    }
  };

  // This useEffect ,
  // prevents from going back after login
  // also, after register is submitted it will render the page with login content
  useEffect(() => {
    // This useEffect checks if (user is not null) , it will :
    // 1. setValues
    // 2. navigate to /register
    if (register) {
      setValues({
        ...values,
        isMember: !values.isMember,
        name: '',
        email: '',
        password: '',
      });
      navigate('/register');
    }
    // If token is true navigate to '/'
    // This means: after we login , if we click on the back button , we still be at the page '/'
    if (token) {
      navigate('/');
    }
  }, [register, token, navigate]);

  return (
    <Wrapper className="full-page">
      <form action="" method="post" className="form" onSubmit={onSubmit}>
        <Logo />
        <h3>{values.isMember ? 'Login' : 'Register'}</h3>
        {showAlert && <Alert />}
        {!values.isMember && <FormRow type="text" name="name" value={values.name} handleChange={handleChange} />}
        <FormRow type="email" name="email" value={values.email} handleChange={handleChange} />
        {!values.isMember && <FormRow type="text" name="address" value={values.address} handleChange={handleChange} />}
        {!values.isMember && <FormRow type="text" name="jobLocation" value={values.jobLocation} handleChange={handleChange} />}
        {!values.isMember && <FormRow type="text" name="city" value={values.city} handleChange={handleChange} />}
        {!values.isMember && <FormRow type="text" name="country" value={values.country} handleChange={handleChange} />}
        <FormRow type="password" name="password" value={values.password} handleChange={handleChange} />
        <button type="submit" className="btn btn-block" disabled={isLoading}>
          submit
        </button>
        <p>
          {values.isMember ? 'Not a member yet?' : 'Alreay a member?'}
          <button type="button" onClick={toggleMember} className="member-btn">
            {values.isMember ? 'Register' : 'Login'}
          </button>
        </p>
      </form>
    </Wrapper>
  );
};

export default Register;
