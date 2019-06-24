<template>
  <div class="UserList2">
    <div class="list">
      <div v-for="(item,index) in list" :key="index" class="item">
        <img src="../../../assets/img/img1.png">
        <div :class="'tip'+index"/>
        <div :class="'index'+index">{{ index+1 }}</div>
        <div class="name">姓名:张三</div>
        <div class="text-box">
          <div class="text">
            积分：12345
          </div>
          <div class="row">
            科室：养护处
          </div>
          <div class="row">
            部门：综合管理部
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
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
.UserList2 {
  width: 100%;
  padding: 20px;
  display: flex;
  flex-direction: row;
  border: 1px solid #eee;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  .list {
    display: flex;
    flex-direction: row;
    width: 100%;
    justify-content: space-between;
    .item {
      width: 186px;
      height: 237px;
      background: #f9f9f9;
      border: 1px solid #ebecef;
      display: flex;
      flex-direction: column;
      position: relative;
      font-size: 14px;
      .name {
        position: absolute;
        left: 0;
        top: 130px;
        background-color: rgba(0, 0, 0, 0.5);
        color: white;
        height: 20px;
        width: 100%;
        line-height: 20px;
        padding: 0 10px;
      }
      .tip0 {
        width: 0px;
        height: 0px;
        border-top: 20px solid #ff564b;
        border-right: 12px solid #ff564b;
        border-bottom: 5px solid transparent;
        border-left: 12px solid #ff564b;
        position: absolute;
        top: 0;
        left: 10px;
      }
      .tip1 {
        width: 0px;
        height: 0px;
        border-top: 20px solid #ffc535;
        border-right: 12px solid #ffc535;
        border-bottom: 5px solid transparent;
        border-left: 12px solid #ffc535;
        position: absolute;
        top: 0;
        left: 10px;
      }
      .tip2 {
        width: 0px;
        height: 0px;
        border-top: 20px solid #86dc85;
        border-right: 12px solid #86dc85;
        border-bottom: 5px solid transparent;
        border-left: 12px solid #86dc85;
        position: absolute;
        top: 0;
        left: 10px;
      }
      .tip3,
      .tip4 {
        width: 0px;
        height: 0px;
        border-top: 20px solid #f2f2f2;
        border-right: 12px solid #f2f2f2;
        border-bottom: 5px solid transparent;
        border-left: 12px solid #f2f2f2;
        position: absolute;
        top: 0;
        left: 10px;
      }
      .index0,
      .index1,
      .index2 {
        color: white;
        position: absolute;
        top: 3px;
        left: 17px;
      }
      .index3,
      .index4 {
        color: #9b9b9b;
        position: absolute;
        top: 3px;
        left: 17px;
      }
      img {
        width: 184px;
        height: 150px;
      }
      .text-box {
        flex: 1;
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

