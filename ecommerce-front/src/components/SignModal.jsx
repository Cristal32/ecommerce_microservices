import { AiOutlineClose } from "react-icons/ai";
import "../App.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function SignModal({ setIsOpen }) {
  const [formData, setFormData] = useState({
    user: {
      username: "",
      password: "",
    },
    client: {
      firstName: "",
      lastName: "",
      email: "",
      tel: "",
    },
  });
  const [isMember, setIsMember] = useState(true);
  const navigate = useNavigate();

  const handleSubmit = async () => {
    try {
      if (isMember) {
        // Sign In
        const { user } = formData;
        const response = await axios.post("http://localhost:8222/api/auth/getToken", {
          username: formData.user.username,
          password: formData.user.password,
        });
        localStorage.setItem("token", response.data);
        alert("Sign In successful");
      } else {
        // Sign Up
        const requestData = formData;
        await axios.post("http://localhost:8222/api/auth/register", requestData);
        alert("Sign Up successful");
      }
      navigateAfterAuth();
      setIsOpen(false);
    } catch (error) {
      console.error("Authentication error:", error);
      alert("Error during authentication");
    }
  };

  const navigateAfterAuth = async () => {
    try {
      const token = localStorage.getItem("token");
      if (token) {
        const decodedToken = parseJwt(token);
        if (decodedToken.roles.includes("admin")) {
          localStorage.setItem("isAdmin", "true");
          console.log("you are admin");
        } else {
          localStorage.setItem("isAdmin", "false");
          console.log("you are NOT admin");
        }
        navigate("/catalogue");
      } else {
        alert("Token not found");
      }
    } catch (error) {
      console.error("Error navigating after authentication:", error);
      alert("Error navigating after authentication");
    }
  };

  const parseJwt = (token) => {
    try {
      return JSON.parse(atob(token.split(".")[1]));
    } catch (e) {
      return {};
    }
  };

  return (
    <div className="signModal">
      <div className="signmodal-content">
        <AiOutlineClose className="close" onClick={() => setIsOpen(false)} />
        <h2>{isMember ? "Welcome back, User" : "Sign Up to Let's Shop"}</h2>
        <br />
        <div className="signdiv">
          {!isMember && (
            <>
              <input
                type="text"
                id="firstname"
                name="firstName"
                placeholder="First Name"
                onChange={(e) =>
                  setFormData({
                    ...formData,
                    client: { ...formData.client, [e.target.name]: e.target.value },
                  })
                }
              />
              <br />
              <input
                type="text"
                id="lastname"
                name="lastName"
                placeholder="Last Name"
                onChange={(e) =>
                  setFormData({
                    ...formData,
                    client: { ...formData.client, [e.target.name]: e.target.value },
                  })
                }
              />
              <br />
              <input
                type="text"
                id="email"
                name="email"
                placeholder="Email"
                onChange={(e) =>
                  setFormData({
                    ...formData,
                    client: { ...formData.client, [e.target.name]: e.target.value },
                  })
                }
              />
              <br />
              <input
                type="text"
                id="tel"
                name="tel"
                placeholder="Tel"
                onChange={(e) =>
                  setFormData({
                    ...formData,
                    client: { ...formData.client, [e.target.name]: e.target.value },
                  })
                }
              />
              <br />
            </>
          )}
          <input
            type="text"
            id="username"
            name="username"
            placeholder="username"
            onChange={(e) =>
              setFormData({
                ...formData,
                user: { ...formData.user, username: e.target.value },
              })
            }
          />
          <br />
          <input
            type="password"
            id="password"
            name="password"
            placeholder="Password"
            onChange={(e) =>
              setFormData({
                ...formData,
                user: { ...formData.user, password: e.target.value },
              })
            }
          />
          <br />
          <button type="button" onClick={handleSubmit}>
            {isMember ? "Sign In" : "Sign Up"}
          </button>
        </div>
        <div className="separator"></div>
        <h2>{isMember ? "New to Let's Shop?" : "Already joined Let's Shop?"}</h2>
        <button className="button" onClick={() => setIsMember(!isMember)}>
          {isMember ? "Sign Up" : "Sign In"}
        </button>
      </div>
    </div>
  );
}

export default SignModal;
