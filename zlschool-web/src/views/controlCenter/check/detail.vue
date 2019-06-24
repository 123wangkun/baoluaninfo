<template>
  <!-- 课程活动列表 -->
  <div class="detail pd-20">
    <el-input
      v-model="realName"
      placeholder="请输入用户名称"
      class="mr-15"/>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
    <el-button type="primary" plain class="btn" @click="add">新增</el-button>
    <el-button type="primary" plain class="btn" @click="importList">导入</el-button>
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
        label="考核名称"/>
      <el-table-column
        prop="realName"
        label="用户名称"/>
      <el-table-column
        prop="checkScore"
        label="考核得分"/>
      <el-table-column label="操作" min-width="220" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增/修改弹窗 -->
    <el-dialog
      :visible.sync="addDialog"
      :title="operate === 0 ? '新增用户考核' : '修改用户考核信息'"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="addMsg"
        :model="addMsg"
        :rules="addMsgRules"
        label-position="left">
        <el-form-item prop="name" class="dept">
          <div class="name">
            <i>*</i>
            <span>考核名称：</span>
          </div>
          <el-input
            v-model="addMsg.name"
            placeholder="请输入考核名称"/>
        </el-form-item>
        <el-form-item prop="realName" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>用户名称：</span>
          </div>
          <el-input
            v-model="addMsg.realName"
            disabled
            class="pic-input"/>
          <el-button v-if="addMsg.realName == ''" type="primary" plain class="btn" @click="selectUser">选择</el-button>
          <el-button v-else type="primary" plain class="btn">更改</el-button>
        </el-form-item>
        <el-form-item prop="checkScore" class="dept">
          <div class="name">
            <i>*</i>
            <span>考核得分：</span>
          </div>
          <el-input
            v-model="addMsg.checkScore"
            placeholder="请输入考核得分"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="addSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 删除弹窗 -->
    <el-dialog
      :visible.sync="delDialog"
      title="您确定删除吗？"
      width="30%"
      @close="closeDialog">
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="delSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 选择用户弹窗 -->
    <el-dialog
      :visible.sync="userDialog"
      title="选择用户"
      width="30%"
      @close="closeUserDialog">
      <div class="tag-wrap flex">
        <div class="left">
          <el-tree v-if="treeList.length > 0" ref="tree" :data="treeList" :props="defaultProps" node-key="id" @node-click="handleNodeClick" />
        </div>
        <div class="right">
          <div class="search">
            <el-input
              v-model="userName"
              placeholder="请输入用户名称"
              prefix-icon="el-icon-search"/>
            <el-input
              v-model="telePhone"
              placeholder="请输入手机号码"
              prefix-icon="el-icon-search"/>
            <el-button type="primary" plain class="btn" @click="searchUser">搜索</el-button>
          </div>
          <el-table
            ref="multipleTable"
            :data="userList"
            border
            stripe
            tooltip-effect="dark"
            class="table">
            <el-table-column
              type="index"
              width="55"
              label="序号"/>
            <el-table-column
              prop="deptName"
              label="部门"/>
            <el-table-column
              prop="realName"
              label="用户姓名"/>
            <el-table-column
              prop="telePhone"
              label="手机号码"/>
            <el-table-column
              prop="createdTime"
              label="注册时间"
              min-width="100"/>
          </el-table>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeUserDialog">取 消</el-button>
        <el-button type="primary" @click="userDialogSure">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { uploadFile } from '@/api/common'
export default {
  name: 'Detail',
  data() {
    return {
      tableList: [{
        id: '1',
        name: '',
        realName: '',
        checkScore: 80
      }],
      addMsg: {},
      addMsgRules: {
        name: [
          { required: true, message: '请输入考核名称', trigger: 'blur' }
        ],
        checkTime: [
          { required: true, message: '请选择考核时间', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入考核内容', trigger: 'blur' }
        ]
      },
      realName: '',
      userList: [{
        id: '1',
        deptName: '工程部',
        realName: '王小虎',
        telePhone: 18895665656,
        createdTime: '2018-01-01'
      }],
      userName: '',
      telePhone: '',
      userDialog: false
    }
  },
  created() {},
  methods: {
    // 搜索
    search() {},
    // 新增
    add() {
      this.addMsg = {}
    },
    // 导入
    importList() {
      const fileObj = new FormData()
      fileObj.append('editormd-image-file', param.file)
      uploadFile(fileObj, 8).then(res => {
        this.$message.success('导入成功')
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 修改
    edit(index, row) {
      this.selectIndex = index
      this.addMsg = {
        name: row.name,
        content: row.content,
        checkTime: row.checkTime,
        memo: row.memo
      }
      this.addDialog = true
    },
    // 点击修改弹窗确定
    addSure() {
      this.$refs.addMsg.validate(valid => {
        if (valid) {
          if (this.operate === 0) {
            this.tableList.push(this.addMsg)
          } else if (this.operate === 1) {
            this.tableList.splice(this.selectIndex, 1, this.addMsg)
            this.addDialog = false
          }
        }
      })
    },
    // 删除
    del(index) {
      this.selectIndex = index
      this.delDialog = true
    },
    // 点击删除弹窗确定
    delSure() {
      this.tableList.splice(this.selectIndex, 1)
      this.delDialog = false
    },
    // 关闭弹窗
    closeDialog() {
      this.addDialog = false
      this.delDialog = false
    },
    // 选择用户
    selectUser() {
      this.userDialog = true
    },
    // 搜索用户列表
    searchUser() {},
    // 点击选择用户弹窗确定按钮
    userDialogSure() {
      this.userDialog = false
    },
    // 关闭选择用户弹窗
    closeUserDialog() {
      this.userDialog = true
    }
  }
}
</script>
