<template>
  <div class="zszl">
    <div class="left">知识资料</div>
    <div class="right">
      <div class="top">
        <div class="item curr">课程数500</div>
        <div class="item">题库数241</div>
      </div>
      <div class="bottom">
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
          <div class="item">
            <img src="../../../assets/img/icon_4.png">人才培养
            <span>（100）</span>
          </div>
          <div class="item">
            <img src="../../../assets/img/A.png">课程A类
            <span>（100）</span>
          </div>
        </div>
        <div class="row">
          <div class="item">
            <img src="../../../assets/img/B.png">课程B类
            <span>（100）</span>
          </div>
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
          <div class="item"/>
        </div>
      </div>
    </div>
</div></template>

<script>
export default {}
</script>

<style lang="scss" scoped>
.zszl {
  display: flex;
  flex-direction: row;
  height: 140px;
  margin-top: 30px;
  .left {
    background: #5096eb;
    color: white;
    width: 200px;
    text-align: center;
    line-height: 120px;
    font-size: 25px;
    font-weight: bold;
  }
  .right {
    flex: 1;
    display: flex;
    flex-direction: column;
    background: #f2f2f2;
    padding: 15px;
    .top {
      display: flex;
      .item {
        padding-bottom: 5px;
        margin-right: 20px;
      }
      .curr {
        color: #5096eb;
        border-bottom: 2px solid #5096eb;
      }
    }
    .bottom {
      flex: 1;
      margin-top: 15px;
      .row {
        display: flex;
        flex-direction: row;
        margin-top: 5px;
        .item {
          flex: 1;
          display: flex;
          align-items: center;
          img {
            width: 24px;
            margin-right: 10px;
          }
          span {
            color: rgb(114, 114, 114);
          }
        }
      }
    }
  }
}
</style>
