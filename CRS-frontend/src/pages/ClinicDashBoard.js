import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import NavBar from "../components/NavBar";
import "../css/ClinicDashBoard.css";
import axios from "axios";

export default function ClinicDashBoard() {
  const { clinicId } = useParams();
  const navigate = useNavigate();
  const [clinicH, setClinicH] = useState([]);

  useEffect(() => {
    loadClinicH();
  }, []);

  const loadClinicH = async () => {
    const result = await axios.get(
      `http://localhost:8080/appointments/getAppointments/${clinicId}`
    );
    setClinicH(result.data);
  };

  const deleteclinicH = async (appId) => {
    const result = await axios.delete(
      `http://localhost:8080/appointments/deleteAppointment/${appId}`
    );
    loadClinicH();
  };

  const modifyclinicH = async (appId) => {
    navigate(`/modifyavailability/${clinicId}/${appId}`);
  };

  return (
    <div className="clinicDash">
      <NavBar></NavBar>
      <div className="title">
        <h1>Apointments</h1>
        <button
          className="btn-1"
          onClick={() => navigate(`/addavailability/${clinicId}`)}
        >
          Add Appointment
        </button>
      </div>
      <div className="table">
        <table>
          <thead>
            <tr>
              <th>Id</th>
              <th>Day</th>
              <th>Start Time</th>
              <th>End Time</th>
              <th>Number of Patients</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {clinicH.map((app, i) => (
              <tr>
                <td>{i + 1}</td>
                <td>{app.day}</td>
                <td>{app.startTime}</td>
                <td>{app.endTime}</td>
                <td>{app.numRegPatients}</td>
                <td>
                  <div className="tableBtns">
                    <button
                      className="tableBtnDelete"
                      onClick={() => {
                        deleteclinicH(app.appId);
                      }}
                    ></button>
                    <button
                      className="tableBtnModify"
                      onClick={() => {
                        modifyclinicH(app.appId);
                      }}
                    ></button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="activeNumber">
        <div className="div-1">
          <h1>Current Active Number: 12</h1>
          <h1>Estimated Wait Time: 1:00</h1>
        </div>
        <button onClick={() => console.log(clinicH)}>
          Update Active Number
        </button>
      </div>
    </div>
  );
}
