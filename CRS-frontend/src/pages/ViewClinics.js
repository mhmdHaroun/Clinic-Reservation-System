import React, { useEffect, useState } from "react";
import axios from "axios";
import Clinic from "../components/clinic";
import { useNavigate, useParams } from "react-router-dom";
import NavBar from "../components/NavBar";
import "../css/ViewClinics.css";

export default function ViewClinics() {
  const { patientId } = useParams();
  let navigate = useNavigate();
  const [clinics, setClinics] = useState([]);

  useEffect(() => {
    loadClinics();
  }, []);

  const loadClinics = async () => {
    const result = await axios.get("http://localhost:8080/clinics");
    setClinics(result.data);
  };

  

  return (
    <div className="con">
      <NavBar></NavBar>
      <div className="clinics">
        {clinics.map((clinic) => (
          <div>
            <Clinic clinic={clinic} patientId={patientId} />
            <div className="break" />
          </div>
        ))}
      </div>
    </div>
  );
}
