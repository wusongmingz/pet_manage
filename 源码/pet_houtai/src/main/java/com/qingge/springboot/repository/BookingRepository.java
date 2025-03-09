package com.qingge.springboot.repository;

import com.qingge.springboot.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // 根据用户名查询预订记录
    List<Booking> findByname(String name);
    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.status = :status WHERE b.id = :id")
    int updateBookingStatus(Long id, String status);
}
