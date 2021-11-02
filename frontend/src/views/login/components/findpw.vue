<template lang="">
  <q-dialog>
    <q-card>
      <q-card-section class="findpw-title">
        <div class="row justify-center items-center">
          <div class="self-center">비밀번호찾기</div>
          <q-btn
            class="findpw-btn-close self-end"
            v-close-popup
            flat
            round
            dense
            icon="close"
          />
        </div>
      </q-card-section>
      <q-card-section class="align-center">
        <div>본인 이메일 아이디를 적어주세요</div>
      </q-card-section>

      <q-card-actions>
        <div class="row justify-center items-center">
          <q-input
            ref="email"
            v-model="state.email"
            :rules="state.rules.email"
            lazy-rules
            type="email"
            label="이메일"
          >
          </q-input>
        </div>
      </q-card-actions>
      <q-btn
        class="primary"
        outline
        style="color: goldenrod"
        label="인증요청"
        @click="requsetAuth"
      ></q-btn>
    </q-card>
  </q-dialog>
</template>
<script>
import "@/styles/findpw.scss";
import { ref, reactive } from "vue";
import { useStore } from "vuex";
export default {
  name: "login-findpw",
  setup(props, { emit }) {
    const store = useStore();
    const email = ref(null);

    const state = reactive({
      email: "",
      rules: {
        email: [
          (val) => (val != null && val !== "") || "필수입력 항목입니다.",
          (val) => isValidEmail(val) || "이메일형식에 맞지 않습니다.",
        ],
      },
    });
    const isValidEmail = (val) => {
      // eslint-disable-next-line
      const emailPattern = /^[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/;
      return emailPattern.test(val) || "이메일 형식에 맞지 않습니다.";
    };

    const emailAuthentication = () => {
      email.value.validate().then((success) => {
        if (success) {
          store
            .dispatch("root/")
            .then(
              (response) => {
                console.log(response);
              },
              (error) => {
                console.log(error);
              }
            )
            .catch((error) => {
              console.log(error);
            });
        }
      });
    };
    const requsetAuth = () => {
      console.log(state.email);
      emit("openemailcheck", state.email);
    };
    return {
      state,
      emailAuthentication,
      requsetAuth,
    };
  },
};
</script>
<style lang=""></style>
