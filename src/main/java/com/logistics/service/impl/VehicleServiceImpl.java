package com.logistics.service.impl;

import com.logistics.entity.Vehicle;
import com.logistics.repository.VehicleRepository;
import com.logistics.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 车辆服务实现类
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        // 检查车牌号是否已存在
        Optional<Vehicle> existingVehicle = vehicleRepository.findByPlateNumber(vehicle.getPlateNumber());
        if (existingVehicle.isPresent()) {
            throw new RuntimeException("车牌号已存在");
        }
        
        vehicle.setCreateTime(new Date());
        vehicle.setUpdateTime(new Date());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        // 检查车辆是否存在
        Vehicle existingVehicle = getVehicleById(vehicle.getId());
        
        // 如果修改了车牌号，检查新车牌号是否已存在
        if (!existingVehicle.getPlateNumber().equals(vehicle.getPlateNumber())) {
            Optional<Vehicle> vehicleWithSamePlate = vehicleRepository.findByPlateNumber(vehicle.getPlateNumber());
            if (vehicleWithSamePlate.isPresent()) {
                throw new RuntimeException("车牌号已存在");
            }
        }
        
        vehicle.setUpdateTime(new Date());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        // 检查车辆是否存在
        Vehicle existingVehicle = getVehicleById(id);
        
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (!vehicleOptional.isPresent()) {
            throw new RuntimeException("车辆不存在");
        }
        return vehicleOptional.get();
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleByPlateNumber(String plateNumber) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByPlateNumber(plateNumber);
        if (!vehicleOptional.isPresent()) {
            throw new RuntimeException("车辆不存在");
        }
        return vehicleOptional.get();
    }

    @Override
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return vehicleRepository.findByVehicleType(vehicleType);
    }

    @Override
    public List<Vehicle> getVehiclesByStatus(Integer status) {
        return vehicleRepository.findByStatus(status);
    }

    @Override
    public List<Vehicle> getVehiclesByDriverName(String driverName) {
        return vehicleRepository.findByDriverNameLike("%" + driverName + "%");
    }
} 