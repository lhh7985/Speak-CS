import axios from "@/lib/axios";

export function requestUserLogin({ state }, payload) {
  console.log("requestUserLogin", state, payload);
  const url = "/auth/login";
  const body = payload;
  return axios.post(url, body);
}
