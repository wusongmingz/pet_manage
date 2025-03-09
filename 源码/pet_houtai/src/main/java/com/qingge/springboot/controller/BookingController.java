package com.qingge.springboot.controller;


import com.qingge.springboot.entity.Booking;
import com.qingge.springboot.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }
    // 查询所有预订数据接口
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // 删除预订数据接口
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        try {
            bookingService.deleteBooking(id);
            return new ResponseEntity<>("预订数据删除成功", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("预订数据删除失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // 通过用户名查询某一个用户的预订数据接口
    @GetMapping("/byUsername")
    public ResponseEntity<List<Booking>> getBookingsByUsername(@RequestParam String name) {
        List<Booking> bookings = bookingService.getBookingsByUsername(name);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @PutMapping("/complete/{id}")
    public ResponseEntity<String> completeBooking(@PathVariable Long id) {
        boolean isCompleted = bookingService.completeBooking(id);
        if (isCompleted) {
            return new ResponseEntity<>("订单完成，状态已更新", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("订单完成失败，可能订单 ID 不存在", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
