package com.logistics.controller;

import com.logistics.dto.ResultDTO;
import com.logistics.entity.Vehicle;
import com.logistics.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车辆控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * 添加车辆
     * @param vehicle 车辆信息
     * @return 添加结果
     */
    @PostMapping
    public ResultDTO<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle savedVehicle = vehicleService.addVehicle(vehicle);
            return ResultDTO.success("添加车辆成功", savedVehicle);
        } catch (Exception e) {
            log.error("添加车辆失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 更新车辆
     * @param vehicle 车辆信息
     * @return 更新结果
     */
    @PutMapping
    public ResultDTO<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);
            return ResultDTO.success("更新车辆成功", updatedVehicle);
        } catch (Exception e) {
            log.error("更新车辆失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 删除车辆
     * @param id 车辆ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResultDTO<Void> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicle(id);
            return ResultDTO.success("删除车辆成功", null);
        } catch (Exception e) {
            log.error("删除车辆失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 根据ID查询车辆
     * @param id 车辆ID
     * @return 车辆信息
     */
    @GetMapping("/{id}")
    public ResultDTO<Vehicle> getVehicleById(@PathVariable Long id) {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            return ResultDTO.success(vehicle);
        } catch (Exception e) {
            log.error("查询车辆失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }

    /**
     * 查询所有车辆
     * @return 车辆列表
     */
    @GetMapping
    public ResultDTO<List<Vehicle>> getAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleService.getAllVehicles();
            return ResultDTO.success(vehicles);
        } catch (Exception e) {
            log.error("查询车辆列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 根据车牌号查询车辆
     * @param plateNumber 车牌号
     * @return 车辆信息
     */
    @GetMapping("/plate/{plateNumber}")
    public ResultDTO<Vehicle> getVehicleByPlateNumber(@PathVariable String plateNumber) {
        try {
            Vehicle vehicle = vehicleService.getVehicleByPlateNumber(plateNumber);
            return ResultDTO.success(vehicle);
        } catch (Exception e) {
            log.error("查询车辆失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 根据车型查询车辆
     * @param vehicleType 车型
     * @return 车辆列表
     */
    @GetMapping("/type")
    public ResultDTO<List<Vehicle>> getVehiclesByType(@RequestParam String vehicleType) {
        try {
            List<Vehicle> vehicles = vehicleService.getVehiclesByType(vehicleType);
            return ResultDTO.success(vehicles);
        } catch (Exception e) {
            log.error("查询车辆列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 根据车辆状态查询车辆
     * @param status 车辆状态
     * @return 车辆列表
     */
    @GetMapping("/status/{status}")
    public ResultDTO<List<Vehicle>> getVehiclesByStatus(@PathVariable Integer status) {
        try {
            List<Vehicle> vehicles = vehicleService.getVehiclesByStatus(status);
            return ResultDTO.success(vehicles);
        } catch (Exception e) {
            log.error("查询车辆列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
    
    /**
     * 根据司机姓名查询车辆
     * @param driverName 司机姓名
     * @return 车辆列表
     */
    @GetMapping("/driver")
    public ResultDTO<List<Vehicle>> getVehiclesByDriverName(@RequestParam String driverName) {
        try {
            List<Vehicle> vehicles = vehicleService.getVehiclesByDriverName(driverName);
            return ResultDTO.success(vehicles);
        } catch (Exception e) {
            log.error("查询车辆列表失败", e);
            return ResultDTO.error(e.getMessage());
        }
    }
} 