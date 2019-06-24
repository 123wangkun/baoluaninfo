<template>
  <!-- 标签管理 -->
  <div class="tag-wrap flex">
    <div class="left">
      <div class="title">标签管理</div>
      <el-tree v-if="treeList.length > 0" ref="tree" :data="treeList" :props="defaultProps" node-key="id" @node-click="handleNodeClick" />
      <!-- 新增/修改标签弹窗 -->
      <el-dialog
        :visible.sync="addDialog"
        :title="operate === 0 ? '新增标签' : '修改标签信息'"
        class="edit-dialog"
        @close="closeDialog">
        <el-form
          ref="addTagForm"
          :model="addTagForm"
          :rules="addTagFormRules"
          label-position="left">
          <el-form-item prop="upTagName" class="dept mt-15">
            <div class="name">
              <i>*</i>
              <span>上级标签：</span>
            </div>
            <el-input
              v-model="addTagForm.upTagName"
              placeholder="请输入上级标签"
              class="search-input"/>
          </el-form-item>
          <el-form-item prop="tagName" class="dept mt-15">
            <div class="name">
              <i>*</i>
              <span>标签名称：</span>
            </div>
            <el-input
              v-model="addTagForm.tagName"
              placeholder="请输入标签名称"
              class="search-input"/>
          </el-form-item>
          <el-form-item prop="remark" class="dept mt-15">
            <div class="name">备注：</div>
            <el-input
              :rows="2"
              v-model="addTagForm.remark"
              type="textarea"
              placeholder="请输入备注信息"
              class="search-input"/>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="editSure">确 定</el-button>
        </span>
      </el-dialog>
      <!-- 删除标签弹窗 -->
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
    </div>
    <div class="right">
      <div class="search">
        <el-input
          v-model="tagName"
          placeholder="请输入标签名称"
          prefix-icon="el-icon-search"
          class="search-input search-user"/>
        <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
        <el-button type="primary" plain class="btn" @click="add">新增</el-button>
      </div>
      <el-table
        ref="multipleTable"
        :data="tableList"
        border
        stripe
        tooltip-effect="dark"
        class="table">
        <el-table-column
          type="index"
          width="55"
          label="序号"/>
        <el-table-column
          prop="tagName"
          label="标签名称"/>
        <el-table-column
          prop="upTagName"
          label="上级标签"
          min-width="114"/>
        <el-table-column
          prop="createTime"
          label="创建时间"
          min-width="100"/>
        <el-table-column
          prop="remark"
          label="备注"/>
        <el-table-column label="操作" min-width="140" >
          <template slot-scope="scope">
            <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
            <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Tag',
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      treeList: [{
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
      // treeList: [],
      tableList: [{
        upTagName: 'A',
        tagName: 'A01',
        createTime: '2019-03-10',
        remark: '备注信息'
      }],
      addTagForm: {},
      addTagFormRules: {
        upTagName: [
          { required: true, message: '请输入上级标签名字', trigger: 'blur' }
        ],
        tagName: [
          { required: true, message: '请输入标签名字', trigger: 'blur' }
        ]
      },
      addDialog: false,
      delDialog: false,
      tagName: '',
      operate: 0,
      selectIndex: 0,
      tagSelect: null,
      node: null
    }
  },
  methods: {
    // 选择标签
    handleNodeClick(node, data) {
      console.log(node)
      console.log(data)
      this.tagSelect = data
      this.node = node
    },
    // 搜索
    search() { },
    // 新增
    add() {
      this.addTagForm = {}
      this.operate = 0
      this.addDialog = true
    },
    // 修改
    edit(index, row) {
      this.selectIndex = index
      this.operate = 1
      this.addTagForm = {
        upTagName: row.upTagName,
        tagName: row.upTagName,
        remark: row.remark
      }
      this.addDialog = true
    },
    // 点击修改弹窗确定
    editSure() {
      this.$refs.addTagForm.validate(valid => {
        if (valid) {
          if (this.operate === 0) {
            const newChild = { id: this.id++, name: this.addTagForm.tagName, children: [] }
            const data = this.node
            if (!data.children) {
              this.$set(data, 'children', [])
            }
            data.children.push(newChild)
            this.tableList.push(this.addTagForm)
          } else {
            this.tableList.splice(this.selectIndex, 1, this.addTagForm)
          }
          this.addDialog = false
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
    }
  }
}
</script>
<style lang="scss">
  .tag-wrap{
    padding:20px 40px 20px 20px;
    .search-user {
      margin-right: 11px;
      width: 225px;
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
    }
    .right {
      width: calc(100% - 300px);
      .search {
        padding-bottom: 15px;
        .el-input__icon {
          line-height: 26px;
        }
        .el-range-separator {
          width: 13%;
          line-height: 26px;
        }
      }
      .table {
        width: 100%;
      }
    }
  }
</style>

