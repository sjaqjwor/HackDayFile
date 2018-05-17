<template>
  <div>
    <md-content>
      <div v-for="(path, key, index) in paths" v-bind:key="index">
        <img v-lazy="path">
      </div>
    </md-content>
  </div>
</template>

<script>
export default {
  name: 'LoadImage',
  props: ['id'],
  data: function () {
    return {
      paths: []
    }
  },
  methods: {
    loadFild: function () {
      this.$http.get('/api/manuscripts/' + this.id)
        .then((result) => {
          this.paths = result.body.imagePath
        }, (error) => {
          console.error(error.data)
        })
    }
  },
  created () {
    this.loadFild()
  },
  watch: {

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
