<template>
  <div class="makeProb-wrap">
    <div class="makeProb-title">문제만들기</div>
    <div class="">
      <form class="makeProb-form">
        <div>
          <label class="label" for="category">문제 카테고리</label>
          <select v-model="state.problem.categoryId" id="category">
            <option
              v-for="(category, idx) in state.categories"
              :key="idx"
              :value="category.id"
            >
              {{ category.name }}
            </option>
          </select>
        </div>
        <div>
          <label class="label" for="desc">문제</label>
          <input id="desc" v-model="state.problem.description" />
        </div>
        <div id="box">
          <div>
            <label class="label" for="answerinput">정답</label>
            <input id="answerinput" class="answer" />
            <input
              class="addBtn"
              type="button"
              value="추가"
              @click="addInput"
            />
          </div>
        </div>
      </form>
      <button class="makeBtn" @click="makeProblem">생성하기</button>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { reactive } from "vue";
import { useRouter } from "vue-router";

export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      categories: store.getters["root/getCategories"],
      problem: {
        userId: null,
        categoryId: null,
        description: null,
        answers: [],
      },
    });

    const addInput = () => {
      console.log("Add");
      const box = document.getElementById("box");
      const newDiv = document.createElement("div");
      newDiv.innerHTML = "<input  class='answer'/>";

      box.appendChild(newDiv);
    };

    const makeProblem = () => {
      console.log(state.problem.description);
      if (state.problem.categoryId == null) {
        alert("카테고리를 지정해주세요.");
      } else if (state.problem.description == null) {
        alert("문제설명은 필수입니다.");
      } else if (
        state.problem.description.length <= 4 ||
        state.problem.description.length > 400
      ) {
        alert(
          "문제 길이는 5글자 이상 400글자 이하입니다. 현재 글자는" +
            state.problem.description.length +
            "글자입니다"
        );
      } else {
        const answers = document.getElementsByClassName("answer");

        state.problem.userId = store.getters["root/getUser"].id;
        for (var i = 0; i < answers.length; i++) {
          // 빈 칸이 아닐때만 답 저장
          if (answers[i].value != "") {
            state.problem.answers.push(answers[i].value);
          }
        }
        if (state.problem.answers.length == 0) {
          alert("정답 입력은 필수입니다.");
        }
        store
          .dispatch("root/requsetProblemCreate", state.problem)
          .then((response) => {
            console.log(response);
            alert("문제 생성이 완료되었습니다. 승인 후 등록됩니다.");
            state.problem.categoryId = null;
            state.problem.description = null;
            state.problem.answers = [];
            document.getElementById("answerinput").value = "";
            router.push({ name: "mypage-prob" });
          })
          .catch((err) => {
            console.log(err);
          });
      }
    };

    return {
      state,
      // saveIndex,
      makeProblem,
      addInput,
    };
  },
};
</script>
