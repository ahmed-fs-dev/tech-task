import axios from "axios";

console.log("API Base URL:", process.env.REACT_APP_SERVER_URL);   

const axiosApiClient = axios.create({
    baseURL: process.env.REACT_APP_SERVER_URL, // change to your API URL
    timeout: 10000,
    headers: { "Content-Type": "application/json" },
});

export default axiosApiClient;