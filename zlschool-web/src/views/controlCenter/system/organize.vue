<template>
  <!-- 组织管理 -->
  <div class="organize-wrap flex">
    <div class="left">
      <div class="organize">
        <div class="title">组织管理</div>
        <el-button type="primary" class="btn" @click="addDept">新增</el-button>
        <el-button type="primary" icon="el-icon-edit" class="del-btn" @click="editDept"/>
        <el-upload
          :file-list="deptList"
          :before-upload="beforeUpload"
          :http-request="uploadExcel"
          :on-remove="handleRemove"
          :multiple="multiple"
          :show-file-list="false"
          action="customize"
          accept=".xls,.xlsx"
          list-type="picture"
          class="upload-btn">
          <el-button type="primary" icon="el-icon-upload" class="del-btn"/>
        </el-upload>
        <el-button type="primary" icon="el-icon-download" class="del-btn" @click="downloadOrganize"/>
        <el-button type="danger" icon="el-icon-delete" class="del-btn" @click="delDept"/>
      </div>
      <el-button v-if="organizeList.length == 0" type="primary" plain class="btn create" @click="createOrganize">创建组织</el-button>
      <el-tree v-if="organizeList.length > 0" ref="tree" :data="organizeList" :props="defaultProps" node-key="id" @node-click="handleNodeClick" />
      <!-- 新增部门弹窗 -->
      <el-dialog
        :visible.sync="addDeptDialog"
        :title="deptOperate === 0 ? '新增部门' : '编辑部门信息'"
        class="add-dialog"
        @close="closeDialog">
        <el-form
          ref="addDeptForm"
          :model="addDeptForm"
          :rules="addDeptFormRules"
          label-position="left">
          <el-form-item prop="upName" class="dept mt-15">
            <div class="name">
              <i>*</i>
              <span>上级名称：</span>
            </div>
            <el-input
              v-model="addDeptForm.upName"
              placeholder="请输入上级名称"
              class="search-input"/>
          </el-form-item>
          <el-form-item prop="deptName" class="dept mt-15">
            <div class="name">
              <i>*</i>
              <span>部门名称：</span>
            </div>
            <el-input
              v-model="addDeptForm.deptName"
              placeholder="请输入部门名称"
              class="search-input"/>
          </el-form-item>
          <el-form-item prop="deptCode" class="dept mt-15">
            <div class="name">
              <i>*</i>
              <span>部门编码：</span>
            </div>
            <el-input
              v-model="addDeptForm.deptCode"
              placeholder="请输入部门编码"
              class="search-input"/>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="addDeptSure">确 定</el-button>
        </span>
      </el-dialog>
      <!-- 删除组织弹窗 -->
      <el-dialog
        :visible.sync="delDeptDialog"
        title="您确定删除吗？"
        width="30%"
        @close="closeDialog">
        <span slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="deptDialogSure">确 定</el-button>
        </span>
      </el-dialog>
    </div>
    <div class="right">
      <div class="user flex">
        <span>用户管理</span>
        <div>
          <el-button type="primary" class="btn" @click="addStaff">新增</el-button>
          <el-upload
            :file-list="staffList"
            :before-upload="beforeUpload"
            :http-request="uploadExcel"
            :on-remove="handleRemove"
            :multiple="multiple"
            :show-file-list="false"
            action="customize"
            accept=".xls,.xlsx"
            list-type="picture"
            class="upload-btn">
            <el-button type="primary" icon="el-icon-upload" class="del-btn"/>
          </el-upload>
          <el-button type="primary" icon="el-icon-download" class="del-btn" @click="downloadStaff"/>
          <el-button type="danger" icon="el-icon-delete" class="del-btn" @click="delBatchStaff"/>
        </div>
      </div>
      <div class="search">
        <el-input
          v-model="searchStaff"
          placeholder="请输入用户名称"
          prefix-icon="el-icon-search"
          class="search-input mr-15"/>
        <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
      </div>
      <el-table
        ref="multipleTable"
        :data="tableList"
        border
        stripe
        tooltip-effect="dark"
        class="table"
        @selection-change="selectStaff">
        <el-table-column
          type="selection"
          width="55"/>
        <el-table-column
          type="index"
          width="55"
          label="序号"/>
        <el-table-column
          prop="name"
          label="用户姓名"/>
        <el-table-column
          prop="telePhone"
          label="手机号码"
          min-width="114"/>
        <el-table-column
          prop="createdTime"
          label="注册时间"
          min-width="100"/>
        <el-table-column
          prop="regStatusName"
          label="注册状态"/>
        <el-table-column
          prop="statusName"
          label="用户状态"/>
        <el-table-column prop="inviteLink" label="邀请链接">
          <template slot-scope="scope">
            <el-button :data-clipboard-text="scope.row.inviteLink" size="mini" class="copy" @click="copyLink">复制</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="certificate" label="用户证书">
          <template slot-scope="scope">
            <el-button size="mini" @click="viewCertificate">查看</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150" >
          <template slot-scope="scope">
            <el-button size="mini" type="primary" icon="el-icon-edit" @click="editStaff(scope.row)"/>
            <el-button size="mini" type="danger" icon="el-icon-delete" @click="delStaff(scope)"/>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="listQuery.page"
        :limit.sync="listQuery.limit"
        @pagination="getList"
      />
      <!-- 新增用户弹窗 -->
      <el-dialog
        :visible.sync="addStaffDialog"
        :title="staffOperate === 0 ? '新增用户' : '编辑用户信息'"
        class="staff-dialog"
        @close="closeDialog">
        <div class="staff-dialog-head">
          <el-button :class="barSelecte == 0 ? 'btn-select' : ''" class="btn" @click="selectInfo(0)">基本信息</el-button>
          <el-button :class="barSelecte == 1 ? 'btn-select' : ''" class="btn" @click="selectInfo(1)">角色权限</el-button>
        </div>
        <!--  基本信息 -->
        <div v-if="barSelecte == 0" class="info">
          <el-form
            ref="staffSelect"
            :model="staffSelect"
            :rules="staffSelectRules"
            label-position="left">
            <div class="info-left">
              <el-form-item prop="dept" class="dept">
                <div class="name">
                  <i>*</i>
                  <span>部门名称：</span>
                </div>
                <el-input
                  v-model="staffSelect.dept"
                  disabled
                  placeholder="请输入部门名称"
                  class="search-input"/>
              </el-form-item>
              <el-form-item prop="pwd" class="dept">
                <div class="name">
                  <i>*</i>
                  <span>用户密码：</span>
                </div>
                <el-input
                  v-model="staffSelect.pwd"
                  placeholder="请输入密码"
                  class="search-input"/>
              </el-form-item>
              <el-form-item prop="telePhone" class="dept">
                <div class="name">
                  <i>*</i>
                  <span>手机号码：</span>
                </div>
                <el-input
                  v-model="staffSelect.telePhone"
                  placeholder="请输入手机号码"
                  class="search-input"/>
              </el-form-item>
              <el-form-item prop="age" class="dept">
                <div class="name">
                  <i/>
                  <span>出生日期：</span>
                </div>
                <el-date-picker
                  v-model="staffSelect.age"
                  :editable="false"
                  type="date"
                  placeholder="请选择出生日期"
                  class="both"/>
              </el-form-item>
              <el-form-item prop="post" class="dept">
                <div class="name">
                  <i/>
                  <span>职务：</span>
                </div>
                <el-input
                  v-model="staffSelect.post"
                  placeholder="请输入职务"
                  class="search-input"/>
              </el-form-item>
              <el-form-item prop="education" class="dept">
                <div class="name">
                  <i/>
                  <span>学历：</span>
                </div>
                <el-input
                  v-model="staffSelect.education"
                  placeholder="请输入学历"
                  class="search-input"/>
              </el-form-item>
              <el-form-item prop="state" class="dept">
                <div class="name">
                  <i/>
                  <span>状态：</span>
                </div>
                <el-radio v-model="staffSelect.state" label="启用">启用</el-radio>
                <el-radio v-model="staffSelect.state" label="禁用">禁用</el-radio>
              </el-form-item>
            </div>
            <div class="info-left">
              <el-form-item prop="name" class="dept">
                <div class="name">
                  <i>*</i>
                  <span>姓名：</span>
                </div>
                <el-input
                  v-model="staffSelect.name"
                  placeholder="请输入姓名"
                  class="search-input"/>
              </el-form-item>
              <el-form-item prop="confirmPwd" class="dept">
                <div class="name">
                  <i>*</i>
                  <span>确认密码：</span>
                </div>
                <el-input
                  v-model="staffSelect.confirmPwd"
                  placeholder="请输入确认密码"
                  class="search-input"/>
              </el-form-item>
              <el-form-item prop="email" class="dept">
                <div class="name">
                  <i/>
                  <span>电子邮箱：</span>
                </div>
                <el-input
                  v-model="staffSelect.email"
                  placeholder="请输入电子邮箱"
                  class="search-input"/>
              </el-form-item>
              <el-form-item prop="sex" class="dept">
                <div class="name">
                  <i/>
                  <span>性别：</span>
                </div>
                <el-radio v-model="staffSelect.sex" :label="0">男</el-radio>
                <el-radio v-model="staffSelect.sex" :label="1">女</el-radio>
              </el-form-item>
              <el-form-item prop="position" class="dept">
                <div class="name">
                  <i/>
                  <span>职称：</span>
                </div>
                <el-input
                  v-model="staffSelect.position"
                  placeholder="请输入职称"
                  class="search-input"/>
              </el-form-item>
              <el-form-item prop="degree" class="dept">
                <div class="name">
                  <i/>
                  <span>学位：</span>
                </div>
                <el-input
                  v-model="staffSelect.degree"
                  placeholder="请输入学位"
                  class="search-input"/>
              </el-form-item>
            </div>
          </el-form>
        </div>
        <!-- 角色权限 -->
        <div v-if="barSelecte == 1" class="role">
          <el-table
            ref="multipleTable"
            :data="roleList"
            border
            stripe
            tooltip-effect="dark"
            class="table"
            @selection-change="selectRole">
            <el-table-column
              type="selection"
              width="55"/>
            <el-table-column
              type="index"
              label="序号"
              width="100"/>
            <el-table-column
              prop="name"
              label="角色名称"/>
          </el-table>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="addStaffSure">确 定</el-button>
        </span>
      </el-dialog>
      <!-- 查看证书弹窗 -->
      <el-dialog
        :visible.sync="certificateDialog"
        :title="'证书列表'"
        width="52%"
        class="certificate-dialog"
        @close="closeDialog">
        <el-table
          ref="multipleTable"
          :data="certificateList"
          border
          stripe
          tooltip-effect="dark"
          class="table">
          <el-table-column
            type="index"
            width="55"
            label="序号"/>
          <el-table-column
            prop="name"
            label="证书名称"/>
          <el-table-column
            prop="type"
            label="证书类型"/>
          <el-table-column
            prop="validity"
            label="有效期"
            width="100"/>
          <el-table-column
            prop="state"
            label="证书状态"/>
          <el-table-column
            prop="createTime"
            label="创建时间"
            width="100"/>
          <el-table-column
            prop="remark"
            label="备注"/>
          <el-table-column prop="url" label="证书路径">
            <template slot-scope="scope">
              <el-button size="mini" @click="viewCertificateUrl(scope.row.url)">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
        <span slot="footer" class="dialog-footer">
          <el-button @click="certificateDialog = false">取 消</el-button>
          <el-button type="primary" @click="certificateDialog = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import Clipboard from 'clipboard'
