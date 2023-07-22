import {createApp} from 'vue'
import './style.css'
import App from './App.vue'
// ElementPlus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 路由
import router from './router'

window[router] = router
const app:App = createApp(App)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
