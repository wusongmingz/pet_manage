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
            <h3>{{ service.title }}</h3>
            <img :src="service.image" alt="Service Image" style="width: 150px;height: 150px;"/>
            <video :src="service.video" controls style="width: 150px;height: 150px; margin-top: 10px;"></video>
            <p>{{ service.description }}</p>
            <div v-if="service.prices.length > 0" class="price-list">
              <div v-for="(price, index) in service.prices" :key="index" class="price-item">
                <span>{{ price.condition }}</span>
                <span>￥{{ price.price }}</span>
              </div>
            </div>
            <button v-if="service.canBook" @click="openBookingModal(service)">立即预订</button>
            <p v-if="service.note">{{ service.note }}</p>
            <div v-if="service.details" class="service-details">
              <h4>服务详情</h4>
              <p>{{ service.details }}</p>
            </div>
          </div>
        </div>

        <!-- 预约模态框 -->
        <div v-if="bookingModalVisible" class="booking-modal">
          <div class="booking-modal-content">
            <span class="close" @click="closeBookingModal">&times;</span>
            <h2>预约服务</h2>
            <form @submit.prevent="submitBooking">
              <label for="service-type">服务类型:</label>
              <input type="text" :value="selectedService.title" readonly style="margin-bottom: 10px;">
              <br>
              <label for="name">姓名:</label>
              <input type="text" v-model="user.username" placeholder="请输入名字" style="margin-bottom: 10px;">
              <br>
              <label for="time-range">时间范围:</label>
              <input type="text" v-model="booking.timeRange" placeholder="例如：2025-01-30 10:00 - 12:00" style="margin-bottom: 10px;">
              <br>
              <label for="location">地点:</label>
              <input type="text" v-model="booking.location" placeholder="请输入地址" style=" margin-bottom: 10px;">
              <br>
              <label for="pet-info">宠物相关情况:</label>
              <textarea v-model="booking.petInfo" placeholder="请描述宠物的品种、年龄、体重等信息" style="margin-bottom: 10px;"></textarea>
              <br>
              <button type="submit" style="margin-right: 5px;" >提交预约</button>
              <button class="exit-button" @click="closeBookingModal">退出</button>
            </form>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      services: [
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
          note: '每增加一只额外加15元',
          details: '我们的专业遛狗人员会准时上门，带狗狗到附近合适的地方散步，确保狗狗的安全和健康。遛狗过程中会清理狗狗的排泄物。',
          image: 'http://localhost:9090/file/dog_walk_30min.jpg',
          video: 'http://localhost:9090/file/dog_walk_30min_video.mp4'
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
          note: '每增加一只额外加15元',
          details: '提供更长时间的遛狗服务，让狗狗有更充足的时间活动和玩耍。遛狗过程中会密切关注狗狗的状态，确保其舒适和安全。',
          image: 'http://localhost:9090/file/dog_walk_60min.jpg',
          video: 'http://localhost:9090/file/dog_walk_60min_video.mp4'
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
          note: '每增加一只额外加10元',
          details: '专业人员会按时上门，为猫咪提供新鲜的食物和水，清理猫砂盆，确保猫咪生活环境的清洁和舒适。',
          image: 'http://localhost:9090/file/cat_feed_30min.jpg',
          video: 'http://localhost:9090/file/cat_feed_30min_video.mp4'
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
          note: '此项目“住时伴宠”服务需与标准服务一起选购',
          details: '提供住家陪伴宠物服务，可选择加时或夜间陪伴。陪伴过程中会照顾宠物的日常需求，给予宠物足够的关爱和陪伴。',
          image: 'http://localhost:9090/file/home_pet_company.jpg',
          video: 'http://localhost:9090/file/home_pet_company_video.mp4'
        },
        {
          id: 5,
          title: '提前上门交接 交接时长：15 - 20分钟',
          description: '提前熟悉交接 ￥50/次',
          prices: [
            { condition: '提前熟悉交接', price: 50 }
          ],
          canBook: true,
          note: '此项不能单独选购，需与标准服务一起选购',
          details: '在正式服务前，工作人员提前上门与您交接，了解宠物的生活习惯和特殊需求，确保服务顺利进行。',
          image: 'http://localhost:9090/file/advance_handover.jpg',
          video: 'http://localhost:9090/file/advance_handover_video.mp4'
        }
      ],
      bookingModalVisible: false,
      selectedService: null,
      booking: {
        timeRange: '',
        location: '',
        petInfo: '',
        name: ''
      },
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
    };
  },
  methods: {
    submitBooking() {
      const bookingData = {
        "name": this.user.username,
        "serviceTitle": this.selectedService.title,
        "timeRange": this.booking.timeRange,
        "location": this.booking.location,
        "petInfo": this.booking.petInfo
      };
      console.log('request 对象:', this.request); // 添加调试信息

      this.request.post("/bookings", bookingData).then(res => {
        if (res.data.code === '200') {
          alert('预约成功');
        } else {
          this.$message.error(res.data.msg);
        }
      }).catch(error => {
        console.error('请求出错:', error);
      });
      this.closeBookingModal();
    },
    openBookingModal(service) {
      this.selectedService = service;
      this.bookingModalVisible = true;
    },
    closeBookingModal() {
      this.bookingModalVisible = false;
      this.selectedService = null;
      this.booking = {
        timeRange: '',
        location: '',
        petInfo: ''
      };
    }
  }
};
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

.service-details {
  margin-top: 10px;
  text-align: left;
}

.booking-modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.booking-modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  width: 400px;
}

.booking-modal-content h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.booking-modal-content label {
  display: block;
  margin-bottom: 5px;
  color: #666;
}

.booking-modal-content input[type="text"],
.booking-modal-content textarea {
  width: 100%;
  padding: 8px;
  margin-bottom: 15px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.booking-modal-content button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 3px;
  cursor: pointer;
  margin-right: 10px;
}

.booking-modal-content button.exit-button {
  background-color: #ccc;
  color: #333;
}

.booking-modal-content button:hover {
  opacity: 0.8;
}

.booking-modal-content .close {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 20px;
  cursor: pointer;
  color: #666;
}

.booking-modal-content .close:hover {
  color: #333;
}
</style>