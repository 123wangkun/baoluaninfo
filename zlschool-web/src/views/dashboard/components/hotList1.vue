<template>
  <div class="hotList1">
    <div class="f1 border1" style="margin-right:10px;">
      <img src="../../../assets/img/img1.png">
      <div class="info-box">
        <div>限时优惠！仅剩15天2小时18分!更新中</div>
        <div class="sndtext">2019-4-28</div>
        <div class="snd-title">
          <img src="../../../assets/img/yds.png" class="icon-img">
          <span class="text">100</span>
          <img src="../../../assets/img/dz1.png" class="icon-img">
          <span class="text">20</span>
          <img src="../../../assets/img/sc1.png" class="icon-img">
          <span class="text">10</span>
        </div>
      </div>
    </div>
    <div class="f1">
      <div class="imgbox" style="margin-right:10px;">
        <div class="text-box">
          <div class="snd-title">
            <img src="../../../assets/img/yds_1.png" class="icon-img">
            <span class="text">100</span>
            <img src="../../../assets/img/dz1_1.png" class="icon-img">
            <span class="text">20</span>
            <img src="../../../assets/img/sc1_1.png" class="icon-img">
            <span class="text">10</span>
          </div>
          <div style="margin-top:3px;">限时优惠！仅剩15天2小时18分!更新中</div>
          <div class="sndtext">2019-4-28</div>
        </div>
      </div>
      <div class="imgbox" style="margin-right:10px;margin-top:10px;">
        <div class="text-box">
          <div class="snd-title">
            <img src="../../../assets/img/yds_1.png" class="icon-img">
            <span class="text">100</span>
            <img src="../../../assets/img/dz1_1.png" class="icon-img">
            <span class="text">20</span>
            <img src="../../../assets/img/sc1_1.png" class="icon-img">
            <span class="text">10</span>
          </div>
          <div style="margin-top:3px;">限时优惠！仅剩15天2小时18分!更新中</div>
          <div class="sndtext">2019-4-28</div>
        </div>
      </div>
    </div>
    <div class="f1">
      <div class="imgbox">
        <div class="text-box">
          <div class="snd-title">
            <img src="../../../assets/img/yds_1.png" class="icon-img">
            <span class="text">100</span>
            <img src="../../../assets/img/dz1_1.png" class="icon-img">
            <span class="text">20</span>
            <img src="../../../assets/img/sc1_1.png" class="icon-img">
            <span class="text">10</span>
          </div>
          <div style="margin-top:3px;">限时优惠！仅剩15天2小时18分!更新中</div>
          <div class="sndtext">2019-4-28</div>
        </div>
      </div>
      <div class="imgbox" style="margin-top:10px;">
        <div class="text-box">
          <div class="snd-title">
            <img src="../../../assets/img/yds_1.png" class="icon-img">
            <span class="text">100</span>
            <img src="../../../assets/img/dz1_1.png" class="icon-img">
            <span class="text">20</span>
            <img src="../../../assets/img/sc1_1.png" class="icon-img">
            <span class="text">10</span>
          </div>
          <div style="margin-top:3px;">限时优惠！仅剩15天2小时18分!更新中</div>
          <div class="sndtext">2019-4-28</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style lang="scss" scoped>
.hotList1 {
  display: flex;
  flex-direction: row;
  .f1 {
    flex: 1;
    display: flex;
    flex-direction: column;
    .info-box {
      padding: 10px;
      .sndtext {
        margin-top: 5px;
        color: rgb(114, 114, 114);
      }
      .snd-title {
        margin-top: 10px;
        font-size: 16px;
        color: rgb(114, 114, 114);
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: flex-end;
        line-height: 16px;
        .icon-img {
          width: 16px;
          height: 16px;
          margin-right: 10px;
          margin-left: 20px;
        }
        .text {
          margin-right: 0px;
        }
      }
    }
    .imgbox {
      flex: 1;
      background: url(../../../assets/img/img2.png) no-repeat;
      position: relative;
      background-size: 100% 100%;
      .text-box {
        bottom: 0px;
        width: 100%;
        position: absolute;
        left: 0px;
        background-color: rgba(0, 0, 0, 0.5);
        padding: 10px;
        z-index: 10;
        color: white;
        font-size: 14px;
        .snd-title {
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
  .border1 {
    border: 1px solid #eee;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  }
}
</style>

