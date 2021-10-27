import axios from "@/lib/axios";

// http://localhost:8080/swagger-ui.html

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 생성하기
export async function requsetUserRegist({ state }, payload) {
  console.log("requsetUserRegist", state, payload);
  const url = "/user";
  const body = payload;
  return await axios.post(url, body);
}

// 로그인하기
export async function requestUserLogin({ state }, payload) {
  console.log("requestUserLogin", state, payload);
  const url = "/user/login";
  const body = payload;
  return await axios.post(url, body);
}

// 유저정보 가져오기
export async function requsetUserInfo({ state }, payload) {
  console.log("requsetUserInfo", state, payload);
  const url = "/user/me";
  const body = payload;
  return await axios.get(url, body);
}

// 유저정보 리스트 가져오기
export async function requestUserList({ state }, payload) {
  console.log("requestUserList", state, payload);
  const url = "/user/all";
  const body = payload;
  return await axios.get(url, body);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 카테고리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 모든 카테고리 정보 가져오기
export async function requsetCategoryList({ state }, payload) {
  console.log("requsetCategoryList", state, payload);
  const url = "/category";
  return await axios.get(url);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 문제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 문제만들기
export async function requsetProblemCreate({ state }, payload) {
  console.log("requsetProblemCreate", state, payload);
  const url = "/problem/make";
  const body = payload;
  return await axios.post(url, body);
}

// 카테고리별 문제 리스트 가져오기
export async function requestProblemList({ state }, payload) {
  console.log("requestProblemList", state, payload);
  const url = `/problem/${payload.id}}`;
  return await axios.get(url);
}

// 문제 정답 맞추기
export async function requestProblemCheckAnswer({ state }, payload) {
  console.log("requestProblemCheckAnswer", state, payload);
  const url = "/problem/checkanswer";
  const body = payload;
  return await axios.post(url, body);
}

// 승인 대기중인 문제 리스트 가져오기
export async function requestProblemWait({ state }, payload) {
  console.log("requestProblemWait", state, payload);
  const url = "/problem/wait";
  const body = payload;
  return await axios.get(url, body);
}

// 관리자가 문제상태 변경하기
export async function requestProblemEvaluate({ state }, payload) {
  console.log("requestProblemEvaluate", state, payload);
  const url = "/problem/evaluate";
  const body = payload;
  return await axios.post(url, body);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 게시판 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 게시판 한개 가져오기
export async function requsetBoardInfo({ state }, payload) {
  console.log("requsetBoardInfo", state, payload);
  const url = `/board/${payload.id}`;
  return await axios.get(url);
}

// 게시판 리스트 가져오기
export async function requestBoardList({ state }, payload) {
  console.log("requestBoardList", state, payload);
  const url = "/board";
  return await axios.get(url);
}
