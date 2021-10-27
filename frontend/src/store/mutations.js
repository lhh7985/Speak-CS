export function setItem(state, item) {
  state.item = item;
}
export function setCategories(state, categories) {
  state.categories = categories;
}

export function setSelctedProblems(state, selectedProblems) {
  state.selectedProblems = selectedProblems;
}

export function setProblemResults(state, problemResults) {
  state.problemResults.add(problemResults);
}
