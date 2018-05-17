import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Cartoonist from '@/components/Cartoonists/Cartoonist'
import Subscriber from '@/components/Subscribers/Subscriber'
import Manuscripts from '@/components/Cartoonists/Manuscripts/Manuscripts'
import Manuscript from '@/components/Cartoonists/Manuscripts/Manuscript'
import Applicant from '@/components/Cartoonists/Applicants/Applicant'
import Accepter from '@/components/Cartoonists/Accepters/Accepter'
import Competitor from '@/components/Cartoonists/Competitors/Competitor'

Vue.use(Router)
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    }, {
      path: '/manuscript/:id',
      name: 'Manuscript',
      component: Manuscript
    }, {
      path: '/subscribers',
      name: 'Subscribers',
      component: Subscriber
    }, {
      path: '/cartoonists',
      name: 'Cartoonist',
      component: Cartoonist,
      children: [
        {
          path: '',
          name: 'Manuscripts',
          component: Manuscripts
        }, {
          path: 'applicants',
          name: 'Applicant',
          component: Applicant
        }, {
          path: 'acceptors',
          name: 'Accepter',
          component: Accepter
        }, {
          path: 'competitors',
          name: 'Competitor',
          component: Competitor
        }
      ]
    }
  ]
})
