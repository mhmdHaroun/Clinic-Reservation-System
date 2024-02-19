import React, { useState } from "react";
import { Login } from "../components/Login";
import { Register } from "../components/Register";
import "../css/LoginAndSignUp.css";

export default function LoginAndSignUp() {
  const [currentForm, setCurrentForm] = useState("login");

  const toggleForm = (formName) => {
    setCurrentForm(formName);
  };

  return (
    <div className="loginAndSign">
      {currentForm === "login" ? (
        <Login onFormSwitch={toggleForm} />
      ) : (
        <Register onFormSwitch={toggleForm} />
      )}
    </div>
  );
}
