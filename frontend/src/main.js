import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./lib/store";
import { Quasar } from "quasar";
import quasarUserOptions from "./quasar-user-options";
import VueApexCharts from "vue3-apexcharts";
createApp(App)
  .use(Quasar, quasarUserOptions)
  .use(store)
  .use(router)
  .use(VueApexCharts)
  .mount("#app");
