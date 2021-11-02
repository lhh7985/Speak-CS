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
        <div>{{ email }}로 인증번호를 발송했습니다.</div>
        <div>발송에 최소1분~최대5분 소요시간이 있으니 기다려주세요</div>
        <div>인증번호는 "몇분"뒤 폐기됩니다.</div>
        <div>다시 요청하려면 아래버튼을 눌러주세요</div>
        <q-btn
          class="primary"
          outline
          style="color: goldenrod"
          label="인증요청"
          @click="emailAuthentication"
        ></q-btn>
      </q-card-section>

      <q-card-actions>
        <div class="row justify-center items-center">
          <q-input ref="auth" v-model="state.auth" label="인증번호"> </q-input>
          <q-btn
            class="primary"
            outline
            style="color: goldenrod"
            label="인증"
            @click="authentication"
          ></q-btn>
        </div>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>
<script>
import { reactive } from "vue";
export default {
  name: "login-emailcheck",
  props: {
    email: String,
  },
  setup(props, { emit }) {
    const state = reactive({
      auth: "",
    });
    const emailAuthentication = () => {
      console.log("emailAuthentication");
    };
    const authentication = () => {
      emit("openupdatepw");
    };
    return {
      state,
      emailAuthentication,
      authentication,
    };
  },
};
</script>
<style lang=""></style>
