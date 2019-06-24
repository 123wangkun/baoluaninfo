<template>
  <ul class="NoticeList">
    <li v-for="(item,index) in list" :key="index" @click="openDetail(item)">
      <img :src="item.IMG_PATH|imgUrlFilter" class="img">
      <img v-if="item.SPREAD2 === '1'" class="sc" src="../../../assets/img/hot.png">
      <div class="title">{{ item.TITLE }}</div>
      <div class="time">{{ item.CREATEDATE|timeFilter }}</div>
    </li>
  </ul>
</template>

<script>
import moment from 'moment'
export default {
  name: 'NoticeList',
  filters: {
    timeFilter(date) {
      return moment(date).format('YYYY-MM-DD  HH:mm')
    }
  },
  props: {
    list: {
      type: Array,
      required: true
    }
  },
  methods: {
    openDetail(item) {
      localStorage.setItem('noticeDetail', JSON.stringify(item))
      this.$router.push({ path: '/noticeDetail' })
    }
  }
}
</script>

<style lang="scss" scoped>
.NoticeList {
  padding: 20px;
  margin: 0;
  li {
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-bottom: 10px;
    cursor: pointer;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
    &:active {
      background: #eee;
    }
    &:hover {
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    }
    &:first-child {
      // margin-top: 10px;
    }
    &:last-child {
      margin: 0;
    }
    .img {
      width: 80px;
      height: 60px;
      background: white;
      margin-right: 10px;
    }
    .sc {
      width: 20px;
      margin-right: 5px;
    }
    .title {
      flex: 1;
    }
    .time {
      color: #898989;
      font-size: 14px;
    }
  }
}
</style>
