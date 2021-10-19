import axios from "axios";

const instance = axios.create({
  baseURL: "/api/v1",
  // baseURL: "http://localhost:8080",
  // baseURL: "http://j5c201.p.ssafy.io/api/v1",
  headers: {
    "Content-type": "application/json",
  },
});

export default instance;
