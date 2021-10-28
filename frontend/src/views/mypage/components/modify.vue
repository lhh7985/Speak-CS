<template>
  <div class="modify-wrap">
    <span class="modify-title">프로필 수정하기</span>
    <div class="form-wrap">
      <div class="modify-image">
        <img class="modi-img" :src="state.imageurl" />
        <label for="chooseFile" class="upload-btn">사진변경</label>
        <input
          type="file"
          id="chooseFile"
          name="chooseFile"
          accept="image/*"
          style="display: none"
          @change="loadf"
        />
      </div>
      <div class="qform">
        <q-form
          class="q-gutter-md form"
          ref="modify_form"
          @submit="onSubmit"
          @reset="onReset"
        >
          <q-input readonly filled label="성함" v-model="state.user.name" />
          <div class="modify-nick">
            <q-input
              filled
              class="nickinput"
              v-model="state.form.nickname"
              :rules="state.rules.nickname"
              lazy-rules
              label="닉네임 *"
              :placeholder="state.user.nickname"
            />
            <q-btn
              class="nickcheck"
              primary
              label="중복확인"
              @click="nickNameCheck"
            ></q-btn>
          </div>

          <q-input
            filled
            v-model="state.form.pass"
            :rules="state.rules.pass"
            type="password"
            label="비밀번호 *"
            :placeholder="state.user.pass"
          />

          <q-input
            filled
            v-model="state.form.passcheck"
            :rules="state.rules.passcheck"
            type="password"
            label="비밀번호 확인 *"
          />

          <q-input readonly filled label="이메일" v-model="state.user.email" />
        </q-form>
      </div>
    </div>
    <div class="submit-wrap">
      <q-btn color="primary" type="submit" label="수정하기" />
      <q-btn class="q-ml-sm" flat color="primary" type="reset" label="초기화" />
    </div>
  </div>
</template>
<script>
import { ref, reactive } from "vue";
// import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { useQuasar } from "quasar";

export default {
  name: "modify",
  setup() {
    const modify_form = ref(null);
    const store = useStore();
    const quasar = useQuasar();
    // const router = useRouter();
    const state = reactive({
      user: store.getters["root/getUser"],
      imageurl: require("../../../assets/malang.png"),
      file: null,
      form: {
        nickName: "",
        nickName_check: false,
        pass: "",
        passcheck: "",
      },
      rules: {
        nickName: [
          (val) => val != null || "필수입력 항목입니다.",
          (val) =>
            (val.length >= 2 && val.length <= 16) ||
            "2~16자리의 닉네임을 설정해주세요.",
        ],
        pass: [
          (val) => val !== null || "필수입력 항목입니다.",
          (val) =>
            isValidPass(val) ||
            "영문, 숫자, 특수문자를 포함한 8자리 이상의 비밀번호를 생성해주세요!",
        ],
        passcheck: [
          (val) => (val !== null && val.length > 0) || "필수입력 항목입니다.",
          (val) => isValidPassCheck(val) || "입력한 비밀번호와 맞지 않습니다.",
        ],
      },
    });
    /*ㅡㅡㅡㅡㅡ 검증 ㅡㅡㅡㅡㅡ*/
    const isValidPass = (val) => {
      const passPattern =
        /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
      return (
        passPattern.test(val) ||
        "영문, 숫자, 특수문자를 포함한 8자리 이상의 비밀번호를 생성해주세요!"
      );
    };
    const isValidPassCheck = (val) => {
      if (state.form.pass != val) {
        console.log(state.form.pass, val);
        return false;
      } else {
        return true;
      }
    };

    const nickNameCheck = () => {
      console.log(state.form.nickName);
      store
        .dispatch("root/requestUserNickNameCheck", state.form.nickName)
        .then(
          (response) => {
            console.log(response);
            state.form.nickName_success = true;
            nickNameSuccess();
          },
          (error) => {
            console.log(error.response.data);
            nickNameError();
          }
        )
        .catch((error) => {
          console.log(error);
        });
    };
    const nickNameSuccess = () => {
      quasar
        .dialog({
          title: "닉네임 중복확인",
          message: "사용가능한 닉네임입니다!",
        })
        .onOk(() => {
          console.log("OK");
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };
    const nickNameError = () => {
      quasar
        .dialog({
          title: "닉네임 중복확인",
          message: "중복된 닉네임입니다.",
        })
        .onOk(() => {
          console.log("OK");
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };

    /*ㅡㅡㅡㅡㅡ 버튼 ㅡㅡㅡㅡㅡ*/
    const onSubmit = () => {};
    const onReset = () => {
      state.form.nickName = null;
      state.form.pass = null;
      state.form.passcheck = null;
    };

    const loadf = () => {
      var file = document.getElementById("chooseFile");
      console.log(file.files[0]);
      state.imageurl = URL.createObjectURL(file.files[0]);
    };
    return {
      modify_form,
      state,
      onSubmit,
      onReset,
      loadf,
      nickNameCheck,
      nickNameSuccess,
      nickNameError,
    };
  },
};
</script>
