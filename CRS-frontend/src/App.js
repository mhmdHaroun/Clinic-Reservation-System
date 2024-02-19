import React, { useState } from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginAndSignUp from "./pages/LoginAndSignUp";
import ViewClinics from "./pages/ViewClinics";
import ClinicDashBoard from "./pages/ClinicDashBoard";
import AddAvailability from "./pages/AddAvailability";
import ModifyAvailability from "./pages/ModifyAvailability";
import Reserve from "./pages/Reserve";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route exact path="/" element={<LoginAndSignUp />} />
          <Route exact path="/clinics/:patientId" element={<ViewClinics />} />
          <Route
            exact
            path="/addavailability/:clinicId"
            element={<AddAvailability />}
          />
          <Route
            exact
            path="/modifyavailability/:clinicId/:appId"
            element={<ModifyAvailability />}
          />
          <Route
            exact
            path="/clinic-dashboard/:clinicId"
            element={<ClinicDashBoard />}
          />
          <Route
            exact
            path="/reserve/:patientId/:clinicId"
            element={<Reserve />}
          />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
