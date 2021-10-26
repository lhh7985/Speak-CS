<template lang="">
  <div>
    <LeftPage></LeftPage>
    <RightPage></RightPage>
  </div>
</template>
<script>
import { onBeforeMount } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import LeftPage from "./leftpage.vue";
import RightPage from "./rightpage.vue";
export default {
  name: "problem",
  components: {
    LeftPage,
    RightPage,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    onBeforeMount(() => {
      store
        .dispatch("root/requsetCategoryList")
        .then((response) => {
          store.commit("root/setCategories", response.data);
          router.push({ name: "problem-info" });
        })
        .catch((error) => {
          console.log(error);
        });
    });
    return {
      onBeforeMount,
    };
  },
};
</script>
<style lang=""></style>
