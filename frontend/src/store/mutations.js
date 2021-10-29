/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
export function setUser(state, user) {
  state.user = user;
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 문제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
export function setCategories(state, categories) {
  state.categories = categories;
}

export function setSelctedProblems(state, selectedProblems) {
  state.selectedProblems = selectedProblems;
}

export function setProblemResults(state, problemResults) {
  state.problemResults.push(problemResults.data);
}

export function setProblemResultsInit(state) {
  state.problemResults.length = 0;
}
