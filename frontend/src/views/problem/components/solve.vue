<template>
  <div>
    <h2>{{ state.problems[$route.query.num].description }}</h2>
    <q-btn
      unelevated
      flat
      @click="checkAnswer($route.query.id, $route.query.num)"
      >제출하기</q-btn
    >
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";
import { useRouter } from "vue-router";
export default {
  name: "login-nav",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      problems: computed(() => store.getters["root/getSelectedProblems"]),
    });
    const checkAnswer = (id, num) => {
      console.log(id);
      const payload = {
        id: id,
        myAnswer: "스프링",
      };
      store
        .dispatch("root/requestProblemCheckAnswer", payload)
        .then((response) => {
          console.log(response);
          store.commit("root/setProblemResults", response);
          num++;
          if (num < state.problems.length) {
            router.push({
              name: "problem-solve",
              query: {
                num: num,
                id: state.problems[num].id,
              },
            });
          } else {
            router.push({
              name: "problem-result",
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    };
    return {
      state,
      checkAnswer,
    };
  },
};
</script>
<style lang=""></style>
