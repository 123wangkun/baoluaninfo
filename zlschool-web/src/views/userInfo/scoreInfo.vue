<template>
  <div class="scoreInfo">
    <div class="card">
      <div class="t1">学分获取规则</div>
      <div class="t2">获取学分有什么用？</div>
      <div class="t3">学分是企业对员工学习奇情况的一种考量标准，员工必须在规定的时间内完成企业给的学分目标才算合格</div>
      <div class="t2">我都可以通过哪些操作来获得学分？</div>
      <el-table :data="list" border style="width: 100%" class="table">
        <el-table-column label="以下操作可以获得学分" >
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [
        {
          name: '通过完成岗位课程、公开课可以获得学分'
        },
        {
          name: '通过完成培训任务可以获得学分'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.scoreInfo {
  width: 100%;
  padding: 20px;
  min-height: calc(100vh - 110px);
  .card {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background: white;
    padding: 20px 20px;
    .t1 {
      font-size: 20px;
      border-bottom: 1px solid #e4e7ed;
      padding-bottom: 15px;
    }
    .t2 {
      font-weight: bold;
      margin-top: 20px;
      margin-bottom: 20px;
    }
    .t3 {
      border-bottom: 1px solid #e4e7ed;
      padding-bottom: 20px;
      color: #606266;
    }
  }
}
</style>

