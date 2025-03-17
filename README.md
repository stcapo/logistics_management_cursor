# 物流管理调度系统

基于SpringBoot的物流管理调度系统，实现了用户管理、物流订单管理和车辆管理等基本功能。

## 技术栈

- SpringBoot 2.7.14
- Spring Data JPA
- H2数据库
- Lombok

## 功能特性

1. 用户管理：用户登录、添加、修改、删除、查询
2. 物流订单管理：订单添加、修改、删除、查询
3. 车辆管理：车辆添加、修改、删除、查询

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+

### 运行步骤

1. 克隆项目到本地
2. 进入项目目录，执行Maven命令构建项目：
   ```bash
   mvn clean package
   ```
3. 运行项目：
   ```bash
   java -jar target/logistics-management-0.0.1-SNAPSHOT.jar
   ```
4. 访问系统：
   - 系统地址：http://localhost:8080
   - H2数据库控制台：http://localhost:8080/h2-console
     - JDBC URL: jdbc:h2:mem:logisticsdb
     - 用户名: sa
     - 密码: 空

### 初始化管理员账号

访问以下地址初始化管理员账号：
```
GET http://localhost:8080/api/initAdmin
```

初始管理员账号信息：
- 用户名：admin
- 密码：123456

## API接口说明

### 用户相关接口

- 用户登录：`POST /api/login`
- 初始化管理员：`GET /api/initAdmin`
- 添加用户：`POST /api/user`
- 更新用户：`PUT /api/user`
- 删除用户：`DELETE /api/user/{id}`
- 根据ID查询用户：`GET /api/user/{id}`
- 查询所有用户：`GET /api/user`

### 物流订单相关接口

- 添加订单：`POST /api/order`
- 更新订单：`PUT /api/order`
- 删除订单：`DELETE /api/order/{id}`
- 根据ID查询订单：`GET /api/order/{id}`
- 查询所有订单：`GET /api/order`
- 根据订单编号查询订单：`GET /api/order/no/{orderNo}`
- 根据客户名称查询订单：`GET /api/order/customer?customerName=xxx`
- 根据客户电话查询订单：`GET /api/order/phone?customerPhone=xxx`
- 根据订单状态查询订单：`GET /api/order/status/{status}`
- 根据货物名称查询订单：`GET /api/order/goods?goodsName=xxx`

### 车辆相关接口

- 添加车辆：`POST /api/vehicle`
- 更新车辆：`PUT /api/vehicle`
- 删除车辆：`DELETE /api/vehicle/{id}`
- 根据ID查询车辆：`GET /api/vehicle/{id}`
- 查询所有车辆：`GET /api/vehicle`
- 根据车牌号查询车辆：`GET /api/vehicle/plate/{plateNumber}`
- 根据车型查询车辆：`GET /api/vehicle/type?vehicleType=xxx`
- 根据车辆状态查询车辆：`GET /api/vehicle/status/{status}`
- 根据司机姓名查询车辆：`GET /api/vehicle/driver?driverName=xxx`

## 数据字典

### 用户状态

- 0：禁用
- 1：启用

### 订单状态

- 0：待分配
- 1：已分配
- 2：运输中
- 3：已完成
- 4：已取消

### 车辆状态

- 0：维修中
- 1：闲置中
- 2：运输中 