<template lang="">
  <div>
    <div>{{ name }}</div>
    <q-btn flat @click="selectProblem">시작하기</q-btn>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  name: "problem-description",
  props: {
    id: String,
    description: String,
    name: String,
  },
  setup(props) {
    const store = useStore();
    const router = useRouter();

    const selectProblem = () => {
      store
        .dispatch("root/requestProblemList", props.id)
        .then((response) => {
          store.commit("root/setProblemResultsInit");
          store.commit("root/setSelctedProblems", response.data);
          router.push({
            name: "problem-solve",
            query: { num: 0, id: response.data[0].id },
          });
        })
        .catch((error) => {
          console.log(error);
        });
    };
    return {
      selectProblem,
    };
  },
};
</script>
<style lang=""></style>
