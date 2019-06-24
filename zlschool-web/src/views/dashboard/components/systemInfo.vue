<template>
  <div class="systemInfo">
    <div class="select">
      <el-select v-model="value" placeholder="请选择" size="small">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
    </div>
    <div class="row">
      <div class="item">
        <div class="text1">50%</div>
        <div class="text2"><img src="../../../assets/img/dlbl.png">登陆比例</div>
      </div>
      <div class="item">
        <div class="text1">80%</div>
        <div class="text2"><img src="../../../assets/img/czbl.png">持证比例</div>
      </div>
      <div class="item">
        <div class="text1">90%</div>
        <div class="text2"><img src="../../../assets/img/kstgl.png">考试通过率</div>
      </div>
      <div class="item">
        <div class="text1">70%</div>
        <div class="text2"><img src="../../../assets/img/xxbl.png">学习比例</div>
      </div>
    </div>
    <div class="row">
      <div class="item">
        <div class="text1">25</div>
        <div class="text2"><img src="../../../assets/img/ljdl.png">累计登陆</div>
      </div>
      <div class="item">
        <div class="text1">50</div>
        <div class="text2"><img src="../../../assets/img/ljffhbs.png">累计发放红包</div>
      </div>
      <div class="item">
        <div class="text1">25</div>
        <div class="text2"><img src="../../../assets/img/yzzks.png">已组织考试</div>
      </div>
      <div class="item">
        <div class="text1">25</div>
        <div class="text2"><img src="../../../assets/img/yzzgwpx.png">已组织培训考试</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SystemInfo',
  data() {
    return {
      options: [
        {
          value: '综合管理部',
          label: '综合管理部'
        },
        {
          value: '机务养护部',
          label: '机务养护部'
        },
        {
          value: '综合管理部1',
          label: '综合管理部1'
        }
      ],
      value: '综合管理部'
    }
  }
}
</script>

<style lang="scss" scoped>
.systemInfo {
  width: 100%;
  height: 100%;
  padding: 20px;
  .select {
    display: flex;
    justify-content: flex-end;
  }
  .row {
    display: flex;
    flex-direction: row;
    padding-top: 20px;
    &:last-child {
      padding-top: 60px;
    }
    .item {
      flex: 1;
      border-right: 1px solid #e6e6e6;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      &:last-child {
        border: 0;
      }
      .text1 {
        font-size: 30px;
        color: #409eff;
      }
      .text2 {
        margin-top: 18px;
        font-size: 16px;
        display: flex;
        align-items: center;
      }
    }
  }
}
</style>

