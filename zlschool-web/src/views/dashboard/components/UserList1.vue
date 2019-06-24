<template>
  <div class="UserList">
    <div class="list">
      <div class="item">
        <img src="../../../assets/img/img3.png">
        <div class="text">安全</div>
        <div class="t1">线上30%</div>
        <div class="t2">线下70%</div>
      </div>
      <div class="item">
        <img src="../../../assets/img/img3.png">
        <div class="text">质量</div>
        <div class="t1">线上30%</div>
        <div class="t2">线下70%</div>
      </div>
      <div class="item">
        <img src="../../../assets/img/img3.png">
        <div class="text">教育</div>
        <div class="t1">线上30%</div>
        <div class="t2">线下70%</div>
      </div>
      <div class="item">
        <img src="../../../assets/img/img3.png">
        <div class="text">技术</div>
        <div class="t1">线上30%</div>
        <div class="t2">线下70%</div>
      </div>
      <div class="item">
        <img src="../../../assets/img/img3.png">
        <div class="text">经济</div>
        <div class="t1">线上30%</div>
        <div class="t2">线下70%</div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'UserList',
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
  }
}
</script>

<style lang="scss" scoped>
.UserList {
  width: 100%;
  padding: 40px 20px;
  display: flex;
  flex-direction: row;
  border: 1px solid #eee;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  .arrow-box {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    img {
      width: 40px;
      cursor: pointer;
    }
  }
  .list {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    width: 100%;
    .item {
      position: relative;
      img {
        width: 90px;
      }
      .text {
        margin-top: 10px;
        text-align: center;
      }
      .t1 {
        position: absolute;
        top: 18px;
        left: 22px;
        color: white;
        font-size: 12px;
      }
      .t2 {
        position: absolute;
        bottom: 55px;
        left: 13px;
        color: white;
        font-size: 12px;
      }
    }
  }
}
</style>

