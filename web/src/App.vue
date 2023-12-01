<template>
  <div>
    Hello world
    <div>
      学校：{{ school_name }}
    </div>
    <div>
      课程：{{ class_name }}
    </div>
  </div>
</template>

<script>
  import $ from 'jquery';
  import { ref } from 'vue';
  
  export default {
    name: "App",
    setup: () => {
      let school_name = ref("");
      let class_name = ref("");

      $.ajax({
        url: "http://127.0.0.1:8000/test2/getinfo/",
        type: "get",
        success: resp => {
          // console.log(resp);
          school_name.value = resp.school;
          class_name.value = resp.class;
        }
      });

      return {
        school_name,
        class_name
      }
    }
  }
</script>

<style>
body {
  background-image: url("@/assets/background.png");
  background-size: cover;
}
</style>