<template>
  <ul class="NoticeMoreList">
    <li v-for="(item,index) in list" :key="index" @click="openDetail(item)">
      <!-- <img :src="item.IMG_PATH|imgUrlFilter" :onerror="defaultImg" class="img"> -->
      <div class="point"/>
      <span class="dept">公司动态</span>
      <!-- <img v-if="item.SPREAD2 === '1'" class="sc" src="../../../assets/img/hot.png"> -->
      <img :style="item.SPREAD2 === '1'?'visibility:visible':'visibility:hidden'" class="new" src="../../../assets/image/new.gif">
      <div class="title">{{ item.TITLE }}</div>
      <div class="time">{{ item.CREATEDATE|timeFilter }}</div>
    </li>
  </ul>
</template>

<script>
import moment from 'moment'
export default {
  name: 'NoticeMoreList',
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
  data() {
    return {
      defaultImg: 'this.src="' + require('../../../assets/img/noImg.png') + '"'
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
.NoticeMoreList {
  padding: 20px;
  margin: 0;
  li {
    display: flex;
    flex-direction: row;
    align-items: center;
    // margin-bottom: 10px;
    padding: 15px 0;
    // box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
    border-bottom: 1px dashed #000;
    cursor: pointer;
    &:active {
      background: #eee;
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
    .point {
      width: 8px;
      height: 8px;
      border-radius: 10px;
      background: #fabd2e;
      margin-right: 10px;
    }
    .dept {
      margin-right: 20px;
    }
    .new {
      color: white;
      font-size: 12px;
      border-radius: 4px;
      padding: 2px;
      line-height: 12px;
      margin-right: 5px;
    }
    .sc {
      width: 20px;
      margin-right: 5px;
    }
    .title {
      flex: 1;
      word-break: keep-all;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
    .time {
      color: #898989;
      font-size: 14px;
    }
  }
}
</style>
