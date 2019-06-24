<template>
  <!-- 角色管理 -->
  <div class="role-manage pd-20">
    <el-button type="primary" class="btn" @click="addRole">新增角色</el-button>
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
        label="角色名称"/>
      <el-table-column
        prop="remark"
        label="备注信息"/>
      <el-table-column label="操作" width="250" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="editRole(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="delRole(scope.$index)"/>
          <el-button size="mini" type="primary" @click="authorize(scope.$index)">角色授权</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增角色弹窗 -->
    <el-dialog
      :visible.sync="addRoleDialog"
      :title="roleOperate === 0 ? '新增角色' : '修改角色'"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="addRoleMsg"
        :model="addRoleMsg"
        :rules="addRoleMsgRules"
        label-position="left">
        <el-form-item prop="name" class="dept">
          <div class="name">
            <i>*</i>
            <span>角色名称：</span>
          </div>
          <el-input
            v-model="addRoleMsg.name"
            placeholder="请输入角色名称"/>
        </el-form-item>
        <el-form-item prop="remark" class="dept">
          <div class="name">
            <i/>
            <span>备注信息：</span>
          </div>
          <el-input
            :rows="2"
            v-model="addRoleMsg.remark"
            type="textarea"
            placeholder="请输入备注信息"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="addRoleSure">确 定</el-button>
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
        <el-button type="primary" @click="delRoleSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 角色授权弹窗 -->
    <el-dialog
      :visible.sync="authorizeDialog"
      title="角色授权"
      class="authorize-dialog"
      @close="closeDialog">
      <div class="menu-part">
        <div class="menu">
          <p>操作菜单列表</p>
          <el-tree v-if="authorizeMsg.menuList.length > 0" ref="tree" :data="authorizeMsg.menuList" :props="defaultProps" node-key="id" show-checkbox @node-click="menuClick" />
        </div>
        <div class="menu">
          <el-form
            ref="addRoleMsg"
            :model="authorizeMsg"
            label-position="left">
            <el-form-item prop="function" class="dept">
              <div>功能操作描述：</div>
              <el-input
                :rows="2"
                v-model="authorizeMsg.function"
                type="textarea"
                placeholder="描述菜单有哪些功能"/>
            </el-form-item>
            <el-form-item prop="attention" class="dept">
              <div>注意事项：</div>
              <el-input
                :rows="2"
                v-model="authorizeMsg.attention"
                type="textarea"
                placeholder="操作选中菜单需要注意的事项"/>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="authorizeSure">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'RoleManage',
  data() {
    return {
      tableList: [{
        name: '超级管理员',
        remark: '备注'
      }, {
        name: '管理员',
        remark: '备注'
      }],
      addRoleMsg: {},
      addRoleMsgRules: {
        name: [
          { required: true, message: '请输入角色名称', trigger: 'blur' }
        ]
      },
      authorizeMsg: {
        menuList: [{
          id: 1,
          name: '1-江苏高速养护公司',
          children: [{
            id: 4,
            name: '1-1-总经理',
            children: [{
              id: 7,
              name: '1-1-1-部门领导',
              children: [{
                id: 10,
                name: '1-1-1-1-员工1'
              }, {
                id: 11,
                name: '1-1-1-2-员工2'
              }]
            }]
          }]
        }, {
          id: 2,
          name: '2-上海高速养护公司',
          children: [{
            id: 5,
            name: '2-1-总经理',
            children: [{
              id: 8,
              name: '2-1-1-部门领导'
            }]
          }]
        }, {
          id: 3,
          name: '3-北京高速养护公司',
          children: [{
            id: 6,
            name: '3-1-总经理',
            children: [{
              id: 9,
              name: '3-1-1-部门领导'
            }]
          }]
        }],
        function: '',
        attention: ''
      },
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      addRoleDialog: false,
      delDialog: false,
      authorizeDialog: false,
      selectIndex: 0,
      roleOperate: 0, // 0新增，1修改
      menuSelect: null,
      node: null
    }
  },
  methods: {
    // 新增角色
    addRole() {
      this.roleOperate = 0
      this.addRoleMsg = {}
      this.addRoleDialog = true
    },
    // 点击新增角色弹窗确定
    addRoleSure() {
      this.$refs.addRoleMsg.validate(valid => {
        if (valid) {
          if (this.roleOperate === 0) {
            this.tableList.push(this.addRoleMsg)
          } else if (this.roleOperate === 1) {
            this.tableList.splice(this.selectIndex, 1, this.addRoleMsg)
          }
          this.addRoleDialog = false
        }
      })
    },
    // 修改角色
    editRole(index, row) {
      this.selectIndex = index
      this.addRoleMsg = {
        name: row.name,
        remark: row.remark
      }
      this.roleOperate = 1
      this.addRoleDialog = true
    },
    // 删除角色
    delRole(index) {
      this.selectIndex = index
      this.delDialog = true
    },
    // 点击删除角色弹窗确定
    delRoleSure() {
      this.tableList.splice(this.selectIndex, 1)
      this.delDialog = false
    },
    // 角色授权
    authorize(index) {
      this.selectIndex = index
      this.authorizeDialog = true
    },
    // 关闭弹窗
    closeDialog() {
      this.addRoleDialog = false
      this.delDialog = false
      this.authorizeDialog = false
    },
    // 选择菜单
    menuClick(node, data) {
      console.log(node)
      console.log(data)
      this.menuSelect = data
      this.node = node
    },
    // 点击角色授权弹窗确定
    authorizeSure() {
      console.log(this.authorizeMsg)
      this.authorizeDialog = false
    }
  }
}
</script>
<style lang="scss">
  .role-manage {
    .el-dialog__body {
      .menu-part {
        .menu {
          display: inline-block;
          min-width: 200px;
          vertical-align: top;
          .el-input,.el-textarea {
            width: 100%;
          }
        }
        .menu:last-child {
          width: 200px;
        }
      }
    }
    .authorize-dialog {
      .el-dialog {
        width: 470px;
      }
    }
  }
</style>
