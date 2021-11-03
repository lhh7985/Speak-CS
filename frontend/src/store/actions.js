import axios from "@/lib/axios";

// http://localhost:8080/swagger-ui.html

// ! {state}, console삭제하면 payload제대로 안넘어감
/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 생성하기
export async function requestUserRegist({ state }, payload) {
  console.log("requestUserRegist", state, payload);
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

// 닉네임 중복검사
export async function requestUserNickNameCheck({ state }, payload) {
  console.log("requestUserNickNameCheck", state, payload);
  const url = `/user/${payload}`;
  return await axios.get(url);
}

// 유저정보 가져오기
export async function requsetUserInfo({ state }, payload) {
  console.log("requsetUserInfo", state, payload);
  const url = "/user/me";
  const header = {
    headers: {
      Authorization: `Bearer ${payload}`,
    },
  };
  return await axios.get(url, header);
}

// 유저정보 리스트 가져오기
export async function requestUserList() {
  const url = "/user/all";
  return await axios.get(url);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 카테고리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 모든 카테고리 정보 가져오기
export async function requsetCategoryList() {
  const url = "/category";
  return await axios.get(url);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 문제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 문제만들기
export async function requsetProblemCreate(payload) {
  const url = "/problem/make";
  const body = payload;
  return await axios.post(url, body);
}

// 카테고리별 문제 리스트 가져오기
export async function requestProblemList({ state }, payload) {
  console.log("requestProblemList", state, payload);
  const url = `/problem/${payload}`;
  return await axios.get(url);
}

// 문제 정답 맞추기
export async function requestProblemCheckAnswer({ state }, payload) {
  console.log("requestProblemCheckAnswer", state, payload);
  const url = "/problem/checkanswer";
  return await axios.post(url, payload);
}

// 승인 대기중인 문제 리스트 가져오기
export async function requestProblemWait() {
  const url = "/problem/wait";
  return await axios.get(url);
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
export async function requestBoardList() {
  const url = "/board";
  return await axios.get(url);
}
