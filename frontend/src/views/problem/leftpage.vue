<template>
  <div class="left-wrap">
<<<<<<< HEAD
    <div>
      <q-btn flat @click="mvProblemInfo">문제카테고리</q-btn>
    </div>
    <div>
      <q-btn
        v-for="(category, index) in state.categories"
        :key="index"
        unelevated
        flat
        @click="mvProblemDescription(category)"
        ><div style="display: block">{{ category.name }}</div>
      </q-btn>
=======
    <div class="open-left-wrap">
      <div>
        <q-btn flat @click="mvProblemInfo">문제카테고리</q-btn>
      </div>
      <div>
        <q-btn
          v-for="(category, index) in state.categories"
          :key="index"
          unelevated
          flat
          @click="mvProblemDescription(category)"
          >{{ category.name }}</q-btn
        >
      </div>
>>>>>>> e1714b943dbfef5dc5c02a75c9bfcd17d8cb9c0d
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "problem-left",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      categories: computed(() => store.getters["root/getCategories"]),
    });
    const mvProblemDescription = (category) => {
      router.push({
        name: "problem-description",
        params: {
          id: category.id,
          description: category.description,
          name: category.name,
        },
      });
    };
    const mvProblemInfo = () => {
      router.push({ name: "problem-info" });
    };
    return {
      state,
      mvProblemDescription,
      mvProblemInfo,
    };
  },
};
</script>
<style lang=""></style>
