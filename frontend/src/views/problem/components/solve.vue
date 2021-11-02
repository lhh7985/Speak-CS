<template>
  <div>
    <h2>{{ state.problems[$route.query.num].description }}</h2>
    <q-btn
      unelevated
      flat
      id="menuBtn1"
      class="speech-to-text"
      @click="startSpeechToTxt"
      >Speech to text</q-btn
    >
    <p>{{ lastTranscription }}</p>

    <q-btn
      unelevated
      flat
      @click="checkAnswer($route.query.id, $route.query.num, lastTranscription)"
      >제출하기</q-btn
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
  methods: {
    startSpeechToTxt() {
      console.log("start text");
      // initialisation of voicereco
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
            console.log(response);
            this.$store.commit("root/setProblemResults", response);
            num++;
            if (num < this.state.problems.length) {
              console.log(num);
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
