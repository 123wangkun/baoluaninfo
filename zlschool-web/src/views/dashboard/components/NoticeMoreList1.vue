<template>
  <ul class="NoticeMoreList">
    <li v-for="(item,index) in list" :key="index" @click="openDetail(item)">
      <!-- <img :src="item.IMG_PATH|imgUrlFilter" :onerror="defaultImg" class="img"> -->
      <div class="timebox">
        <div class="t1">20</div>
        <div class="t2">2019.04</div>
      </div>
      <div class="list_content">
        <div class="title">{{ item.TITLE }}</div>
        <div class="time">3月4日—8日，公司在浙江大学举办了“转变思维  提升视野”为主题的管理骨干专题培训班。</div>
      </div>
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
  border: 1px solid #eee;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  padding: 5px;
  margin: 0;
  li {
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 10px 0;
    cursor: pointer;
    &:hover {
      background: #eee;
    }
    &:active {
      background: #eee;
    }
    .timebox {
      margin-right: 5px;
      .t1 {
        font-size: 20px;
        color: #176fbb;
        text-align: center;
      }
      .t2 {
        font-size: 14px;
      }
    }
    .list_content {
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
        font-size: 14px;
        margin-left: 8px;
      }
    }
  }
}
</style>
