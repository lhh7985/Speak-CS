import axios from "axios";

const instance = axios.create({
  // baseURL: "/",
  // baseURL: "/api/v1",
  // baseURL: "http://localhost:8080",
  baseURL: "http://k5c101.p.ssafy.io/api",
  headers: {
    "Content-type": "application/json",
  },
});

export default instance;
