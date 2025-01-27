package com.qingge.springboot.service;
import com.qingge.springboot.entity.Booking;
import com.qingge.springboot.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // 删除预订数据的方法
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}