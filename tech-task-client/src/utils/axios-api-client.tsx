import axios from "axios";

console.log("server url", process.env.REACT_APP_SERVER_URL)

const axiosApiClient = axios.create({
    baseURL: process.env.REACT_APP_SERVER_URL,
    timeout: 10000,
    headers: { "Content-Type": "application/json" },
});

export default axiosApiClient;