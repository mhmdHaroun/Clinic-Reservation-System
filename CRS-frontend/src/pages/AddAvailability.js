import React, { useState } from "react";
import "../css/AddAvailability.css";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

export default function AddAvailability() {
  const { clinicId } = useParams();
  const [selectedDays, setSelectedDays] = useState([]);
  const [startTime, setStartTime] = useState("09");
  const [startAmPm, setStartAmPm] = useState("AM");
  const [endTime, setEndTime] = useState("05");
  const [endAmPm, setEndAmPm] = useState("PM");
  const [maxPatients, setMaxPatients] = useState(1);
  const [availability, setAvailability] = useState([]);
  const [slots, setSlots] = useState([]);

  const numbers = [];
  for (let i = 1; i <= 100; i++) numbers.push(i);

  const navigate = useNavigate();

  const handleDayChange = (day) => {
    if (selectedDays.includes(day)) {
      setSelectedDays(
        selectedDays.filter((selectedDay) => selectedDay !== day)
      );
    } else {
      setSelectedDays([...selectedDays, day]);
    }
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

  const addTimeSlot = () => {
    if (selectedDays.length > 0) {
      const timeSlot = `${startTime}:${startAmPm} - ${endTime}:${endAmPm}`;
      const strTime =
        startAmPm == "AM"
          ? `${startTime}:00:00`
          : `${Number(startTime) + 12}:00:00`;
      const enTime =
        endAmPm == "AM" ? `${endTime}:00:00` : `${Number(endTime) + 12}:00:00`;
      setSlots([
        ...slots,
        { days: selectedDays, strTime, enTime, maxPatients },
      ]);
      setAvailability([
        ...availability,
        { days: selectedDays, timeSlot, maxPatients },
      ]);

      setSelectedDays([]);
    }
  };

  const removeTimeSlot = (index) => {
    setAvailability(availability.filter((_, i) => i !== index));
    setSlots(slots.filter((_, i) => i !== index));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    slots.map(async (slot) => {
      slot.days.map(async (day) => {
        const interval = {
          clinicID: clinicId,
          day: day,
          startTime: slot.strTime,
          endTime: slot.enTime,
          maxPatients: maxPatients,
        };
        try {
          const response = await axios.post(
            "http://localhost:8080/appointments/addAppointment",
            interval
          );
          navigate(`/clinic-dashboard/${clinicId}`);
        } catch (e) {
          console.log(e);
        }
      });
    });
  };

  return (
    <div className="auth-form-container clinic-availability-container">
      <h2>Set Clinic Availability</h2>
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
                checked={selectedDays.includes(day)}
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
          <button type="button" onClick={addTimeSlot}>
            Add Time Slot
          </button>
        </div>
        <div className="selected-time-slots">
          <label>Selected Time Slots</label>
          <ul>
            {availability.map((slot, index) => (
              <li key={index}>
                {slot.days.join(", ")} - {slot.timeSlot} {slot.maxPatients}{" "}
                Patients
                <button
                  type="button"
                  onClick={() => removeTimeSlot(index)}
                  className="remove-btn"
                >
                  Remove
                </button>
              </li>
            ))}
          </ul>
        </div>
        <div className="submit-container">
          <button type="submit" className="submit-btn">
            Save Availability
          </button>
        </div>
      </form>
    </div>
  );
}
