import React, { useState } from 'react';
import { FormRow, Alert } from '../../components';
import { useAppContext } from '../../context/appContext';
import Wrapper from '../../assets/wrappers/DashboardFormPage';

const Profile = () => {
  const { user, showAlert, isLoading, displayAlert, updateUser } = useAppContext();

  const uuid = user?.uuid;
  const [name, setName] = useState(user?.name);
  const [email, setEmail] = useState(user?.email);
  const [address, setAddress] = useState(user?.address);
  const [jobLocation, setJobLocation] = useState(user?.jobLocation);
  const [city, setCity] = useState(user?.city);
  const [country, setCountry] = useState(user?.country);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!name || !email || !address || !jobLocation || !city || !country) {
      displayAlert();
      return;
    }
    // I need to send to the server also user UUID
    // Why?
    // Because before I update the user , I will load the previous details of the user by searching his UUID
    // Then I will update all the rest of the data in DB
    updateUser({ uuid, name, email, address, jobLocation, city, country });
  };

  return (
    <Wrapper>
      <form className="form" onSubmit={handleSubmit}>
        <h3>profile</h3>
        {showAlert && <Alert />}
        <div className="form-center">
          <FormRow type="text" name="name" value={name} handleChange={(e) => setName(e.target.value)} />
          <FormRow type="email" name="email" value={email} handleChange={(e) => setEmail(e.target.value)} />
          <FormRow type="text" name="address" value={address} handleChange={(e) => setAddress(e.target.value)} />
          <FormRow type="text" name="jobLocation" value={jobLocation} handleChange={(e) => setJobLocation(e.target.value)} />
          <FormRow type="text" name="city" value={city} handleChange={(e) => setCity(e.target.value)} />
          <FormRow type="text" name="country" value={country} handleChange={(e) => setCountry(e.target.value)} />
          <button type="submit" className="btn btn-block" disabled={isLoading}>
            {isLoading ? 'please wait...' : 'save changes'}
          </button>
        </div>
      </form>
    </Wrapper>
  );
};

export default Profile;
