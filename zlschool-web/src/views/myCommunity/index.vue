<template>
  <div class="myCommunity">
    <div class=" left">
      <div class="hotGroup card">
        <ListTitle title="热门小组"/>
        <div class="hotGroupList">
          <div class="row">
            <div class="item">
              <img src="https://t.nxb100.com/upload/t_nxb100_com/sns/img/20190129/1548752507090128583.jpg">
              <div class="text-box">
                <div class="t1">养护之家</div>
                <div class="t2"><svg-icon icon-class="add" class="icon"/>加入小组</div>
              </div>
            </div>
            <div class="item">
              <img src="https://t.nxb100.com/upload/nxb100/sns/img/20180320/1521537153366955968.jpg">
              <div class="text-box">
                <div class="t1">养护之家</div>
                <div class="t2 c1"><svg-icon icon-class="gou" class="icon"/>已加入</div>
              </div>
            </div>
            <div class="item">
              <img src="https://t.nxb100.com/upload/nxb100/sns/img/20180320/1521537153366955968.jpg">
              <div class="text-box">
                <div class="t1">养护之家</div>
                <div class="t2"><svg-icon icon-class="add" class="icon"/>加入小组</div>
              </div>
            </div>
            <div class="item">
              <img src="https://t.nxb100.com/upload/nxb100/sns/img/20180320/1521537153366955968.jpg">
              <div class="text-box">
                <div class="t1">养护之家</div>
                <div class="t2"><svg-icon icon-class="add" class="icon"/>加入小组</div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="item">
              <img src="https://t.nxb100.com/upload/t_nxb100_com/sns/img/20190129/1548752507090128583.jpg">
              <div class="text-box">
                <div class="t1">养护之家</div>
                <div class="t2"><svg-icon icon-class="add" class="icon"/>加入小组</div>
              </div>
            </div>
            <div class="item">
              <img src="https://t.nxb100.com/upload/nxb100/sns/img/20180320/1521537153366955968.jpg">
              <div class="text-box">
                <div class="t1">养护之家</div>
                <div class="t2 c1"><svg-icon icon-class="gou" class="icon"/>已加入</div>
              </div>
            </div>
            <div class="item">
              <img src="https://t.nxb100.com/upload/nxb100/sns/img/20180320/1521537153366955968.jpg">
              <div class="text-box">
                <div class="t1">养护之家</div>
                <div class="t2"><svg-icon icon-class="add" class="icon"/>加入小组</div>
              </div>
            </div>
            <div class="item">
              <img src="https://t.nxb100.com/upload/nxb100/sns/img/20180320/1521537153366955968.jpg">
              <div class="text-box">
                <div class="t1">养护之家</div>
                <div class="t2"><svg-icon icon-class="add" class="icon"/>加入小组</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="topic card">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="热门话题" name="first"/>
          <el-tab-pane label="最新话题" name="second"/>
        </el-tabs>
        <ul class="topicList">
          <li class="item">
            <img src="https://t.nxb100.com//upload/t_nxb100_com/user/image/20180424/1524571293764242952.jpg?v=1551200466804">
            <div class="text-box">
              <div class="t1">尽心尽责除险情 排难解忧获称赞</div>
              <div class="t2">10月29日一大早，公司徐连养护处汾灌养护中心收到一面绣有真情援助、爱心永存的锦旗和一封表扬信，称赞养护员工</div>
              <div class="user-box">
                <div class="user">luoyelingluo&nbsp;&nbsp;&nbsp;&nbsp;来自于&nbsp;&nbsp;&nbsp;&nbsp;养护之家&nbsp;&nbsp;&nbsp;&nbsp;2018-03-20 11:26:10</div>
                <div class="user-count">
                  <div class="count"><svg-icon icon-class="dz" class="icon"/>2</div>
                  <div class="count"><svg-icon icon-class="hf" class="icon"/>9</div>
                  <div class="count"><svg-icon icon-class="yj" class="icon"/>42</div>
                </div>
              </div>
            </div>
          </li>
          <li class="item">
            <img src="https://t.nxb100.com//upload/t_nxb100_com/user/image/20180424/1524571293764242952.jpg?v=1551200466804">
            <div class="text-box">
              <div class="t1">尽心尽责除险情 排难解忧获称赞</div>
              <div class="t2">10月29日一大早，公司徐连养护处汾灌养护中心收到一面绣有真情援助、爱心永存的锦旗和一封表扬信，称赞养护员工</div>
              <div class="user-box">
                <div class="user">luoyelingluo&nbsp;&nbsp;&nbsp;&nbsp;来自于&nbsp;&nbsp;&nbsp;&nbsp;养护之家&nbsp;&nbsp;&nbsp;&nbsp;2018-03-20 11:26:10</div>
                <div class="user-count">
                  <div class="count"><svg-icon icon-class="dz" class="icon"/>2</div>
                  <div class="count"><svg-icon icon-class="hf" class="icon"/>9</div>
                  <div class="count"><svg-icon icon-class="yj" class="icon"/>42</div>
                </div>
              </div>
            </div>
          </li>
          <li class="item">
            <img src="https://t.nxb100.com//upload/t_nxb100_com/user/image/20180424/1524571293764242952.jpg?v=1551200466804">
            <div class="text-box">
              <div class="t1">尽心尽责除险情 排难解忧获称赞</div>
              <div class="t2">10月29日一大早，公司徐连养护处汾灌养护中心收到一面绣有真情援助、爱心永存的锦旗和一封表扬信，称赞养护员工</div>
              <div class="user-box">
                <div class="user">luoyelingluo&nbsp;&nbsp;&nbsp;&nbsp;来自于&nbsp;&nbsp;&nbsp;&nbsp;养护之家&nbsp;&nbsp;&nbsp;&nbsp;2018-03-20 11:26:10</div>
                <div class="user-count">
                  <div class="count"><svg-icon icon-class="dz" class="icon"/>2</div>
                  <div class="count"><svg-icon icon-class="hf" class="icon"/>9</div>
                  <div class="count"><svg-icon icon-class="yj" class="icon"/>42</div>
                </div>
              </div>
            </div>
          </li>
          <li class="item">
            <img src="https://t.nxb100.com//upload/t_nxb100_com/user/image/20180424/1524571293764242952.jpg?v=1551200466804">
            <div class="text-box">
              <div class="t1">尽心尽责除险情 排难解忧获称赞</div>
              <div class="t2">10月29日一大早，公司徐连养护处汾灌养护中心收到一面绣有真情援助、爱心永存的锦旗和一封表扬信，称赞养护员工</div>
              <div class="user-box">
                <div class="user">luoyelingluo&nbsp;&nbsp;&nbsp;&nbsp;来自于&nbsp;&nbsp;&nbsp;&nbsp;养护之家&nbsp;&nbsp;&nbsp;&nbsp;2018-03-20 11:26:10</div>
                <div class="user-count">
                  <div class="count"><svg-icon icon-class="dz" class="icon"/>2</div>
                  <div class="count"><svg-icon icon-class="hf" class="icon"/>9</div>
                  <div class="count"><svg-icon icon-class="yj" class="icon"/>42</div>
                </div>
              </div>
            </div>
          </li>
          <li class="item">
            <img src="https://t.nxb100.com//upload/t_nxb100_com/user/image/20180424/1524571293764242952.jpg?v=1551200466804">
            <div class="text-box">
              <div class="t1">尽心尽责除险情 排难解忧获称赞</div>
              <div class="t2">10月29日一大早，公司徐连养护处汾灌养护中心收到一面绣有真情援助、爱心永存的锦旗和一封表扬信，称赞养护员工</div>
              <div class="user-box">
                <div class="user">luoyelingluo&nbsp;&nbsp;&nbsp;&nbsp;来自于&nbsp;&nbsp;&nbsp;&nbsp;养护之家&nbsp;&nbsp;&nbsp;&nbsp;2018-03-20 11:26:10</div>
                <div class="user-count">
                  <div class="count"><svg-icon icon-class="dz" class="icon"/>2</div>
                  <div class="count"><svg-icon icon-class="hf" class="icon"/>9</div>
                  <div class="count"><svg-icon icon-class="yj" class="icon"/>42</div>
                </div>
              </div>
            </div>
          </li>
        </ul>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="listQuery.page"
          :limit.sync="listQuery.limit"
          @pagination="getList"
        />
      </div>
    </div>
    <div class="right">
      <div class="right-b1 card">
        <img src="https://t.nxb100.com/static/nxb/images/page/sns-bg.png">
        <div class="count">
          <div class="item">
            <div class="t1">0</div>
            <div class="t2">话题</div>
          </div>
          <div class="item">
            <div class="t1">0</div>
            <div class="t2">回复</div>
          </div>
          <div class="item">
            <div class="t1">1</div>
            <div class="t2">小组</div>
          </div>
        </div>
      </div>
      <div class="right-b2 card">
        <ListTitle title="我加入的小组"/>
        <ul class="myGroupList">
          <li class="item">
            <img src="https://t.nxb100.com/upload/t_nxb100_com/sns/img/20190129/1548752507090128583.jpg">
            <div class="text-box">
              <div class="t1">养护之家</div>
              <div class="t2">7位成员&nbsp;&nbsp;&nbsp;&nbsp;9条新话题</div>
            </div>
          </li>
        </ul>
      </div>
      <div class="right-b3 card">
        <ListTitle title="我参与的话题"/>
        <ul class="myJoinList">
          <li class="item">
            <svg-icon icon-class="jing" class="icon"/>
            <div class="title">尽心尽责除险情 排难解忧获称赞尽心尽责除险情 排难解忧获称赞</div>
            <div class="count">45</div>
          </li>
          <li class="item">
            <svg-icon icon-class="jing" class="icon"/>
            <div class="title">尽心尽责除险情 排难解忧获称赞尽心尽责除险情 排难解忧获称赞</div>
            <div class="count">45</div>
          </li>
          <li class="item">
            <svg-icon icon-class="jing" class="icon"/>
            <div class="title">尽心尽责除险情 排难解忧获称赞尽心尽责除险情 排难解忧获称赞</div>
            <div class="count">45</div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import buttonList from '@/components/buttonList'
