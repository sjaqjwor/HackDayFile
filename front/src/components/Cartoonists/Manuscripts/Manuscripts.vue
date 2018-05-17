<template>
  <div>
    <md-card v-for="manuscript in manuscripts" v-bind:key="manuscript.id" @click.native="detail(manuscript.id)"
             class="md-primary"
             md-theme="orange-card" md-with-hover>
      <md-ripple>
        <md-card-header>
          <div class="md-title">{{ manuscript.title }}</div>
          <div class="md-subhead">{{ manuscript.turn }} 회차 </div>
          <div class="md-subhead">{{ manuscript.updatedAt }}</div>
        </md-card-header>

        <md-card-content>
          {{ manuscript.summary }}
        </md-card-content>
      
      </md-ripple>
    </md-card>
    <md-button class="md-raised" @click="add">Add</md-button>
  </div>
</template>

<script>

export default {
  name: 'Manuscripts',
  data: function () {
    return {
      manuscripts: []
    }
  },
  methods: {
    detail (id) {
      this.$router.push({name: 'Manuscript', params: {id: id}})
    },
    add () {
      this.$router.push({name: 'CreateManuscript'})
    }
  },
  created: function () {
    this.$http.get('/api/manuscripts')
      .then((result) => {
        this.manuscripts = result.body
      })
  }
}
</script>

<style scoped>
.md-card {
  width: 320px;
  margin: 4px;
  display: inline-block;
  vertical-align: top;
}
</style>
