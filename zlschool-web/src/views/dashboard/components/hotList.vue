<template>
  <ul class="hotList">
    <li v-for="(item,index) in list" :key="index">
      <img class="img">
      <div class="text-box">
        <div class="title">限时优惠！仅剩15天2小时18分!更新中</div>
        <div class="snd-title">
          <img src="../../../assets/img/yds.png" class="icon-img">
          <span class="text">100</span>
          <img src="../../../assets/img/dz1.png" class="icon-img">
          <span class="text">20</span>
          <img src="../../../assets/img/sc1.png" class="icon-img">
          <span class="text">10</span>
        </div>

      </div>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'HotList',
  props: {
    list: {
      type: Array,
      required: true
    }
  }
}
</script>

<style lang="scss" scoped>
.hotList {
  margin: 0;
  padding: 0;
  list-style: none;
  padding: 20px;
  li {
    display: flex;
    flex-direction: row;
    margin-top: 10px;
    cursor: pointer;
    &:first-child {
      margin-top: 0px;
    }
    .img {
      width: 144px;
      height: 82px;
      margin-right: 20px;
    }
    .text-box {
      display: flex;
      flex-direction: column;
      justify-content: center;
      .title {
      }
      .snd-title {
        margin-top: 10px;
        font-size: 16px;
        color: rgb(114, 114, 114);
        display: flex;
        flex-direction: row;
        align-items: center;
        line-height: 16px;
        .icon-img {
          width: 16px;
          height: 16px;
          margin-right: 10px;
        }
        .text {
          margin-right: 30px;
        }
      }
    }
  }
}
</style>

