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
  const newData = [];
  newData.push(problemResults.data.proper.length);
  newData.push(problemResults.data.wrong.length);
  state.series.push(newData);
}

export function setProblemResultsInit(state) {
  state.problemResults.length = 0;
  state.series.length = 0;
}
