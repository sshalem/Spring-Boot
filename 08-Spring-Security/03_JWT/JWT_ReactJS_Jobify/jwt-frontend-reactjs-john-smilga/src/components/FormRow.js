import React from 'react';

const FormRow = ({ type, name, value, handleChange, labelText }) => {
  // In some cases we will use a labelText.
  // For register and login we don't use labelText
  // Thus the name will be displayed
  return (
    <div className="form-row">
      <label htmlFor={name} className="form-label">
        {labelText || name}
      </label>
      <input type={type} name={name} value={value} onChange={handleChange} className="form-input" />
    </div>
  );
};

export default FormRow;
