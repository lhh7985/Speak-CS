import axios from "@/lib/axios";

// http://localhost:8080/swagger-ui.html

export function requsetUserRegist({ state }, payload) {
  console.log("requsetUserRegist", state, payload);
  const url = "/user";
  const body = payload;
  return axios.post(url, body);
}

export function requestUserLogin({ state }, payload) {
  console.log("requestUserLogin", state, payload);
  const url = "/user/login";
  const body = payload;
  return axios.post(url, body);
}

export function requsetUserInfo({ state }, payload) {
  console.log("requsetUserInfo", state, payload);
  const url = "/user/me";
  const body = payload;
  return axios.post(url, body);
}

export function requestUserList({ state }, payload) {
  console.log("requestUserList", state, payload);
  const url = "/user/all";
  const body = payload;
  return axios.post(url, body);
}
