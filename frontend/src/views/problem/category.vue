<template>
  <div>
    <template v-for="(category, index) in state.categories" :key="index">
      <q-btn unelevated flat @click="selectProblem(category.id)">{{
        category.name
      }}</q-btn>
    </template>
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
      categories: computed(() => store.getters["root/getCategories"]),
    });

    const selectProblem = (id) => {
      store
        .dispatch("root/requestProblemList", id)
        .then((response) => {
          store.commit("root/setSelctedProblems", response.data);
          router.push({ name: "problem-solve" });
        })
        .catch((error) => {
          console.log(error);
        });
    };

    return {
      state,
      selectProblem,
    };
  },
};
</script>
<style lang=""></style>
