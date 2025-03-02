<template>
  <div class="container">

    <div style="width: 25%; display: flex;background-color: white;">

      <div style="flex: 1;width: 50%;padding: 50px;display: flex;flex-direction: column;justify-content: center;" >
        <div style="text-align: center; font-size: 30px; margin-bottom: 50px; color: #333 ;font-size: 30px;color: #000000" >宠 物 领 养 系 统</div>
          <el-form :model="user" :rules="rules" ref="userForm">
            <el-form-item prop="username">
              <el-input size="medium" prefix-icon="el-icon-user" v-model="user.username" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item prop="password" style="margin-bottom: 50px">
              <el-input size="medium" prefix-icon="el-icon-lock" show-password v-model="user.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item style="margin-top: 30px; display: flex;">
              <el-button style="flex: 1;width: 100px" type="primary" size="small"  autocomplete="off" @click="login">登录</el-button>
              <el-button style="flex: 1 ;width: 100px" type="warning" size="small"  autocomplete="off" @click="$router.push('/register')">前往注册</el-button>
              <el-button style=" flex: 1 ;width: 100px;background-color: #67C23A;color: #ffffff" size="small" autocomplete="off" @click="handlePass">找回密码</el-button>
            </el-form-item>
          </el-form>
        </div>

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

<style>
.container {
  height: 100vh;
  overflow: hidden;
  /*background-color: #dcd5b0;*/
  background-image: url("../assets/bg2.png");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #4169E1 , 	#87CEFA);
  overflow: hidden;
}
</style>
