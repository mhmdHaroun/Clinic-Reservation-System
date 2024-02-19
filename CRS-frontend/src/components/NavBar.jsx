import React from "react";
import { useNavigate } from "react-router-dom";
import "../css/NavBar.css";

export default function NavBar() {
  let navigate = useNavigate();
  return (
    <div className="navBar">
      <div className="navBar1">
        <h1>Clinics Reservation System</h1>
        <button onClick={() => navigate("/")} className="clinicsbutton">
          Sign Out
        </button>
      </div>
      <div className="underline" />
    </div>
  );
}
