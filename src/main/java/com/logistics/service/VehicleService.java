package com.logistics.service;

import com.logistics.entity.Vehicle;

import java.util.List;

/**
 * 车辆服务接口
 */
public interface VehicleService {
    
    /**
     * 添加车辆
     * @param vehicle 车辆信息
     * @return 车辆信息
     */
    Vehicle addVehicle(Vehicle vehicle);
    
    /**
     * 更新车辆
     * @param vehicle 车辆信息
     * @return 车辆信息
     */
    Vehicle updateVehicle(Vehicle vehicle);
    
    /**
     * 删除车辆
     * @param id 车辆ID
     */
    void deleteVehicle(Long id);
    
    /**
     * 根据ID查询车辆
     * @param id 车辆ID
     * @return 车辆信息
     */
    Vehicle getVehicleById(Long id);
    
    /**
     * 查询所有车辆
     * @return 车辆列表
     */
    List<Vehicle> getAllVehicles();
    
    /**
     * 根据车牌号查询车辆
     * @param plateNumber 车牌号
     * @return 车辆信息
     */
    Vehicle getVehicleByPlateNumber(String plateNumber);
    
    /**
     * 根据车型查询车辆
     * @param vehicleType 车型
     * @return 车辆列表
     */
    List<Vehicle> getVehiclesByType(String vehicleType);
    
    /**
     * 根据车辆状态查询车辆
     * @param status 车辆状态
     * @return 车辆列表
     */
    List<Vehicle> getVehiclesByStatus(Integer status);
    
    /**
     * 根据司机姓名模糊查询车辆
     * @param driverName 司机姓名
     * @return 车辆列表
     */
    List<Vehicle> getVehiclesByDriverName(String driverName);
} 