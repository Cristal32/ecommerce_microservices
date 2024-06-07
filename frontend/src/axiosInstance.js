import axios from "axios";


const axiosInstance = axios.create({
    baseURL: "http://localhost:8222/api", // Set your base URL here
});

axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token");
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
            console.log(token);
            const decodedToken = parseJwt(token); // Function to parse JWT token
            if (decodedToken) {
                config.headers.currentUser = decodedToken.sub; // Set current user in headers
                console.log("decodedToken",decodedToken.sub);
                sessionStorage.setItem("currentUser", decodedToken.sub); // Store current user in session storage
            }
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Function to parse JWT token and extract payload
function parseJwt(token) {
    try {
        return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
        return null;
    }
}

export default axiosInstance;
