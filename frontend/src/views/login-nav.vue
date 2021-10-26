<template>
  <div>
    <q-btn unelevated flat class="menu-btn">소개</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvProblemCategory"
      >문제풀기</q-btn
    >
    <q-btn unelevated flat class="menu-btn" @click="mvBoard">게시판</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvMypage">마이페이지</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvLogout">로그아웃</q-btn>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";

// import "../styles/cover.scss";

export default {
  name: "login-nav",
  setup(props, { emit }) {
    const store = useStore();
    const router = useRouter();
    const mvLogout = () => {
      alert("로그아웃 될거에요");
      router.push({ name: "logout-cover" });
      emit("logout");
    };
    const mvMypage = () => {
      router.push({ name: "mypage-profile" });
    };
    const mvProblemCategory = () => {
      store
        .dispatch("root/requsetCategoryCreate")
        .then((response) => {
          store.commit("root/setCategories", response.data);
          router.push({ name: "problem-category" });
        })
        .catch((error) => {
          console.log(error);
        });
    };
    const mvBoard = () => {
      router.push({ name: "board" });
    };
    return {
      mvLogout,
      mvMypage,
      mvProblemCategory,
      mvBoard,
    };
  },
};
</script>
<style lang=""></style>
