<template>
  <!-- 已考用户列表 -->
  <div class="examed-user pd-20">
    <el-input
      v-model="realName"
      placeholder="请输入用户名称"
      class="mr-15"/>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
    <el-table
      ref="multipleTable"
      :data="tableList"
      border
      stripe
      tooltip-effect="dark"
      class="table mt-15">
      <el-table-column
        type="index"
        width="55"
        label="序号"/>
      <el-table-column
        prop="name"
        label="试卷名称"/>
      <el-table-column
        prop="realName"
        label="用户名称"/>
      <el-table-column
        prop="departName"
        label="所属部门"/>
      <el-table-column
        prop="startTime"
        label="开始时间"
        min-width="100"/>
      <el-table-column
        prop="endTime"
        label="结束时间"
        min-width="100"/>
      <el-table-column
        prop="totalScore"
        label="得分"/>
      <el-table-column label="预览">
        <template slot-scope="scope">
          <el-button size="mini" @click="view">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'ExamedUser',
  data() {
    return {
      tableList: [{
        id: '1',
        name: '课程名称',
        realName: '王小虎',
        departName: '开发',
        startTime: '2019-01-01',
        endTime: '2019-01-01',
        totalScore: 80
      }],
      realName: ''
    }
  },
  created() {},
  methods: {
    // 搜索
    search() {},
    // 预览
    view(url) {
      window.open(url)
    }
  }
}
</script>
