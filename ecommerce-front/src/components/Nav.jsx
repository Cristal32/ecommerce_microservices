import "../App.css";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import SignModal from "./SignModal";
import Logo from "../assets/Logo.png";

function Nav() {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <nav id="nav" className="nav">
      <img className="logo" src={Logo} alt="logo" />
      <ul className="nav-list">
        <li className="">
          <Link to="/" className="link">Home</Link>
        </li>
        <li className="">
          <Link to="/#About" className="link">About</Link>
        </li>
        <li className="">
          <Link to="/#Contact" className="link">Contact</Link>
        </li>
        <li className="login" onClick={() => setIsOpen(!isOpen)}>
          <p to="/login">Log in</p>
        </li>
        {isOpen && <SignModal setIsOpen={setIsOpen} />}
      </ul>
    </nav>
  );
}

export default Nav;
