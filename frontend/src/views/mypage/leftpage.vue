<template>
  <div class="left-wrap">
    <div class="mypage-left">
      <div class="profile-wrap">
        <img class="profile-img" src="../../assets/malang.png" />
        <span class="user-name text-h4 text-bold">{{ state.user.name }}</span>
      </div>
      <div class="mypage-menu text-h6">
        <div id="menuBtn1" class="menus click-menu" @click="mvProfile">
          프로필
        </div>
        <div id="menuBtn2" class="menus" @click="mvChart">차트</div>
        <div id="menuBtn3" class="menus" @click="mvBoard">나의 게시글</div>
        <div id="menuBtn4" class="menus" @click="mvAlarm">알림</div>
      </div>
    </div>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { onBeforeMount, reactive } from "vue";
import { useRouter } from "vue-router";
import "../../styles/mypage.scss";

export default {
  name: "mypage-left",
  setup() {
    const store = useStore();
    const router = useRouter();

    const state = reactive({
      user: null,
    });
    onBeforeMount(() => {
      state.user = store.getters["root/getUser"];
      console.log(state.user);
    });

    const mvProfile = () => {
      removeColor();
      addColor("menuBtn1");
      router.push({ name: "mypage-profile" });
    };
    const mvChart = () => {
      removeColor();
      addColor("menuBtn2");
      router.push({ name: "mypage-chart" });
    };
    const mvBoard = () => {
      removeColor();
      addColor("menuBtn3");
      router.push({ name: "mypage-board" });
    };
    const mvAlarm = () => {
      removeColor();
      addColor("menuBtn4");
      router.push({ name: "mypage-alarm" });
    };

    const removeColor = () => {
      const menus = document.getElementsByClassName("menus");
      for (var i = 0; i < menus.length; i++) {
        menus[i].classList.remove("click-menu");
      }
    };
    const addColor = (id) => {
      const btn = document.getElementById(id);
      btn.classList.add("click-menu");
    };
    return {
      state,
      onBeforeMount,
      mvProfile,
      mvChart,
      mvBoard,
      mvAlarm,
    };
  },
};
</script>
