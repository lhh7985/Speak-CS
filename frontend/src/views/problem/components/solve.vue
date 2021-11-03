<template>
  <div>
    <h4>
      <b>{{ state.problems[$route.query.num].description }}</b>
    </h4>
    <q-btn
      unelevated
      flat
      id="menuBtn1"
      icon="mic_outline"
      class="speech-to-text"
      color="red"
      size="40px"
      @click="startSpeechToTxt"
    ></q-btn>

    <p>{{ lastTranscription }}</p>
    <!-- <img class="profile-img" src="@/assets/malang.png" /> -->

    <q-btn
      unelevated
      flat
      @click="checkAnswer($route.query.id, $route.query.num, lastTranscription)"
      ><b>제출하기</b></q-btn
    >
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";

export default {
  name: "login-nav",
  data() {
    return {
      runtimeTranscription_: "",
      transcription_: [],
      lastTranscription: "",
      lang_: "ko-KR",
      img: "mic_outline",
    };
  },
  setup() {
    const store = useStore();
    const state = reactive({
      problems: computed(() => store.getters["root/getSelectedProblems"]),
    });

    return {
      state,
    };
  },
  watch: {
    recordFlag: function () {
      this.img = "mic_off_outline";
    },
  },
  methods: {
    startSpeechToTxt() {
      // initialisation of voicereco
      this.recordFlag = false;
      const recognition = new window.SpeechRecognition();
      recognition.lang = this.lang_;
      recognition.interimResults = true;

      // event current voice reco word
      recognition.addEventListener("result", (event) => {
        var text = Array.from(event.results)
          .map((result) => result[0])
          .map((result) => result.transcript)
          .join("");
        this.runtimeTranscription_ = text;
      });
      // end of transcription
      recognition.addEventListener("end", () => {
        this.recordFlag = true;
        this.transcription_.push(this.runtimeTranscription_);
        this.lastTranscription = this.runtimeTranscription_;
        this.runtimeTranscription_ = "";
        recognition.stop();
      });
      recognition.start();
    },
    checkAnswer(id, num) {
      const payload = {
        id: id,
        myAnswer: this.lastTranscription,
      };
      (this.lastTranscription = ""),
        this.$store
          .dispatch("root/requestProblemCheckAnswer", payload)
          .then((response) => {
            this.$store.commit("root/setProblemResults", response);
            num++;
            if (num < this.state.problems.length) {
              this.$router.push({
                name: "problem-solve",
                query: {
                  num: num,
                  id: this.state.problems[num].id,
                },
              });
            } else {
              this.$router.push({
                name: "problem-result",
              });
            }
          });
    },
  },
};
</script>
<style>
.speech-to-text {
  border: 1px solid black;
  z-index: 10;
}
.speech-to-text:hover {
  cursor: pointer;
}
</style>
