<template lang="">
  <div>
    문제설명하는 곳입니다
    <!-- <div>{{ category.id }}</div>
    <div>{{ category.description }}</div>
    <div>{{ category.name }}</div> -->
    <q-btn flat @click="selectProblem">시작하기</q-btn>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  name: "problem-description",
  props: {
    category: {
      type: Object,
    },
  },
  setup(props) {
    console.log(props.category);
    const store = useStore();
    const router = useRouter();

    const selectProblem = () => {
      store
        .dispatch("root/requestProblemList", props.category.id)
        .then((response) => {
          store.commit("root/setSelctedProblems", response.data);
          router.push({ name: "problem-solve" });
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