import { uploadFile } from '@/api/common'
import { getEnterpriseTree, addEnterprise, editDepartment, deleteEnterprise, getEnterprise } from '@/api/system/organize'
import { getUserList } from '@/api/system/user'
import { getRoleList } from '@/api/system/role'
export default {
  name: 'OrganizeManage',
  components: { Pagination },
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      // 初始化组织树列表
      organizeList: [],
      // 用户列表数据
      tableList: [],
      // 用户详情数据
      staffSelect: {
        index: 0,
        dept: '',
        name: '',
        pwd: '',
        confirmPwd: '',
        telePhone: '',
        email: '',
        ege: '',
        sex: 0,
        post: '',
        position: '',
        education: '',
        degree: '',
        state: 0
      },
      staffSelectRules: {
        dept: [
          { required: true, message: '请输入部门名称', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        telePhone: [
          { required: true, message: '请输入手机号码', trigger: 'blur' }
        ],
        pwd: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        confirmPwd: [
          { required: true, message: '请输入确认密码', trigger: 'blur' }
        ]
      },
      roleList: [{
        name: '超级管理员'
      }, {
        name: '企业管理员'
      }],
      certificateList: [{
        name: '王小虎',
        type: '学历',
        validity: '2019-08-10',
        state: '已审核',
        createTime: '2019-08-10',
        remark: '备注',
        url: 'http://ps7qkk8gn.bkt.clouddn.com/img9e1d2350b4434737ab9ef34042145c759.jpg'
      }],
      node: null,
      // 新增0,编辑1
      organizeOperate: 0,
      deptOperate: 0,
      date: '',
      listQuery: {
        page: 1,
        limit: 10
      },
      total: 0,
      barSelecte: 0,
      staffBatchSelect: [],
      certificateDialog: false,
      addDeptDialog: false,
      delDeptDialog: false,
      addStaffDialog: false,
      delStaffIndex: null,
      // 新增0,编辑1,删除2,批量删除3
      staffOperate: 0,
      addDeptForm: {
        upName: '',
        deptName: '',
        deptCode: ''
      },
      addDeptFormRules: {
        upName: [
          { required: true, message: '请输入上级名称', trigger: 'blur' }
        ],
        deptName: [
          { required: true, message: '请输入部门名称', trigger: 'blur' }
        ],
        deptCode: [
          { required: true, message: '请输入部门编码', trigger: 'blur' }
        ]
      },
      deptList: [],
      staffList: [],
      multiple: true,
      deptSelect: null,
      searchOrganize: '',
      searchStaff: '',
      id: 100,
      roleSelect: null
    }
  },
  created() {
    // 1:初始化搜索组织列表
    this.getEnterpriseTree()

    // 2:搜索列表用户信息
    this.getUserList('', '')
  },

  methods: {
    // 组织管理-----------------------------------------------------------------------------------
    // 初始化搜索组织列表
    getEnterpriseTree() {
      getEnterpriseTree().then(res => {
        this.organizeList = res.rows
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },

    // 搜索列表用户信息
    getUserList(depId, name) {
      const params = {
        departId: depId,
        realName: name,
        pageNum: 1,
        pageSize: 10
      }
      getUserList(params).then(res => {
        this.tableList = res.data_list
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },

    // 创建组织--弹出新增窗口
    createOrganize() {
      this.$router.push({
        path: '/controlCenter/system/createEnt'
      })
    },

    // 选择部门--获取树节点选中部门数据
    handleNodeClick(node, data) {
      this.deptSelect = data
      this.node = node

      // 搜索该部门下所有用户
      this.searchStaff = ''
      this.getUserList(this.deptSelect.data.id, this.searchStaff)
    },

    getList() {},

    // 上传文件前限制大小
    beforeUpload(file) {
      const size = file.size / 1024 / 1024
      if (size > 2) {
        this.$message.info('大小不可超过2M')
        return false
      }
    },

    // 删除图片
    handleRemove() {
      // const params = {
      //   fileUrl: this.addMsg.license[0].url
      // }
      // delFile(params).then(res => {
      //   this.addMsg.license[0].url = ''
      // }).catch(error => {
      //   this.$message.error(error.err_msg)
      // })
    },

    // 新增部门弹窗
    addDept() {
      this.deptOperate = 0
      if (this.deptSelect) {
        this.addDeptForm.upName = this.deptSelect.data.name
        this.addDeptDialog = true
      } else {
        this.$message.info('请先选择部门')
      }
    },

    // 修改部门
    editDept() {
      if (this.deptSelect) {
        const departmentParams = {
          id: this.deptSelect.data.id
        }
        getEnterprise(departmentParams).then(res => {
          // 部门修改界面
          this.addDeptForm.upName = res.parentName
          this.addDeptForm.deptName = res.name
          this.addDeptForm.deptCode = res.code
          this.deptOperate = 1
          this.addDeptDialog = true
        }).catch(error => {
          this.$message.error(error.err_msg)
        })
      } else {
        this.$message.info('请先选择部门')
      }
    },

    // 点击新增/修改部门弹窗确定
    addDeptSure() {
      this.$refs.addDeptForm.validate(valid => {
        if (valid) {
          if (this.deptOperate === 0) {
            const departmentParams = {
              name: this.addDeptForm.deptName,
              code: this.addDeptForm.deptCode,
              parentId: this.deptSelect.data.id
            }
            addEnterprise(departmentParams).then(res => {
              this.addDeptDialog = false
              this.getEnterpriseTree()
            }).catch(error => {
              this.$message.error(error.err_msg)
            })
            // const newChild = { id: this.id++, name: this.addDeptForm.deptName, children: [] }
            // const data = this.node
            // if (!data.children) {
            //   this.$set(data, 'children', [])
            // }
            // data.children.push(newChild)
          } else if (this.deptOperate === 1) {
            const departmentParams = {
              depId: this.deptSelect.data.id,
              name: this.addDeptForm.deptName,
              code: this.addDeptForm.deptCode,
              parentId: this.deptSelect.data.parentId
            }
            editDepartment(departmentParams).then(res => {
              this.addDeptDialog = false
              this.getEnterpriseTree()
            }).catch(error => {
              this.$message.error(error.err_msg)
            })
          }
        }
      })
    },

    // 删除部门
    delDept() {
      this.deptOperate = 3
      if (this.deptSelect) {
        this.delDeptDialog = true
      } else {
        this.$message.info('请先选择部门')
      }
    },
    deptDialogSure() {
      this.delDeptDialog = false
      // 删除部门
      if (this.deptOperate === 3) {
        const departmentParams = {
          depId: this.deptSelect.data.id
        }
        deleteEnterprise(departmentParams).then(res => {
          this.getEnterpriseTree()
        }).catch(error => {
          this.$message.error(error.err_msg)
        })
        // const parent = this.deptSelect.parent
        // const children = parent.data.children || parent.data
        // const index = children.findIndex(d => d.id === this.node.id)
        // children.splice(index, 1)
      }
      // 删除单个用户
      if (this.staffOperate === 3) {
        this.tableList.splice(this.delStaffIndex, 1)
      } else if (this.staffOperate === 4) {
        // 批量删除用户
        for (const i in this.tableList) {
          for (const j in this.staffBatchSelect) {
            if (this.tableList[i].id === this.staffBatchSelect[j].id) {
              this.tableList.splice(i, 1)
            }
          }
        }
      }
    },
    // 导入组织/导入用户
    uploadExcel(param) {
      const fileObj = new FormData()
      fileObj.append('editormd-image-file', param.file)
      uploadFile(fileObj, 8).then(res => {
        this.$message.success('导入成功')
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 下载组织
    downloadOrganize() {
      window.location.href = 'https://res.dev.deecard.net/img33b807200113426b8333751444dcfe477.xlsx'
    },

    // 用户管理-----------------------------------------------------------------------------------
    // 搜索
    search() {
      // 搜索该部门下所有用户
      if (this.deptSelect) {
        this.getUserList(this.deptSelect.data.id, this.searchStaff)
      } else {
        this.getUserList('', this.searchStaff)
      }
    },
    // 选择用户
    selectStaff(val) {
      this.staffBatchSelect = val
    },
    // 新增用户弹窗
    addStaff() {
      if (!this.deptSelect) {
        this.$message.info('请先选择部门')
        return
      }
      this.staffOperate = 0
      this.addStaffDialog = true
      // 默认赋值
      this.staffSelect.dept = this.deptSelect.data.name
      this.staffSelect.sex = 0
      this.staffSelect.state = 0
      // 搜索角色列表
      const params = {
      }
      getRoleList(params).then(res => {
        console.log('111111111111111111' + JSON.stringify(res))
      }).catch(error => {
        console.log('222222222222222222' + JSON.stringify(error))
        this.$message.error(error.err_msg)
      })
    },
    // 修改用户资料弹窗
    editStaff(row) {
      if (!row) {
        this.$message.info('请先选择用户')
        return
      }
      this.staffOperate = 1
      this.staffSelect = {
        index: row.index,
        dept: row.dept,
        name: row.name,
        pwd: row.pwd,
        confirmPwd: row.confirmPwd,
        telePhone: row.telePhone,
        email: row.email,
        ege: row.ege,
        sex: row.sex,
        post: row.post,
        position: row.position,
        education: row.education,
        degree: row.degree,
        state: row.state
      }
      this.staffSelect.index = row.$index
      this.addStaffDialog = true
    },
    // 点击新增/修改用户弹窗确定
    addStaffSure() {
      this.$refs.staffSelect.validate(valid => {
        if (valid) {
          if (this.staffOperate === 0) {
            this.tableList.push(this.staffSelect)
            this.addStaffDialog = false
          } else if (this.staffOperate === 1) {
            this.tableList.splice(this.staffSelect.index, 0, this.staffSelect)
          }
        }
      })
    },
    // 关闭创建组织弹窗，新增/修改部门弹窗，用户弹窗
    closeDialog() {
      this.addDeptDialog = false
      this.addStaffDialog = false
      this.delDeptDialog = false
    },
    // 删除单个用户
    delStaff(row) {
      this.staffOperate = 3
      this.delStaffIndex = row.$index
      this.delDeptDialog = true
    },
    // 批量删除用户
    delBatchStaff() {
      this.staffOperate = 4
      if (this.staffBatchSelect.length < 1) {
        this.$message.info('请先选择用户')
      } else {
        this.delDeptDialog = true
      }
    },
    // 上传用户列表
    uploadStaff() {},
    // 下载用户列表
    downloadStaff() {
      window.location.href = 'https://res.dev.deecard.net/img33b807200113426b8333751444dcfe477.xlsx'
    },
    // 选择基本信息
    selectInfo(type) {
      this.barSelecte = type
    },
    // 角色授权
    selectRole(val) {
      this.roleSelect = val
    },
    // 复制链接
    copyLink() {
      var clipboard = new Clipboard('.copy')
      clipboard.on('success', e => {
        this.$message.success('复制成功')
        // 释放内存
        clipboard.destroy()
      })
      clipboard.on('error', e => {
        // 不支持复制
        this.$message.info('该浏览器不支持自动复制')
        // 释放内存
        clipboard.destroy()
      })
    },
    // 查看证书
    viewCertificate(row) {
      this.certificateDialog = true
    },
    viewCertificateUrl(url) {
      window.open(url)
    }
  }
}
</script>
<style lang="scss">
  .organize-wrap{
    padding:20px 40px 20px 20px;
    .create {
      display: block;
      margin: 15px 0 0 98px;
    }
    .upload-btn {
      display: inline-block;
      margin:0 10px;
      vertical-align: top;
    }
    .left {
      width: 300px;
      .organize {
        .title {
          padding-bottom: 5px;
          line-height: 30px;
        }
      }
      .el-tree {
        margin-top: 20px;
        width: 280px;
      }
      .el-tree-node:focus>.el-tree-node__content {
        background-color: #ecf5ff;
      }
      .el-tree-node__content:hover {
        background-color: #ecf5ff;
      }
      .upload-demo {
        padding-top: 25px;
        .el-upload-dragger {
          width: 350px;
        }
      }
      .el-upload-list--picture{
        display:-webkit-box;
        display: -webkit-flex;
        display: flex;
        flex-wrap: wrap;
        .el-upload-list__item {
          margin-right: 10px;
          padding: 0px 0px 0px 60px;
          width: 60px;
          height: 60px;
          border: 0;
          .el-upload-list__item-thumbnail {
            margin-left: -60px;
            width: 60px;
            height: 60px;
          }
          .el-upload-list__item-status-label,.el-icon-close {
            z-index: 99;
          }
        }
      }
      .add-dialog {
        .el-dialog {
          width: 410px;
        }
      }
    }
    .right {
      width: calc(100% - 300px);
      .user {
        padding-bottom: 5px;
        line-height: 30px;
        justify-content: space-between;
      }
      .search {
        padding-bottom: 15px;
      }
      .table {
        width: 100%;
      }
    }
    .staff-dialog {
      .el-dialog {
        width: 715px!important;
        height: 623px;
      }
      .el-dialog__body {
        margin-left: 0;
      }
      .staff-dialog-head {
        padding-bottom: 30px;
        .btn {
          border-radius: 0;
        }
        .btn-select {
          color: #fff;
          background: #409EFF;
        }
        .btn:nth-child(2) {
          margin-left: -5px;
        }
      }
      .info {
        .info-left {
          display: inline-block;
          width: 313px;
          vertical-align: top;
        }
        .info-left:first-child {
          margin-right: 35px;
        }
      }
      .role {
        height: 350px;
        .table {
          width: 91%;
        }
      }
      .dept {
        .post{
          margin-left: 5px;
          .el-checkbox {
            display: block;
            padding-bottom: 10px;
          }
        }
        .el-select{
          margin-left: 4px;
        }
        .el-radio {
          margin-right: 20px;
        }
        .el-radio__label {
          padding-left: 5px;
        }
      }
    }
    .upload-size {
      width: 130px;
      text-align: left;
    }
  }
</style>

