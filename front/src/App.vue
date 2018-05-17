<template>
  <div id="app">
    <md-toolbar class="md-primary" md-elevation="1">
      <h3 style="flex: 1">
        <md-button to="/" class="md-title">Naver Webtoon</md-button>
      </h3>
      <md-button to="/cartoonists">작가용</md-button>
      <md-button to="/subscribers">독자용</md-button>
    </md-toolbar>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
</style>
