import axios from "@/lib/axios";

// http://localhost:8080/swagger-ui.html

// 게시판 한개 가져오기
export function requsetBoardInfo({ state }, payload) {
  console.log("requsetBoardInfo", state, payload);
  const url = `/board/${payload.id}`;
  return axios.get(url);
}
// 게시판 리스트 가져오기
export function requestBoardList({ state }, payload) {
  console.log("requestBoardList", state, payload);
  const url = "/board";
  return axios.get(url);
}
