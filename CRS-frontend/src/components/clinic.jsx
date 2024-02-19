import React from "react";
import Image from "./Image";
import { useNavigate } from "react-router-dom";

export default function Clinic(props) {
  const navigate = useNavigate();
  return (
    <div className="clinic">
      <Image />
      <div className="doc">
        <b>Clinic Name:</b>
        <b> {props.clinic.clinicName}</b>
        <b>Dr First Name:</b>
        <b> {props.clinic.drFirstName}</b>
        <b>Dr Last Name: </b>
        <b>{props.clinic.drLastName}</b>
        <b>Speciality: </b>
        <b>{props.clinic.speciality}</b>
        <b>Location</b>
        <b>{props.clinic.location}</b>
      </div>
      <button
        className="clinicsbutton"
        onClick={() =>
          navigate(`/reserve/${props.patientId}/${props.clinic.clinicId}`)
        }
      >
        Make An Appointment
      </button>
    </div>
  );
}
