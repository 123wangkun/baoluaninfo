<template>
  <ul class="list-box clearfix">
    <li v-for="(item,index) in courselist" :key="index" @click="gotoDetail(item)">
      <div class="item">
        <!-- <img :src="item.IMG_PATH|imgUrlFilter" :onerror="defaultImg"> -->
        <img :onerror="defaultImg" src="../assets/img/img4.jpg">
        <div class="list-text2">
          <div class="title">{{ item.TITLE }}</div>
          <div class="option clearfix">
            <div class="readCount">浏览量:{{ item.READNUM }}</div>
            <div class="text1">收藏</div>
            <img v-if="item.isCollect==0" src="../assets/img/sc.png">
            <img v-else src="../assets/img/sc_c.png">
            <div class="text3">点赞({{ item.LIKE_COUNT }})</div>
            <img v-if="item.isLike==0" src="../assets/img/dz.png">
            <img v-else src="../assets/img/dz_c.png">
          </div>
        </div>
        <img v-if="showIsRead&&item.isRead==1" src="../assets/img/isRead.png" class="isRead">
      </div>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'Courselist',
  props: {
    courselist: {
      type: Array,
      required: true
    },
    showIsRead: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  data() {
    return {
      defaultImg: 'this.src="' + require('../assets/img/noImg.png') + '"'
    }
  },
  methods: {
    gotoDetail(item) {
      this.$router.push({
        path: '/learn/courseDetail',
        query: {
          COURSEDETAIL_ID: item.COURSEDETAIL_ID
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/common.scss';
.list-box {
  width: 100%;
  li {
    float: left;
    width: calc((100vw - 360px) / 4);
    margin: 0px 7px 24px 7px;
    cursor: pointer;
    .item {
      border-radius: 8px;
      box-shadow: 0px 4px 7px rgba(0, 0, 0, 0.3);
      width: calc((100vw - 360px) / 4);
      position: relative;
      img {
        width: 100%;
        border-top-left-radius: 8px;
        border-top-right-radius: 8px;
        height: 150px;
      }
      .list-text2 {
        padding: 6px 12px 14px 12px;
        width: 100%;
        height: 70px;
        display: flex;
        flex-direction: column;
        .title {
          font-size: 13.5px;
          line-height: 16px;
          color: #4d4d4d;
          word-break: break-all;
          overflow: hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-box-orient: vertical;
          -webkit-line-clamp: 2;
          flex: 1;
        }
        .option {
          margin-top: 0px;
          img {
            width: 17px;
            height: 17px;
          }
          * {
            float: right;
          }
          .text1 {
            margin-left: 5px;
            margin-right: 10px;
            margin-top: 3px;
            font-size: 12px;
          }
          .text3 {
            margin-left: 5px;
            font-size: 12px;
            margin-right: 5px;
            margin-top: 3px;
          }
          .readCount {
            float: left;
            color: #808080;
            font-size: 12px;
            margin-top: 3px;
          }
        }
      }
      .isRead {
        width: 50px;
        height: 50px;
        position: absolute;
        top: 0;
        left: 0;
      }
    }
  }
}
</style>

