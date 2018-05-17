import Vue from 'vue'
import App from './App'
import router from './router'
import VueMaterial from 'vue-material'
import VueResource from 'vue-resource'
import VueLazyload from 'vue-lazyload'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'

Vue.use(VueMaterial)
Vue.use(VueResource)
Vue.use(VueLazyload, {
  preLoad: 1.3,
  error: 'static/logo.png',
  loading: 'dist/loading.gif',
  attempt: 1,
  listenEvents: [ 'scroll' ]
})

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
