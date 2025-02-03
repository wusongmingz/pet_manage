<template>
  <div class="dog-walking-service">
    <h1>上门遛狗</h1>
    <p>5大基础服务</p>
    <div class="service-container">
      <div class="service-item" v-for="(service, index) in services" :key="index">
        <img :src="service.image" alt="service" />
        <h3>{{ service.title }}</h3>
        <ul>
          <li v-for="(item, subIndex) in service.details" :key="subIndex">{{ item }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 模拟服务数据
const services = ref([
  {
    title: '按需喂食换水',
    image: require('@/assets/images/feeding.png'), // 这里的路径根据实际图片位置修改
    details: [
      '按宠主要求加粮换水',
      '喂罐头零食和营养品',
      '保证自动饮水机和喂食机正常工作'
    ]
  },
  {
    title: '外出遛狗',
    image: require('@/assets/images/walking.jpg'), // 这里的路径根据实际图片位置修改
    details: [
      '实时追踪遛狗轨迹',
      '按照宠主或狗狗偏好路线达到合理运动量'
    ]
  },
  {
    title: '观察狗状态',
    image: require('@/assets/images/checking.jpg'), // 这里的路径根据实际图片位置修改
    details: [
      '检查狗狗身体和精神状态',
      '观察饮食和排泄状况'
    ]
  },
  {
    title: '清理狗狗相关卫生',
    image: require('@/assets/images/cleaning.jpg'), // 这里的路径根据实际图片位置修改
    details: [
      '统一配备服务装备',
      '更换尿垫'
    ]
  },
  {
    title: '确认环境安全',
    image: require('@/assets/images/safety.jpg'), // 这里的路径根据实际图片位置修改
    details: [
      '观察室内是否有危险物品',
      '确认门窗关闭'
    ]
  }
]);
</script>

<style scoped>
.dog-walking-service {
  text-align: center;
  min-height: calc(100vh - 60px);
  font-family: 'Arial', sans-serif; /* 设置字体 */
  color: #333; /* 设置文本颜色 */
}

.service-container {
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-wrap: wrap;
  padding: 20px; /* 添加内边距 */
}

.service-item {
  width: 300px;
  margin: 20px;
  background-color: #f9f9f9; /* 设置背景颜色 */
  border: 1px solid #ddd; /* 添加边框 */
  border-radius: 10px; /* 圆角边框 */
  padding: 20px; /* 添加内边距 */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
  transition: transform 0.3s ease-in-out; /* 添加过渡效果 */
}

.service-item:hover {
  transform: translateY(-5px); /* 鼠标悬停时向上移动 */
}

.service-item img {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  margin-bottom: 10px; /* 添加底部外边距 */
}

h3 {
  color: #555; /* 设置标题颜色 */
  margin-bottom: 10px; /* 添加底部外边距 */
}

ul {
  list-style-type: none; /* 移除列表项的默认样式 */
  padding: 0; /* 移除内边距 */
}

li {
  margin-bottom: 5px; /* 添加列表项的底部外边距 */
}
</style>