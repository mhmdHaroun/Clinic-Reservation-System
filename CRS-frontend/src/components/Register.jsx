import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export const Register = (props) => {
  let navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [pass, setPass] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [birthday, setBirthday] = useState("");
  const [gender, setGender] = useState("");
  const [details, setDetails] = useState("");
  const [userType, setUserType] = useState("patient"); // Default to patient
  const [emailError, setEmailError] = useState("");
  const [passError, setPassError] = useState("");

  const [clinicName, setClinicName] = useState("");
  const [drFirstName, setDrFirstName] = useState("");
  const [drLastName, setDrLastName] = useState("");
  const [speciality, setSpeciality] = useState("");
  const [clinicLocation, setClinicLocation] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      setEmailError("Invalid email format");
      return;
    } else {
      setEmailError("");
    }

    // Password length validation
    if (pass.length < 8) {
      setPassError("Password should be at least 8 characters");
      return;
    } else {
      setPassError("");
    }
    if (pass.length > 20) {
      setPassError("Password should not exceed 20 characters");
      return;
    } else {
      setPassError("");
    }
    if (userType == "patient") {
      const patient = {
        firstName: firstName,
        lastName: lastName,
        birthday: birthday,
        gender: gender,
        email: email,
        password: pass,
        details: details,
      };
      try {
        const response = await axios.post(
          "http://localhost:8080/patients/signUp",
          patient
        );
        navigate(`/clinics/${response.data}`);
      } catch (error) {
        setPassError("This email is used before");
      }
    } else {
      const clinic = {
        clinicName: clinicName,
        drFirstName: drFirstName,
        drLastName: drLastName,
        email: email,
        password: pass,
        speciality: speciality,
        location: clinicLocation,
        details: details,
      };
      try {
        const response = await axios.post(
          "http://localhost:8080/clinics/signUp",
          clinic
        );
        navigate(`/clinic-dashboard/${response.data}`);
      } catch (error) {
        setPassError("This email is used before");
      }
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Register</h2>
      <form className="register-form" onSubmit={handleSubmit}>
        <label htmlFor="userType">User Type</label>
        <select
          id="userType"
          name="userType"
          value={userType}
          onChange={(e) => setUserType(e.target.value)}
        >
          <option value="patient">Patient</option>
          <option value="clinic">Clinic</option>
        </select>

        {userType === "patient" && (
          <>
            <label htmlFor="firstName">First Name</label>
            <input
              value={firstName}
              name="firstName"
              onChange={(e) => setFirstName(e.target.value)}
              id="firstName"
              placeholder="First Name"
            />
            <label htmlFor="lastName">Last Name</label>
            <input
              value={lastName}
              name="lastName"
              onChange={(e) => setLastName(e.target.value)}
              id="lastName"
              placeholder="Last Name"
            />
            <label htmlFor="email">Email</label>
            <input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              type="email"
              placeholder="youremail@gmail.com"
              id="email"
              name="email"
            />
            <label htmlFor="password">Password</label>
            <input
              value={pass}
              onChange={(e) => setPass(e.target.value)}
              type="password"
              placeholder="********"
              id="password"
              name="password"
            />
            <label htmlFor="birthday">Birthday</label>
            <input
              value={birthday}
              name="birthday"
              onChange={(e) => setBirthday(e.target.value)}
              type="date"
              id="birthday"
            />
            <div className="user-type">
              <label className="radio-label">
                <input
                  type="radio"
                  value="male"
                  checked={gender === "male"}
                  onChange={() => setGender("male")}
                />
                Male
              </label>
              <label className="radio-label">
                <input
                  type="radio"
                  value="female"
                  checked={gender === "female"}
                  onChange={() => setGender("female")}
                />
                Female
              </label>
            </div>
            <label htmlFor="details">Details</label>
            <textarea
              value={details}
              name="details"
              onChange={(e) => setDetails(e.target.value)}
              id="details"
              placeholder="Additional Details"
            />
          </>
        )}

        {userType === "clinic" && (
          <>
            <label htmlFor="clinicName">Clinic Name</label>
            <input
              value={clinicName}
              name="clinicName"
              onChange={(e) => setClinicName(e.target.value)}
              id="clinicName"
              placeholder="Clinic Name"
            />
            <label htmlFor="drFirstName">Doctor's First Name</label>
            <input
              value={drFirstName}
              name="drFirstName"
              onChange={(e) => setDrFirstName(e.target.value)}
              id="drFirstName"
              placeholder="Doctor's First Name"
            />
            <label htmlFor="drLastName">Doctor's Last Name</label>
            <input
              value={drLastName}
              name="drLastName"
              onChange={(e) => setDrLastName(e.target.value)}
              id="drLastName"
              placeholder="Doctor's Last Name"
            />
            <label htmlFor="email">Email</label>
            <input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              type="email"
              placeholder="youremail@gmail.com"
              id="email"
              name="email"
            />
            <label htmlFor="password">Password</label>
            <input
              value={pass}
              onChange={(e) => setPass(e.target.value)}
              type="password"
              placeholder="********"
              id="password"
              name="password"
            />
            <label htmlFor="speciality">Speciality</label>
            <input
              value={speciality}
              name="speciality"
              onChange={(e) => setSpeciality(e.target.value)}
              id="speciality"
              placeholder="Speciality"
            />
            <label htmlFor="clinicLocation">Location</label>
            <input
              value={clinicLocation}
              name="clinicLocation"
              onChange={(e) => setClinicLocation(e.target.value)}
              id="clinicLocation"
              placeholder="Location"
            />
            <label htmlFor="details">Details</label>
            <textarea
              value={details}
              name="details"
              onChange={(e) => setDetails(e.target.value)}
              id="details"
              placeholder="Additional Details"
            />
          </>
        )}
        {/* Add margin-top to create space */}
        <div style={{ marginTop: "20px" }}>
          <button type="submit" className="loginbutton">
            Register
          </button>
        </div>
      </form>
      <button className="link-btn" onClick={() => props.onFormSwitch("login")}>
        Already have an account? Login here.
      </button>
    </div>
  );
};
