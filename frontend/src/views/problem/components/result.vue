<template lang="">
  <div>
    <teamplate
      v-for="(category, index) in state.problemResults"
      :key="index"
      unelevated
    >
      {{ index + 1 }} . {{ state.problems[index].description }}<br />
      맞은 문제: {{ category.proper }}<br />
      틀린 문제: {{ category.wrong }}<br /><br />
    </teamplate>
    <q-btn unelevated flat @click="checkProblem()">문제확인하기</q-btn>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";

export default {
  name: "problem-result",
  setup() {
    const store = useStore();
    const state = reactive({
      problemResults: computed(() => store.getters["root/getProblemResults"]),
      problems: computed(() => store.getters["root/getSelectedProblems"]),
    });

    const checkProblem = () => {
      console.log(state.problems);
    };

    return {
      state,
      checkProblem,
    };
  },
};
</script>
<style lang=""></style>
