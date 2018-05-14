<template>
  <div>
    <LoadImage :no="no"></LoadImage>

    <md-content>
      <form>
        <div class="md-layout md-gutter">
          <div class="md-layout-item md-small-size-100">
            <md-field>
              <md-input v-model="manuscript.summary" placeholder="주제"> {{ manuscript.summary }} </md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100">
            <md-field>
              <label>작가 한마디..</label>
              <md-textarea v-model="manuscript.summary">{{ manuscript.summary }}</md-textarea>
            </md-field>
          </div>
        </div>

        <div class="md-layout-item md-small-size-100">
          <md-field>
            <md-file v-model="manuscript.files" multiple/>
          </md-field>
        </div>

        <md-button type="submit" class="md-primary">수정</md-button>
      </form>
    </md-content>
  </div>
</template>

<script>
import LoadImage from './LoadImage.vue'

export default {
  name: 'Manuscript',
  components: {LoadImage},
  data: function () {
    return {
      id: this.$route.params.id,
      manuscript: {},
      no: []
    }
  },
  created: function () {
    this.$http.get('/api/manuscripts/' + this.id)
      .then((result) => {
        this.manuscript = result.body
      }, (error) => {
        console.error(error.data)
      })
  },
  methods: {
  }
}
</script>

<style scoped>
div {
  margin-left: 100px;
  margin-right: 100px;
}
</style>
