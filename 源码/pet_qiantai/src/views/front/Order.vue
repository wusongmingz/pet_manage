<template>
  <div class="order-page">
    <h1 class="page-title">订单记录</h1>
    <div class="order-record" v-for="(order, id) in userOrder" :key="id" @mouseenter="onMouseEnter(id)" @mouseleave="onMouseLeave(id)" :class="{ 'hovered': hoveredIndex === id }">
      <!-- 姓名 -->
      <div class="record-item">
        <span class="record-label">姓名</span>
        <span class="record-value">{{ order.name || '暂无信息' }}</span>
      </div>
      <!-- 地点 -->
      <div class="record-item">
        <span class="record-label">地点</span>
        <span class="record-value">{{ order.location || '暂无信息' }}</span>
      </div>
      <!-- 备注 -->
      <div class="record-item">
        <span class="record-label">备注</span>
        <span class="record-value">{{ order.petInfo || '暂无信息' }}</span>
      </div>
      <!-- 上门时间 -->
      <div class="record-item">
        <span class="record-label">上门时间</span>
        <span class="record-value">{{ order.timeRange || '暂无信息' }}</span>
      </div>
      <!-- 选择的服务 -->
      <div class="record-item">
        <span class="record-label">选择的服务</span>
        <span class="record-value">{{ order.serviceTitle || '暂无信息' }}</span>
      </div>
      <!-- 完成状态 -->
      <div class="record-item">
        <span class="record-label">完成状态</span>
        <span class="record-value" v-if="order.status === 'COMPLETED'">已完成</span>
        <span class="record-value" v-else>未完成</span>
      </div>
    </div>
  </div>
</template>
<script>
// 假设这里引入了 axios 作为请求库
import axios from 'axios';

export default {
  name: "order",
  data() {
    return {
      userOrder: [],
      hoveredIndex: null
    }
  },
  created() {
    this.getData();
  },
  methods: {
    async getData() {
      const userData = localStorage.getItem('user');
      if (userData) {
        const user = JSON.parse(userData);
        console.log(user);
        const token = user.token;
        try {
          const res = await axios.get('http://localhost:9090/bookings/byUsername', { params: { name: user.username }, headers: {
            token: token
          } });
          console.log(res.data);
          // 将获取到的数据更新到组件状态
          this.userOrder = res.data;
        } catch (error) {
          console.error('获取订单数据失败:', error);
        }
      }
    },
    onMouseEnter(index) {
      this.hoveredIndex = index;
    },
    onMouseLeave() {
      this.hoveredIndex = null;
    }
  }
}
</script>
<style scoped>
/* 为 body 设置背景颜色 */
body {
  background-color: #f4f4f4; /* 这里可以根据需要修改背景颜色 */
  margin: 0;
  padding: 0;
}

.order-page {
  font-family: Arial, sans-serif;
  padding: 20px;
  max-width: 800px; /* 适当增加宽度 */
  margin: 0 auto;
  background-color: transparent; /* 设置为透明背景 */
  /* 设置最小高度，确保背景色覆盖一定范围 */
  min-height: calc(100vh - 40px); 
}

.page-title {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
  font-size: 28px; /* 增大标题字体大小 */
  animation: fadeInDown 1s ease; /* 添加标题动画 */
}

@keyframes fadeInDown {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.order-record {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 30px;
  margin-bottom: 20px; /* 增加订单记录之间的间距 */
  transition: all 0.3s ease; /* 添加过渡动画 */
}

.order-record.hovered {
  transform: scale(1.02); /* 鼠标悬停时放大 */
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.2); /* 鼠标悬停时增加阴影 */
}

.record-item {
  display: flex;
  align-items: center;
  margin-bottom: 15px; /* 增加记录项之间的间距 */
}

.record-label {
  width: 150px; /* 适当增加标签宽度 */
  font-weight: bold;
  color: #555;
  font-size: 16px; /* 增大标签字体大小 */
}

.record-value {
  color: #333;
  font-size: 16px; /* 增大值字体大小 */
}
</style>