import Pagination from '@/components/Pagination'
import ListTitle from './components/ListTitle'
export default {
  name: 'MyCommunity',
  components: { Pagination, buttonList, ListTitle },
  data() {
    return {
      total: 100,
      activeName: 'first',
      listQuery: {
        page: 1,
        limit: 10
      },
      state: '',
      states: [
        {
          value: '',
          name: '全部'
        },
        {
          value: 0,
          name: '机制'
        },
        {
          value: 1,
          name: '内部分享'
        },
        {
          value: -1,
          name: '人事部'
        },
        {
          value: 2,
          name: '产品技术'
        },
        {
          value: 3,
          name: '员工必读'
        },
        {
          value: 3,
          name: '生产部机制'
        }
      ],
      type: '',
      types: [
        {
          value: '',
          name: '最新'
        },
        {
          value: 0,
          name: '关注度'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/common.scss';
.myCommunity {
  padding: 20px;
  min-height: calc(100vh - 110px);
  background: #f8f9fd;
  display: flex;
  .card {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background: white;
  }
  .left {
    flex: 7;
    .hotGroup {
      padding: 20px;
      height: 233px;
      .hotGroupList {
        .row {
          display: flex;
          .item {
            padding: 10px;
            flex: 1;
            display: flex;
            cursor: pointer;
            img {
              background-color: #666666;
              border-radius: 4px;
              width: 56px;
              height: 56px;
              margin-right: 10px;
            }
            .text-box {
              flex: 1;
              display: flex;
              flex-direction: column;
              .t1 {
                width: 100%;
                font-size: 14px;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 1;
                overflow: hidden;
              }
              .t2 {
                font-size: 12px;
                margin-top: 15px;
                color: #31a4ff;
              }
              .c1 {
                color: #999;
              }
            }
          }
        }
      }
    }
    .topic {
      padding: 10px 20px;
      margin-top: 20px;
      .topicList {
        .item {
          display: flex;
          border-bottom: 1px solid #f2f2f2;
          padding: 20px 0 20px 15px;
          cursor: pointer;
          &:hover {
            background: #eee;
          }
          img {
            border-radius: 50%;
            width: 48px;
            height: 48px;
            margin-right: 10px;
          }
          .text-box {
            flex: 1;
            .t1 {
              font-size: 18px;
              color: #333;
            }
            .t2 {
              font-size: 14px;
              color: #999;
              margin: 5px 0 8px 0;
              display: -webkit-box;
              -webkit-box-orient: vertical;
              -webkit-line-clamp: 2;
              overflow: hidden;
              line-height: 23px;
            }
            .user-box {
              font-size: 14px;
              color: #999;
              display: flex;
              flex-direction: row;
              .user {
                flex: 1;
              }
              .user-count {
                display: flex;
                flex-direction: row;
                align-items: center;
                .count {
                  margin-right: 15px;
                }
              }
            }
          }
        }
      }
    }
  }
  .right {
    flex: 3;
    padding-left: 20px;
    .right-b1 {
      height: 233px;
      img {
        width: 100%;
      }
      .count {
        width: 100%;
        display: flex;
        flex-direction: row;
        .item {
          flex: 1;
          display: flex;
          flex-direction: column;
          align-items: center;
          cursor: pointer;
          .t1 {
            margin-top: 30px;
          }
          .t2 {
            margin-top: 20px;
          }
        }
      }
    }
    .right-b2 {
      margin-top: 20px;
      padding: 20px;
      .myGroupList {
        .item {
          padding: 10px;
          flex: 1;
          display: flex;
          cursor: pointer;
          img {
            background-color: #666666;
            border-radius: 4px;
            width: 56px;
            height: 56px;
            margin-right: 10px;
          }
          .text-box {
            flex: 1;
            display: flex;
            flex-direction: column;
            .t1 {
              width: 100%;
              font-size: 14px;
              display: -webkit-box;
              -webkit-box-orient: vertical;
              -webkit-line-clamp: 1;
              overflow: hidden;
              margin-top: 5px;
            }
            .t2 {
              font-size: 12px;
              margin-top: 15px;
              color: #999;
            }
            .c1 {
              color: #999;
            }
          }
        }
      }
    }
    .right-b3 {
      margin-top: 20px;
      padding: 20px;
      .myJoinList {
        .item {
          border-bottom: 1px dotted #f2f2f2;
          padding: 10px 0;
          display: flex;
          flex-direction: row;
          font-size: 14px;
          color: #666;
          align-items: center;
          .title {
            flex: 1;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 1;
            overflow: hidden;
            margin-left: 5px;
            cursor: pointer;
          }
        }
      }
    }
  }
}
</style>

