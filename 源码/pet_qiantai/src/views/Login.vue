<template>
  <div class="container">
    <div class="login-container">
      <div class="login-header">
        <img src="../assets/宠物天地.png" alt="logo" class="login-logo" />
        <h1>同城宠物喂溜系统</h1>
      </div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" prefix-icon="el-icon-user" v-model="user.username" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item prop="password" style="margin-bottom: 30px">
          <el-input size="medium" prefix-icon="el-icon-lock" show-password v-model="user.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item class="button-container">
          <el-button type="primary" size="medium" @click="login">登录</el-button>
          <el-button type="warning" size="medium" @click="$router.push('/register')">前往注册</el-button>
          <el-button type="success" size="medium" @click="handlePass">找回密码</el-button>
        </el-form-item>
      </el-form>
      <el-dialog title="找回密码" :visible.sync="dialogFormVisible" width="30%" >
        <el-form label-width="100px" size="small">
          <el-form-item label="用户名">
            <el-input v-model="pass.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input v-model="pass.phone" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="passwordBack">重置密码</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {resetRouter, setRoutes} from "@/router";

export default {
  name: "Login",
  data() {
    return {
      user: {},
      pass: {},
      dialogFormVisible: false,
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  created() {
    // 重置路由
    resetRouter()
  },
  methods: {
    login() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.post("/user/login", this.user).then(res => {
            if(res.code === '200') {
              localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器
              localStorage.setItem("menus", JSON.stringify(res.data.menus))  // 存储用户信息到浏览器

              // 动态设置当前用户的路由
              setRoutes()
              this.$router.push("/front/home")
              this.$message.success("登录成功")
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },
    handlePass() {
      this.dialogFormVisible = true
      this.pass = {}
    },
    passwordBack() {
      this.request.put("/user/reset", this.pass).then(res => {
        if (res.code === '200') {
          this.$message.success("重置密码成功，新密码为：123，请尽快修改密码")
          this.dialogFormVisible = false
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  overflow: hidden;
  background-image: url("../assets/bg2.png");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}

.login-container {
  width: 350px;
  /* 修改背景颜色为透明，这里使用 rgba 设置透明度为 0.8，你可以根据需要调整 */
  background-color: rgba(255, 255, 255, 0.8); 
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.login-header {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 30px;
}

.login-logo {
  width: 40px;  /* 可以根据实际情况调整 logo 的宽度 */
  height: 40px; /* 可以根据实际情况调整 logo 的高度 */
  margin-right: 10px; /* 调整 logo 和文字之间的间距 */
}

.login-header h1 {
  text-align: center;
  font-size: 28px;
  margin-bottom: 0;
  color: #000;
}

.el-form-item {
  margin-bottom: 20px;
}

.button-container {
  display: flex;
  justify-content: space-around;
}

.dialog-footer {
  text-align: right;
}
</style>