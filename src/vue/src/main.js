import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import vuex from 'vuex'
import vuetify from 'vuetify'
import cookies from 'vue-cookies'

Vue.config.productionTip = false
// state 관리를 하기 위한 vuex
Vue.use(vuex)

new Vue({
  router,
  store,
  axios,
  vuex,
  vuetify,
  cookies,
  render: h => h(App),
  component: {

  }
}).$mount('#app')
