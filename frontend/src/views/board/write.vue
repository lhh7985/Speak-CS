<template>
  <div>
    <div ref="ref_editor"></div>
    <button @click="saveBoard">저장</button>
    <div id="viewer"></div>
  </div>
</template>

<script>
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";

import Editor from "@toast-ui/editor";
import Viewer from "@toast-ui/editor/dist/toastui-editor-viewer";

import { ref, reactive, onMounted } from "vue";

export default {
  name: "board-write",
  setup() {
    const state = reactive({
      editor: null,
      viewer: null,
      editorText: "",
    });

    const ref_editor = ref(null);

    onMounted(() => {
      state.editor = new Editor({
        el: ref_editor.value,
        height: "500px",
        initialEditType: "markdown",
        // initialEditType: "wysiwyg",
        previewStyle: "vertical",
      });
      console.log(state.xeditor);
    });

    const saveBoard = () => {
      state.editorText = state.editor.getMarkdown();

      state.viewer = new Viewer({
        el: document.querySelector("#viewer"),
        initialValue: state.editorText,
      });
    };

    return {
      state,
      ref_editor,
      onMounted,
      saveBoard,
    };
  },
};
</script>
