<template>
  <div>
    <md-tabs class="md-transparent" md-alignment="fixed">
      <md-tab id="tab-favorites" md-label="나의 원고" to="/cartoonists"></md-tab>
      <md-tab id="tab-home" md-label="모든 작가" to="/cartoonists/competitors"></md-tab>
      <md-tab id="tab-pages" md-label="지목받은 작가" to="/cartoonists/applicants"></md-tab>
      <md-tab id="tab-posts" md-label="지목한 작가" to="/cartoonists/acceptors"></md-tab>
    </md-tabs>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'Cartoonist'
}
</script>
