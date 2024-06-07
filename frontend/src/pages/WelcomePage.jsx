import Nav from "../components/Nav";
import React, { useState } from "react";
import "../App.css";
import SignModal from "../components/SignModal";
import Logo from "../assets/Logo.png";
import { useNavigate } from "react-router-dom";

function WelcomePage() {
  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate();

  const handleVisitor = () => {
    sessionStorage.setItem("isVisitor", "true");
    navigate(`/catalogue`);
  };

  const handleNotVisitor = () => {
    sessionStorage.setItem("isVisitor", "false");
    setIsOpen(!isOpen);
  };

  return (
    <>
      <Nav />
      <div className="welcome-page">
        <div className="welcome-text">
          <h1>
            <p>Welcome dear Customer to </p>
            <br />
            <br />
            <p className="lets-shop"> Let's Shop</p>
          </h1>
        </div>
        <div>
          <button onClick={handleNotVisitor}>Start Shopping Now !!</button>
          {isOpen && <SignModal setIsOpen={setIsOpen} />}
          <button onClick={handleVisitor}>Visit The Catalogue</button>
        </div>
      </div>
    </>
  );
}

export default WelcomePage;
