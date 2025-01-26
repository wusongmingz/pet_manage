<template>
  <div style="min-height: calc(100vh - 60px); margin: 10px 0">
    <div class="app-container">
      <header class="app-header">
        <img src="logo.png" alt="BOOMER" class="logo" />
      </header>
      <main class="app-main">
        <h2>北京、上海、深圳 上门伴宠服务价格</h2>
        <div class="service-list">
          <div v-for="service in services" :key="service.id" class="service-item">
            <h3 >{{ service.title }}</h3>
            <img src="http://localhost:9090/file/63119c526e6e4654aa7b24ac89f4b09f.jpg" alt="Service Image"  style="width: 150px;height: 150px;"/>
          
            <p>{{ service.description }}</p>
            <div v-if="service.prices.length > 0" class="price-list">
              <div v-for="(price, index) in service.prices" :key="index" class="price-item">
                <span>{{ price.condition }}</span>
                <span>￥{{ price.price }}</span>
              </div>
            </div>
            <button v-if="service.canBook">立即预订</button>
            <p v-if="service.note">{{ service.note }}</p>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const services = ref([
  {
    id: 1,
    title: '上门遛狗 标准服务：30分钟',
    description: '15斤以内 ￥79/次，15 - 40斤 ￥89/次，超40斤 ￥99/次',
    prices: [
      { condition: '15斤以内', price: 79 },
      { condition: '15 - 40斤', price: 89 },
      { condition: '超40斤', price: 99 }
    ],
    canBook: true,
    note: '每增加一只额外加15元'
  },
  {
    id: 2,
    title: '上门遛狗 服务时长：60分钟',
    description: '15斤以内 ￥109/次，15 - 40斤 ￥119/次，超40斤 ￥129/次',
    prices: [
      { condition: '15斤以内', price: 109 },
      { condition: '15 - 40斤', price: 119 },
      { condition: '超40斤', price: 129 }
    ],
    canBook: true,
    note: '每增加一只额外加15元'
  },
  {
    id: 3,
    title: '上门喂猫 标准服务：30分钟',
    description: '1只 ￥89/次，2只 ￥99/次',
    prices: [
      { condition: '1只', price: 89 },
      { condition: '2只', price: 99 }
    ],
    canBook: true,
    note: '每增加一只额外加10元'
  },
  {
    id: 4,
    title: '住家伴宠 加时伴宠：30分钟 夜间伴宠：21:00 - 8:00分钟',
    description: '加时 ￥30/次，夜间 ￥200/次',
    prices: [
      { condition: '加时', price: 30 },
      { condition: '夜间', price: 200 }
    ],
    canBook: true,
    note: '此项目“住时伴宠”服务需与标准服务一起选购'
  },
  {
    id: 5,
    title: '提前上门交接 交接时长：15 - 20分钟',
    description: '提前熟悉交接 ￥50/次',
    prices: [
      { condition: '提前熟悉交接', price: 50 }
    ],
    canBook: true,
    note: '此项不能单独选购，需与标准服务一起选购'
  }
]);
</script>

<style scoped>
.app-container {
  font-family: Arial, sans-serif;
}

.app-header {
  text-align: center;
  padding: 20px;
}

.logo {
  width: 200px;
}

.app-main {
  padding: 20px;
  text-align: center;
}

.service-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.service-item {
  width: 300px;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 15px;
  margin: 10px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.price-list {
  list-style-type: none;
  padding: 0;
}

.price-item {
  margin: 5px 0;
}

button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 8px 15px;
  border-radius: 3px;
  cursor: pointer;
  margin-top: 10px;
}
</style>