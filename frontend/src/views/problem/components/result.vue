<template lang="">
  <div style="overflow: auto; width: 100%">
    <teamplate
      v-for="(category, index) in state.problemResults"
      :key="index"
      unelevated
    >
      <h4>
        <b>{{ index + 1 }}. {{ state.problems[index].description }}</b>
      </h4>
      <div style="display: inline-block">
        <h6 style="magin: 0px"><b>정답율</b></h6>
        <apexchart
          width="350"
          :options="chartOptions"
          type="donut"
          :series="state.series[index]"
        />
      </div>
      &nbsp;&nbsp;&nbsp;
      <div style="display: inline-block">
        <h6 style="color: red"><b>미언급 단어</b></h6>
        <li v-for="item in category.wrong" v-bind:key="item">
          {{ item }}
        </li>
      </div>
    </teamplate>
    <br />
    <q-btn unelevated flat @click="check()">체크</q-btn>

    <q-btn unelevated flat @click="backToInfo()">뒤로가기</q-btn>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";
import { useRouter } from "vue-router";
export default {
  name: "problem-result",
  data() {
    return {
      chartOptions: {
        chart: {
          width: 380,
          type: "pie",
        },
        labels: ["언급", "미언급"],
        responsive: [
          {
            breakpoint: 480,
            options: {
              chart: {
                width: 200,
              },
              legend: {
                position: "bottom",
              },
            },
          },
        ],
      },
    };
  },

  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      problemResults: computed(() => store.getters["root/getProblemResults"]),
      problems: computed(() => store.getters["root/getSelectedProblems"]),
      series: computed(() => store.getters["root/getSeries"]),
    });

    const backToInfo = () => {
      store.commit("root/setProblemResultsInit");
      router.push({
        name: "problem",
      });
    };

    return {
      state,
      backToInfo,
    };
  },
  methods: {
    check() {
      console.log(this.state.series);
    },
  },
};
</script>
<style lang=""></style>
