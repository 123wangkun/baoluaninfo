import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  // 登录
  {
    path: '/login',
    component: () => import('@/views/userInfo/login'),
    hidden: true
  },
  // 注册
  {
    path: '/register',
    name: 'register',
    component: () => import('@/views/userInfo/register'),
    hidden: true
  },
  // 验证手机号码
  {
    path: '/validateTel',
    component: () => import('@/views/userInfo/validateTel'),
    hidden: true
  },
  // 忘记密码
  {
    path: '/forgetpwd',
    name: 'forgetpwd',
    component: () => import('@/views/userInfo/forgetpwd'),
    hidden: true
  },
  // 修改密码
  {
    path: '/editpwd',
    name: 'editpwd',
    component: () => import('@/views/userInfo/editpwd'),
    hidden: true
  },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'dashboard',
    hidden: true,
    children: [
      {
        path: 'dashboard',
        name: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '主页', icon: '' }
      },
      {
        path: 'noticeDetail',
        component: () => import('@/views/dashboard/noticeDetail'),
        name: 'noticeDetail',
        meta: { title: '　', noCache: true },
        hidden: true
      },
      {
        path: 'noticeList',
        component: () => import('@/views/dashboard/noticeList'),
        name: 'noticeList',
        meta: { title: '　', noCache: true },
        hidden: true
      }
    ]
  },

  {
    path: '/learn',
    component: Layout,
    redirect: '/learn/learnCenter',
    isExternalLink: true,
    name: 'Example',
    meta: { title: '学习中心', icon: 'xxzx' },
    children: [
      {
        path: 'offline',
        name: 'Table',
        component: () => import('@/views/learnOffline/index'),
        meta: { title: '线下学习', icon: '' }
      },
      {
        path: 'offlineDetail',
        component: () => import('@/views/learnOffline/detail'),
        name: 'offlineDetail',
        meta: { title: '线下学习详情', noCache: true },
        hidden: true
      },
      {
        path: 'learnOnline',
        name: 'learnOnline',
        component: () => import('@/views/learnOnline/index'),
        meta: { title: '线上学习', icon: '' }
      },
      {
        path: 'learnCenter',
        component: () => import('@/views/learnCenter/index'),
        name: 'learnCenter',
        meta: { title: '学习统计', noSelect: true },
        hidden: true
      },
      {
        path: 'courseDetail',
        name: 'courseDetail',
        component: () => import('@/views/learnOnline/courseDetail'),
        meta: { title: '课程详情', icon: '' },
        hidden: true
      },
      {
        path: 'courseList2',
        name: 'courseList2',
        component: () => import('@/views/learnOnline/courseList2'),
        meta: { title: '　', icon: '' },
        hidden: true
      },
      {
        path: 'courseList3',
        name: 'courseList3',
        component: () => import('@/views/learnOnline/courseList3'),
        meta: { title: '　', icon: '' },
        hidden: true
      }
    ]
  },

  {
    path: '/exam',
    component: Layout,
    redirect: '/exam/examCenter',
    isExternalLink: true,
    name: 'exam',
    meta: {
      title: '考试中心',
      icon: 'kszx'
    },
    children: [
      {
        path: 'Offline',
        component: () => import('@/views/examOffline/index'), // Parent router-view
        name: 'examOffline',
        meta: { title: '线下考试' }
      },
      {
        path: 'Online',
        component: () => import('@/views/examOnline/index'),
        name: 'examOnline',
        meta: { title: '线上考试' }
      },
      {
        path: 'examCenter',
        component: () => import('@/views/examCenter/index'),
        name: 'examCenter',
        meta: { title: '考试统计', noSelect: true },
        hidden: true
      },
      {
        path: 'OnlineDetail',
        component: () => import('@/views/examOnline/detail'),
        name: 'examOnlineDetail',
        hidden: true,
        meta: { title: '考试详情' }
      }
    ]
  },
  {
    path: '/certificate',
    component: Layout,
    redirect: '/certificate/index',
    isExternalLink: true,
    name: 'certificate',
    meta: {
      title: '证书中心',
      icon: 'zszx'
    },
    children: [
      {
        path: 'index1',
        name: '我的证书',
        component: () => import('@/views/certificate/index1'),
        meta: { title: '我的证书', icon: '' }
      },
      {
        path: 'index',
        name: '证书统计',
        component: () => import('@/views/certificate/index'),
        meta: { title: '证书统计', noSelect: true },
        hidden: true,
        isStatistics: true // 是否是统计页面
      }
    ]
  },
  {
    path: '/userInfo',
    component: Layout,
    redirect: '/userInfo/score',
    name: 'userInfo',
    hidden: true,
    children: [
      {
        path: 'index',
        name: '个人中心',
        component: () => import('@/views/userInfo/index'),
        meta: { title: '个人中心', icon: '' }
      },
      {
        path: 'score',
        component: () => import('@/views/userInfo/score'),
        name: 'score',
        meta: { title: '我的任务', noCache: true },
        hidden: true
      },
      {
        path: 'scoreInfo',
        component: () => import('@/views/userInfo/scoreInfo'),
        name: 'scoreInfo',
        meta: { title: '学分规则', noCache: true },
        hidden: true
      }
    ]
  },
  {
    path: '/jobTrain',
    component: Layout,
    redirect: '/jobTrain/index',
    name: 'jobTrain',
    children: [
      {
        path: 'index',
        name: '岗位培训',
        component: () => import('@/views/jobTrain/index'),
        meta: { title: '岗位培训', icon: 'gwpx' }
      },
      {
        path: 'detail',
        name: 'jobTrainDetail',
        component: () => import('@/views/jobTrain/detail'),
        meta: { title: '培训详情', icon: '' },
        hidden: true
      }
    ]
  },
  {
    path: '/myCommunity',
    component: Layout,
    redirect: '/myCommunity/index',
    name: 'myCommunity',
    children: [
      {
        path: 'index',
        name: '我的社区',
        component: () => import('@/views/myCommunity/index'),
        meta: { title: '我的社区', icon: 'wdsq' }
      }
    ]
  },
  {
    path: '/controlCenter',
    component: Layout,
    isExternalLink: false,
    name: 'controlCenter',
    meta: { title: '管理中心', icon: 'glzx' },
    children: [
      {
        path: 'system',
        component: () => import('@/views/controlCenter/index'),
        isExternalLink: false,
        name: 'system',
        meta: { title: '系统管理' },
        children: [
          {
            path: 'createEnt',
            component: () => import('@/views/controlCenter/system/createEnt'),
            name: 'createEnt',
            meta: { title: '创建企业' }
          },
          {
            path: 'organize',
            component: () => import('@/views/controlCenter/system/organize'),
            name: 'organize',
            meta: { title: '组织管理' }
          },
          {
            path: 'role',
            component: () => import('@/views/controlCenter/system/role'),
            name: 'role',
            meta: { title: '角色管理' }
          },
          {
            path: 'ent',
            component: () => import('@/views/controlCenter/system/ent'),
            name: 'ent',
            meta: { title: '企业管理' }
          },
          {
            path: 'user',
            component: () => import('@/views/controlCenter/system/user'),
            name: 'user',
            meta: { title: '个人用户' }
          }
        ]
      },
      {
        path: 'certificate',
        component: () => import('@/views/controlCenter/index'),
        isExternalLink: false,
        name: 'certificate',
        meta: { title: '证书管理' },
        children: [
          {
            path: 'type',
            component: () => import('@/views/controlCenter/certificate/type'),
            name: 'type',
            meta: { title: '证书类型' }
          },
          {
            path: 'list',
            component: () => import('@/views/controlCenter/certificate/list'),
            name: 'list',
            meta: { title: '证书列表' }
          }
        ]
      },
      {
        path: 'publish',
        component: () => import('@/views/controlCenter/index'),
        isExternalLink: false,
        name: 'publish',
        meta: { title: '发布管理' },
        children: [
          {
            path: 'announcement',
            component: () => import('@/views/controlCenter/publish/announcement'),
            name: 'announcement',
            meta: { title: '通知公告' }
          },
          {
            path: 'training',
            component: () => import('@/views/controlCenter/publish/training'),
            name: 'training',
            meta: { title: '培训活动' }
          }
        ]
      },
      {
        path: 'tag',
        component: () => import('@/views/controlCenter/tag/index'),
        name: 'tag',
        meta: { title: '标签管理' }
      },
      {
        path: 'course',
        component: () => import('@/views/controlCenter/index'),
        isExternalLink: false,
        name: 'course',
        meta: { title: '课程管理' },
        children: [
          {
            path: 'type',
            component: () => import('@/views/controlCenter/course/type'),
            name: 'type',
            meta: { title: '课程类型' }
          },
          {
            path: 'offline',
            component: () => import('@/views/controlCenter/course/offline'),
            name: 'offline',
            meta: { title: '线下课程' }
          },
          {
            path: 'online',
            component: () => import('@/views/controlCenter/course/online'),
            name: 'online',
            meta: { title: '线上课程' }
          }
        ]
      },
      {
        path: 'exam',
        component: () => import('@/views/controlCenter/index'),
        isExternalLink: false,
        name: 'exam',
        meta: { title: '考试管理' },
        children: [
          {
            path: 'question',
            component: () => import('@/views/controlCenter/exam/question'),
            name: 'question',
            meta: { title: '题库维护' }
          },
          {
            path: 'paperType',
            component: () => import('@/views/controlCenter/exam/paperType'),
            name: 'paperType',
            meta: { title: '试卷类型' }
          },
          {
            path: 'examOffline',
            component: () => import('@/views/controlCenter/exam/examOffline'),
            name: 'examOffline',
            meta: { title: '线下考试' }
          },
          {
            path: 'examOnline',
            component: () => import('@/views/controlCenter/exam/examOnline'),
            name: 'examOnline',
            meta: { title: '线上考试' }
          }
        ]
      },
      {
        path: 'train',
        component: () => import('@/views/controlCenter/index'),
        name: 'train',
        meta: { title: '培训管理' },
        children: [
          {
            path: 'index',
            component: () => import('@/views/controlCenter/train'),
            name: 'index',
            meta: { title: '培训管理' }
          }
        ]
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
