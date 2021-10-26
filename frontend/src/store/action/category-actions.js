import axios from "@/lib/axios";

// http://localhost:8080/swagger-ui.html

// 모든 카테고리 정보 가져오기
export function requsetCategoryCreate({ state }, payload) {
  console.log("requsetCategoryCreate", state, payload);
  const url = "/category";
  return axios.get(url, body);
}
