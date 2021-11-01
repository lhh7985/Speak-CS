<template lang="">
  <div class="right-wrap">
    <div class="shadow"></div>
    <div class="main-cover">
      <div>개발자를 위한 CS 역량 강화</div>
      <div class="cstitle">CS의 정석</div>
    </div>
    <div class="voice">
      <q-btn
        class="speech-to-text"
        flat
        style="color: #fc3f2d"
        @click="startSpeechToTxt"
        >Speech to text</q-btn
      >
      <p>{{ transcription_ }}</p>
    </div>
  </div>
</template>
<script>
export default {
  name: "logout-cover-right",
  data() {
    return {
      runtimeTranscription_: "",
      transcription_: [],
      lang_: "ko-KR",
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
        this.runtimeTranscription_ = "";
        recognition.stop();
      });
      recognition.start();
    },
  },
};
</script>
<style lang=""></style>
