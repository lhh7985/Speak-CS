import axios from "@/lib/axios";

// http://localhost:8080/swagger-ui.html

// 문제만들기
export function requsetProblemCreate({ state }, payload) {
  console.log("requsetProblemCreate", state, payload);
  const url = "/problem/make";
  const body = payload;
  return axios.post(url, body);
}
// 카테고리별 문제 리스트 가져오기
export function requestProblemList({ state }, payload) {
  console.log("requestProblemList", state, payload);
  const url = `/problem/${payload.id}}`;
  return axios.get(url);
}
// 문제 정답 맞추기
export function requestProblemCheckAnswer({ state }, payload) {
  console.log("requestProblemCheckAnswer", state, payload);
  const url = "/problem/checkanswer";
  const body = payload;
  return axios.post(url, body);
}
// 승인 대기중인 문제 리스트 가져오기
export function requestProblemWait({ state }, payload) {
  console.log("requestProblemWait", state, payload);
  const url = "/problem/wait";
  const body = payload;
  return axios.get(url, body);
}
// 관리자가 문제상태 변경하기
export function requestProblemEvaluate({ state }, payload) {
  console.log("requestProblemEvaluate", state, payload);
  const url = "/problem/evaluate";
  const body = payload;
  return axios.post(url, body);
}
