<template>
  <div class="UserList">
    <div class="arrow-box">
      <img src="../../../assets/img/left.png">
    </div>

    <div class="list">
      <div v-for="(item,index) in list" :key="index" class="item">
        <img :onerror="defaultImg">
        <div class="text-box">
          <div class="dept">养护处</div>
          <div class="row">
            <div class="left">姓名</div>
            <div class="right">张三</div>
          </div>
          <div class="row">
            <div class="left">部门</div>
            <div class="right">张三</div>
          </div>
          <div class="row">
            <div class="left">积分</div>
            <div class="right">张三</div>
          </div>
        </div>
      </div>
    </div>
    <div class="arrow-box">
      <img src="../../../assets/img/right.png">
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
  padding: 20px;
  display: flex;
  flex-direction: row;
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
    width: 950px;
    .item {
      width: 186px;
      height: 237px;
      margin-right: 70px;
      background: #f9f9f9;
      border: 1px solid #ebecef;
      &:last-child {
        margin-right: 0;
      }
      img {
        width: 184px;
        height: 118px;
        background: white;
      }
      .text-box {
        padding: 10px;
        .row {
          display: flex;
          flex-direction: row;
          margin-top: 5px;
          .left {
            flex: 3;
          }
          .right {
            flex: 7;
          }
        }
      }
    }
  }
}
</style>

