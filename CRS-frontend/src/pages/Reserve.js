import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import "../App.css";
import { useParams } from "react-router-dom";
import axios from "axios";
import NavBar from "../components/NavBar";

const Reserve = () => {
  const { patientId, clinicId } = useParams();
  const [selectedTimeSlot, setSelectedTimeSlot] = useState([-1, "null"]);
  const [isReserved, setIsReserved] = useState(false);
  const [clinic, setClinic] = useState();
  const [clinicH, setClinicH] = useState([]);
  const [date, setDate] = useState();
  const [error, setError] = useState("");
  const {
    clinicName = "Clinic A",
    drFirstName = "",
    drLastName = "",
    speciality = "",
    location = "",
  } = clinic || {};

  useEffect(() => {
    loadClinic();
    loadClinicH();
  }, []);

  const loadClinic = async () => {
    const result = await axios.get("http://localhost:8080/clinics");
    setClinic(result.data.filter((clinic) => clinic.clinicId == clinicId)[0]);
  };

  const loadClinicH = async () => {
    const result = await axios.get(
      `http://localhost:8080/appointments/getAppointments/${clinicId}`
    );
    setClinicH(result.data);
  };

  const handleDate = (e) => {
    const d = new Date(e);
    const day = `${d
      .toLocaleDateString("en-US", { weekday: "long" })
      .toLowerCase()}`;
    const day2 = selectedTimeSlot.slice(2);
    if (selectedTimeSlot[0] == -1 || day2 != day) {
      setError("The two Days Don't Match");
    }
    if (selectedTimeSlot[0] != -1 && day2 == day) {
      setError("");
    }
    setDate(e);
  };

  const handleTimeSlotChange = (e) => {
    setSelectedTimeSlot(e.target.value);
    console.log(selectedTimeSlot);
  };

  const handleReserveClick = async () => {
    if (error == "") {
      const appdate = {
        appointmentDate: date,
      };
      const response = await axios.post(
        `http://localhost:8080/reservations/addReservation/${clinicId}/${patientId}/${selectedTimeSlot[0]}`,
        appdate
      );
      if (response.data) setIsReserved(true);
    }
  };
  const renderTimeSlotDropdown = () => {
    if (isReserved) {
      return <p>Appointment already reserved. Thank you!</p>;
    }

    return (
      <div className="time-slot-container">
        <label>Select Time Slot</label>
        <select value={selectedTimeSlot} onChange={handleTimeSlotChange}>
          <option value="">Select a time slot</option>
          {clinicH.map((slot) => (
            <option value={[slot.appId, slot.day]}>
              {slot.day} {slot.startTime} to {slot.endTime}
            </option>
          ))}
        </select>
        <button
          type="button"
          onClick={handleReserveClick}
          disabled={!selectedTimeSlot}
        >
          Reserve
        </button>
      </div>
    );
  };

  return (
    <div className="appointment-scheduler-container">
      <NavBar />
      <h2>Make an Appointment at {clinicName}</h2>
      <div className="clinic-details">
        <p>
          <b>Dr:</b> {drFirstName} {drLastName}
        </p>
        <p>
          <b>Speciality:</b> {speciality}
        </p>
        <p>
          <b>Location:</b> {location}
        </p>
      </div>
      {renderTimeSlotDropdown()}
      <div>
        <label htmlFor="birthday">AppointMent Date</label>
        <input
          value={date}
          name="date"
          onChange={(e) => handleDate(e.target.value)}
          type="date"
          id="date"
        />
        <p className="error-message" style={{ color: "red" }}>
          {error}
        </p>
      </div>
    </div>
  );
};

export default Reserve;
