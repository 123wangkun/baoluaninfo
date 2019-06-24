<template>
  <div class="Operation">
    <div class="top">
      <div class="item" style="margin-right:20px">
        <span class="item-name">500</span>
        <div class="item-box">
          <img src="../../../assets/img/kc.png">
          课程数
        </div>
      </div>
      <div class="item">
        <span class="item-name">241</span>
        <div class="item-box">
          <img src="../../../assets/img/tk.png">
          题库数
        </div>
      </div>
    </div>
    <div class="list-box">
      <div class="row">
        <div class="item">
          <img src="../../../assets/img/icon_1.png">道路养护
          <span>（100）</span>
        </div>
        <div class="item">
          <img src="../../../assets/img/icon_2.png">技术创新
          <span>（100）</span>
        </div>
        <div class="item">
          <img src="../../../assets/img/icon_3.png">技术交流
          <span>（100）</span>
        </div>
      </div>
      <div class="row">
        <div class="item">
          <img src="../../../assets/img/icon_4.png">人才培养
          <span>（100）</span>
        </div>
        <div class="item">
          <img src="../../../assets/img/A.png">课程A类
          <span>（100）</span>
        </div>
        <div class="item">
          <img src="../../../assets/img/B.png">课程B类
          <span>（100）</span>
        </div>
      </div>
      <div class="row">
        <div class="item">
          <img src="../../../assets/img/C.png">课程C类
          <span>（100）</span>
        </div>
        <div class="item">
          <img src="../../../assets/img/D.png">课程D类
          <span>（100）</span>
        </div>
        <div class="item">
          <img src="../../../assets/img/E.png">课程E类
          <span>（100）</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Operation'
}
</script>

<style lang="scss" scoped>
.Operation {
  padding: 20px;
  .top {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    .item {
      background: rgb(245, 245, 245);
      padding: 20px 0;
      border-radius: 10px;
      flex: 1;
      display: flex;
      flex-direction: column;
      align-items: center;
      .item-name {
        font-size: 30px;
        color: #ff6a61;
        font-weight: bold;
      }
      .item-box {
        display: flex;
        align-items: center;
        img {
          width: 24px;
          margin-right: 10px;
        }
        span {
          line-height: 50px;
          color: rgb(114, 114, 114);
        }
      }
    }
  }
  .list-box {
    .row {
      display: flex;
      flex-direction: row;
      margin-top: 35px;
      &:first-child {
        margin-top: 20px;
      }
      .item {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
        &:first-child {
          justify-content: flex-start;
        }
        &:last-child {
          justify-content: flex-end;
        }
        img {
          margin-right: 10px;
        }
        span {
          color: rgb(114, 114, 114);
        }
      }
    }
  }
}
</style>
