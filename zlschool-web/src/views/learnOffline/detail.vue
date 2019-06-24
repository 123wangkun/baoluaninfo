<template>
  <div class="learnOfflineDetail">
    <div class="title">{{ data.title }}</div>
    <div class="sndBox">
      <div>学习时间:{{ data.study_time |timeFilter }}</div>
      <div>状态:{{ data.state|stateFilter }}</div>
    </div>
    <div class="content" v-html="data.content"/>

  </div>
</template>

<script>
import moment from 'moment'
export default {
  name: 'LearnOfflineDetail',
  filters: {
    timeFilter(date) {
      return moment(date).format('YYYY-MM-DD  HH:mm')
    },
    stateFilter(state) {
      return state === '1' ? '进行中' : '已结束'
    }
  },
  data() {
    return {
      data: JSON.parse(localStorage.getItem('learnOfflineDetail'))
    }
  }
}
</script>

<style lang="scss" scoped>
.learnOfflineDetail {
  padding: 20px 8px 8px 8px;
  width: 960px;
  margin: 0 auto;
  background: white;
  min-height: calc(100vh - 110px);
  .title {
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    margin-bottom: 20px;
  }
  .sndBox {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    div {
      flex: 1;
      text-align: center;
    }
  }
  .content {
    margin: 0;
    p {
      margin: 0;
      padding: 0;
      margin-block-start: 0;
      margin-block-end: 0;
    }
  }
}
</style>
