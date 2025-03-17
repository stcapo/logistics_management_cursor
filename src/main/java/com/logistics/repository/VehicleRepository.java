package com.logistics.repository;

import com.logistics.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 车辆数据访问接口
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
    /**
     * 根据车牌号查找车辆
     * @param plateNumber 车牌号
     * @return 车辆对象
     */
    Optional<Vehicle> findByPlateNumber(String plateNumber);
    
    /**
     * 根据车型查找车辆列表
     * @param vehicleType 车型
     * @return 车辆列表
     */
    List<Vehicle> findByVehicleType(String vehicleType);
    
    /**
     * 根据车辆状态查找车辆列表
     * @param status 车辆状态
     * @return 车辆列表
     */
    List<Vehicle> findByStatus(Integer status);
    
    /**
     * 根据司机姓名查找车辆列表
     * @param driverName 司机姓名
     * @return 车辆列表
     */
    List<Vehicle> findByDriverNameLike(String driverName);
} 