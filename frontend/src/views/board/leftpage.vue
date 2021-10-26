<template>
  <div class="left-wrap">
    <div @click="mvBoardInfo">게시판 사용방법</div>
    <div class="board-list">
      <div @click="mvQuestion">
        <span>제목1</span>
        <span>작성자1</span>
      </div>
      <div @click="mvQuestion">
        <span>제목2</span>
        <span>작성자2</span>
      </div>
      <div @click="mvQuestion">
        <span>제목3</span>
        <span>작성자3</span>
      </div>
    </div>
  </div>
</template>
<script>
import { useRouter } from "vue-router";

export default {
  name: "board-left",
  setup() {
    const router = useRouter();
    const mvBoardInfo = () => {
      router.push({ name: "board-info" });
    };
    const mvQuestion = () => {
      router.push({ name: "board-question" });
    };

    return {
      mvBoardInfo,
      mvQuestion,
    };
  },
};
</script>
