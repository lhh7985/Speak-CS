<template>
  <div>
    문제리스트가 나올페이지입니다.
    <q-btn unelevated flat @click="checkAnswer()">제출하기</q-btn>
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
      categories: computed(() => store.getters["root/getSelectedProblems"]),
    });

    const checkAnswer = (my_ans) => {
      store
        .dispatch("root/requestProblemCheckAnswer", my_ans)
        .then((response) => {
          store.commit("root/setProblemResults", response);
          router.push({ name: "problem-solve" });
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
