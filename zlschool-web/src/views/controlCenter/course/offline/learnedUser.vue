<template>
  <!-- 课程活动列表 -->
  <div class="course-activity pd-20">
    <el-input
      v-model="realName"
      placeholder="请输入用户名称"
      class="mr-15"/>
    <el-select v-model="stateSelect" placeholder="请选择" class="state mr-15">
      <el-option
        v-for="item in stateOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
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
        prop="courseName"
        label="课程名称"/>
      <el-table-column
        prop="realName"
        label="用户名称"/>
      <el-table-column
        prop="departName"
        label="所属部门"/>
      <el-table-column
        prop="spendTime"
        label="学习时长"
        min-width="100"/>
      <el-table-column
        prop="stateName"
        label="学习状态"/>
      <el-table-column
        prop="createdTime"
        label="学习时间"
        min-width="100"/>
      <el-table-column
        prop="memo"
        label="备注"/>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'LearnedUser',
  data() {
    return {
      tableList: [{
        id: '1',
        courseName: '课程名称',
        realName: '王小虎',
        departName: '开发',
        spendTime: '2019-01-01',
        stateName: '已学习',
        createdTime: '2019-01-01',
        memo: '备注内容'
      }],
      stateSelect: 3,
      stateOptions: [{
        value: '',
        label: '全部'
      }, {
        value: 0,
        label: '未进行'
      }, {
        value: 1,
        label: '进行中'
      }, {
        value: 3,
        label: '已学完'
      }],
      realName: ''
    }
  },
  created() {},
  methods: {
    // 搜索
    search(val) {
      console.log(val)
    }
  }
}
</script>
