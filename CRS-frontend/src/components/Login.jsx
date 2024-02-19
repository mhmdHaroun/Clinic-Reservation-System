import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ViewClinics from "../pages/ViewClinics";

export const Login = (props) => {
  let navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [pass, setPass] = useState("");
  const [userType, setUserType] = useState("patient"); // Default to patient
  const [emailError, setEmailError] = useState("");
  const [passError, setPassError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      setEmailError("Invalid email format");
      return;
    } else {
      setEmailError("");
    }

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

    const user = {
      email: email,
      password: pass,
    };
    if (userType == "patient") {
      try {
        const response = await axios.post(
          "http://localhost:8080/patients/logIn",
          user
        );
        navigate(`/clinics/${response.data}`);
      } catch (error) {
        setPassError("Invalid email or password. Please try again");
      }
    } else {
      try {
        const response = await axios.post(
          "http://localhost:8080/clinics/logIn",
          user
        );
        navigate(`/clinic-dashboard/${response.data}`);
      } catch (error) {
        setPassError("Invalid email or password. Please try again");
      }
    }
  };

  return (
    <div className="auth-form-container">
      <h2>Login</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="email">Email</label>
        <input
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          type="email"
          placeholder="youremail@gmail.com"
          id="email"
          name="email"
        />
        {emailError && (
          <p className="error-message" style={{ color: "red" }}>
            {emailError}
          </p>
        )}

        <label htmlFor="password">Password</label>
        <input
          value={pass}
          onChange={(e) => setPass(e.target.value)}
          type="password"
          placeholder="********"
          id="password"
          name="password"
        />
        {passError && (
          <p className="error-message" style={{ color: "red" }}>
            {passError}
          </p>
        )}

        <div className="user-type">
          <label className="radio-label">
            <input
              type="radio"
              value="patient"
              checked={userType === "patient"}
              onChange={() => setUserType("patient")}
            />
            Patient
          </label>
          <label className="radio-label">
            <input
              type="radio"
              value="clinic"
              checked={userType === "clinic"}
              onChange={() => setUserType("clinic")}
            />
            Clinic
          </label>
        </div>
        <button type="submit" className="loginbutton">
          Log In
        </button>
      </form>

      <button
        className="link-btn"
        onClick={() => props.onFormSwitch("register")}
      >
        Don't have an account? Register here.
      </button>
    </div>
  );
};
