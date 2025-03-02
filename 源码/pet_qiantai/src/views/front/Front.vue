<template>
  <div style="background: #F0F8FF">
<!--    头部-->
    <div style="display: flex; background-color: #fff; height: 60px; line-height: 60px; border-bottom: 1px solid #eee">
      <div style="width: 240px; display: flex; padding-left: 30px">
        <div style="width: 60px">
          <img src="../../assets/宠物天地.png" alt="" style="width: 40px; position: relative; top: 10px; right: 0">
        </div>
        <div style="flex: 1; font-size: 20px; color: #1E90FF; font-weight: bold;width: 100px;">同城宠物溜喂</div>
      </div>
      <div >

        <el-menu :default-active="'1'" class="el-menu-demo" mode="horizontal" router>
          <el-menu-item index="/front/home">首页</el-menu-item>
         
          <el-menu-item index="/front/salvation">成为'喂溜专员'</el-menu-item>
          <el-menu-item index="/front/feed">同城喂溜服务预订</el-menu-item>
          <el-menu-item index="/front/lost">喂溜优秀服务专员</el-menu-item>
          <el-menu-item index="/front/rescue">线下溜宠打卡地点推荐</el-menu-item>
          <el-menu-item index="/front/article">宠物异常行为与健康咨询</el-menu-item>
          <el-menu-item index="/front/donate">关于我们</el-menu-item>
          <el-menu-item index="/front/articleKp">宠物资讯</el-menu-item>
          <el-menu-item index="/front/activity">热门活动</el-menu-item>
        </el-menu>
      </div>
      <div style="width: 160px">
        <div v-if="!user.username" style="text-align: right; padding-right: 30px">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
            <div style="display: inline-block">
              <img :src="user.avatarUrl" alt=""
                   style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
              <span>{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
              <el-dropdown-item style="font-size: 14px; padding: 5px 0" v-if="user.role === 'ROLE_ADMIN'">
                <router-link to="/">后台管理</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <router-link to="/front/password">修改密码</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <router-link to="/front/person">个人信息</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <span style="text-decoration: none" @click="logout">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>

    <div style="width: 1200px; margin: 0 auto">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "Front",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {

  },
  methods: {
    logout() {
      this.$store.commit("logout")
      this.$message.success("退出成功")
    }
  }
}
</script>

<style>
.item {
  display: inline-block;
  width: 100px;
  text-align: center;
  cursor: pointer;
}

.item a {
  color: #1E90FF;
}

.item:hover {
  background-color: LightPink;
}

::v-deep .el-menu-demo .el-menu-item:hover {
  background-color: LightPink; /* 修改为你想要的悬浮色 */
  color: #1E90FF; /* 修改为你想要的文字颜色 */
}
</style>

