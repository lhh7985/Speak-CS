/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
export function getUser(state) {
  return state.user;
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 문제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
export function getCategories(state) {
  return state.categories;
}

export function getSelectedProblems(state) {
  return state.selectedProblems;
}

export function getProblemResults(state) {
  return state.problemResults;
}
