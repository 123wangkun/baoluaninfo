<template>
  <div class="TrainList">
    <img src="../../../assets/img/img1.png" class="img1">
    <ul class="list">
      <li v-for="(item,index) in list" :key="index" @click="openDetail(item)">
        <div class="dept">公司动态</div>
        <div class="list_content">
          <img class="new" src="../../../assets/image/new.gif">
          <!-- <img :style="item.SPREAD2 === '1'?'visibility:visible':'visibility:hidden'" class="new" src="../../../assets/image/new.gif"> -->
          <div class="title">{{ item.TITLE }}</div>
          <div class="time">{{ item.CREATEDATE|timeFilter }}</div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    list: {
      type: Array,
      required: true
    }
  }
}
</script>

<style  lang="scss" scoped>
@import '../../../assets/css/common.scss';
.TrainList {
  border: 1px solid #eee;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  padding: 5px;
  display: flex;
  flex-direction: row;
  .img1 {
    height: 230px;
  }
  .list {
    flex: 1;
    margin: 0;
    padding: 0 0px 0 10px;
    li {
      padding: 10px 0;
      cursor: pointer;
      &:hover {
        background: #eee;
      }
      .dept {
      }
      .list_content {
        display: flex;
        flex-direction: row;
        margin-top: 3px;
        .new {
          margin-right: 5px;
          margin-left: 7px;
        }
        .title {
          flex: 1;
        }
        .time {
          color: #9b9b9b;
        }
      }
    }
  }
}
</style>
