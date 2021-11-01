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
    <q-btn unelevated flat @click="backToInfo()">뒤로가기</q-btn>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "problem-result",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      problemResults: computed(() => store.getters["root/getProblemResults"]),
      problems: computed(() => store.getters["root/getSelectedProblems"]),
    });

    const backToInfo = () => {
      store.commit("root/setProblemResultsInit");
      router.push({
        name: "problem",
      });
    };

    return {
      state,
      backToInfo,
    };
  },
};
</script>
<style lang=""></style>
