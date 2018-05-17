<template>
  <div>
    <md-content>
      <div v-for="(path, index) in paths" v-bind:key="index">
        <img v-bind:src=path>
      </div>
    </md-content>
  </div>
</template>

<script>
export default {
  name: 'LoadImage',
  data: function () {
    return {
      paths: ['/static/logo.png', '/static/logo.png', '/static/logo.png', '/static/logo.png', '/static/logo.png']
    }
  }
}
</script>

<style scoped>
md-content {
  width: 400px;
  height: 400px;
  display: flex;
  justify-content: center;
  align-items: center;
}

div > img {
  max-width: 100%;
  max-height: 100%;
}
</style>
