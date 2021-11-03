<template>
  <div class="left-wrap">
    <div class="open-left-wrap">
      <div class="mypage-left">
        <div class="profile-wrap">
          <img class="profile-img" src="../../assets/malang.png" />
          <span class="user-name text-h4 text-bold">{{ state.user.name }}</span>
        </div>
        <div class="mypage-menu text-h6">
          <div id="leftBtn1" class="myleft click-myleft" @click="mvChart">
            차트
          </div>
          <div id="leftBtn2" class="myleft" @click="mvModify">프로필 수정</div>
          <div id="leftBtn3" class="myleft" @click="mvBoard">나의 게시글</div>
          <div id="leftBtn4" class="myleft" @click="mvProb">문제 작성</div>
          <div id="leftBtn5" class="myleft" @click="mvAlarm">알림</div>
        </div>
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

    const mvChart = () => {
      removeBolder();
      addBolder("leftBtn1");
      router.push({ name: "mypage-chart" });
    };
    const mvModify = () => {
      removeBolder();
      addBolder("leftBtn2");
      router.push({ name: "mypage-modify" });
    };
    const mvBoard = () => {
      removeBolder();
      addBolder("leftBtn3");
      router.push({ name: "mypage-board" });
    };
    const mvProb = () => {
      removeBolder();
      addBolder("leftBtn4");
      router.push({ name: "mypage-prob" });
    };

    const mvAlarm = () => {
      removeBolder();
      addBolder("leftBtn5");
      router.push({ name: "mypage-alarm" });
    };

    const removeBolder = () => {
      const myleft = document.getElementsByClassName("myleft");
      for (var i = 0; i < myleft.length; i++) {
        console.log(i);
        myleft[i].classList.remove("click-myleft");
      }
    };
    const addBolder = (id) => {
      console.log(id);
      const btn = document.getElementById(id);
      btn.classList.add("click-myleft");
    };
    return {
      state,
      onBeforeMount,
      mvChart,
      mvModify,
      mvBoard,
      mvProb,
      mvAlarm,
    };
  },
};
</script>
