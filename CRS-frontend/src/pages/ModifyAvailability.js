import React, { useEffect, useState } from "react";
import "../css/AddAvailability.css";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

export default function ModifyAvailabilityAvailability(props) {
  const { clinicId, appId } = useParams();
  const [selectedDay, setSelectedDay] = useState([]);
  const [startTime, setStartTime] = useState("09");
  const [startAmPm, setStartAmPm] = useState("AM");
  const [endTime, setEndTime] = useState("05");
  const [endAmPm, setEndAmPm] = useState("PM");
  const [maxPatients, setMaxPatients] = useState(1);

  useEffect(() => {
    init();
  }, []);

  const init = async () => {
    const apps = (
      await axios.get(
        `http://localhost:8080/appointments/getAppointments/${clinicId}`
      )
    ).data;
    const app = apps.filter((app) => app.appId == appId)[0];
    const day = `${app.day}`;
    setSelectedDay(day.charAt(0).toUpperCase() + day.slice(1));
    setMaxPatients(app.maxPatients);
    let s = Number(app.startTime.substring(0, 2));
    if (s > 11) setStartAmPm("PM");
    let e = Number(app.endTime.substring(0, 2));
    if (e > 11) setEndAmPm("PM");
    if (s > 12)s -= 12;
    if (e > 12)e -= 12;
    setStartTime((s > 9 ? `${s}` : `0${s}`));
    setEndTime(e > 9 ? `${e}` : `0${e}`);
  };

  const numbers = [];
  for (let i = 1; i <= 100; i++) numbers.push(i);

  const navigate = useNavigate();

  const handleDayChange = (day) => {
    setSelectedDay([day]);
  };

  const handleStartTimeChange = (e) => {
    setStartTime(e.target.value);
  };

  const handleStartAmPmChange = (e) => {
    setStartAmPm(e.target.value);
  };

  const handleEndTimeChange = (e) => {
    setEndTime(e.target.value);
  };

  const handleEndAmPmChange = (e) => {
    setEndAmPm(e.target.value);
  };

  const handleMaxPatientsChange = (e) => {
    setMaxPatients(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const strTime =
      startAmPm == "AM"
        ? `${startTime}:00:00`
        : `${Number(startTime) + 12}:00:00`;
    const enTime =
      endAmPm == "AM" ? `${endTime}:00:00` : `${Number(endTime) + 12}:00:00`;
    const availability = {
      clinicID: clinicId,
      appId: appId,
      day: selectedDay,
      startTime: strTime,
      endTime: enTime,
      maxPatients: maxPatients,
    };
    try {
      const response = await axios.post(
        "http://localhost:8080/appointments/updateAppointment",
        availability
      );
      navigate(`/clinic-dashboard/${clinicId}`);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div className="auth-form-container clinic-availability-container">
      <h2>Update Clinic Availability</h2>
      <form className="availability-form" onSubmit={handleSubmit}>
        <div className="day-checkboxes">
          {[
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday",
          ].map((day) => (
            <label key={day} className="day-checkbox">
              <input
                type="checkbox"
                value={day}
                checked={selectedDay == day}
                onChange={() => handleDayChange(day)}
              />
              {day}
            </label>
          ))}
        </div>
        <div className="time-slot-container">
          <label>Start Time</label>
          <select value={startTime} onChange={handleStartTimeChange}>
            <option value="01">01</option>
            <option value="02">02</option>
            <option value="03">03</option>
            <option value="04">04</option>
            <option value="05">05</option>
            <option value="06">06</option>
            <option value="07">07</option>
            <option value="08">08</option>
            <option value="09">09</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
          </select>
          <select value={startAmPm} onChange={handleStartAmPmChange}>
            <option value="AM">AM</option>
            <option value="PM">PM</option>
          </select>
          <label>End Time</label>
          <select value={endTime} onChange={handleEndTimeChange}>
            <option value="01">01</option>
            <option value="02">02</option>
            <option value="03">03</option>
            <option value="04">04</option>
            <option value="05">05</option>
            <option value="06">06</option>
            <option value="07">07</option>
            <option value="08">08</option>
            <option value="09">09</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
          </select>
          <select value={endAmPm} onChange={handleEndAmPmChange}>
            <option value="AM">AM</option>
            <option value="PM">PM</option>
          </select>
          <label>Max Number of Patients</label>
          <select value={maxPatients} onChange={handleMaxPatientsChange}>
            {numbers.map((number) => (
              <option value={number}>{number}</option>
            ))}
          </select>
        </div>

        <div className="submit-container">
          <button type="submit" className="submit-btn">
            Modify Availability
          </button>
        </div>
      </form>
    </div>
  );
}
