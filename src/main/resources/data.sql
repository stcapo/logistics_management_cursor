-- 清空现有数据
DELETE FROM vehicle WHERE id > 0;
DELETE FROM logistics_order WHERE id > 0;

-- 重置自增ID
ALTER TABLE vehicle ALTER COLUMN id RESTART WITH 1;
ALTER TABLE logistics_order ALTER COLUMN id RESTART WITH 1;

-- 插入10条车辆数据
INSERT INTO vehicle (plate_number, vehicle_type, load_capacity, status, driver_name, driver_phone, remark, create_time, update_time) VALUES
('京A12345', '东风重卡', 15.5, 1, '张三', '13800138001', '主要负责北京市内配送', NOW(), NOW()),
('京B54321', '解放J6', 20.0, 2, '李四', '13800138002', '长途运输车辆', NOW(), NOW()),
('沪C98765', '重汽豪沃', 25.0, 0, '王五', '13800138003', '正在进行年度维修', NOW(), NOW()),
('粤D56789', '江淮格尔发', 10.0, 1, '赵六', '13800138004', '负责广东省内物流配送', NOW(), NOW()),
('苏E13579', '奔驰Actros', 18.0, 2, '钱七', '13800138005', '国际物流运输', NOW(), NOW()),
('浙F24680', '斯堪尼亚R系', 22.0, 1, '孙八', '13800138006', '新购置车辆', NOW(), NOW()),
('鲁G11223', '福田欧曼', 17.5, 2, '周九', '13800138007', '山东区域配送', NOW(), NOW()),
('冀H33445', '红岩杰狮', 21.0, 1, '吴十', '13800138008', '常规物流运输', NOW(), NOW()),
('津J55667', '陕汽德龙', 16.0, 0, '郑十一', '13800138009', '进厂大修', NOW(), NOW()),
('渝K77889', '大运重卡', 19.5, 1, '刘十二', '13800138010', '西南区域配送', NOW(), NOW());

-- 插入10条订单数据
INSERT INTO logistics_order (order_no, customer_name, customer_phone, from_address, to_address, goods_name, weight, volume, freight, status, remark, create_time, update_time) VALUES
('ORD20230001', '北京科技有限公司', '010-12345678', '北京市海淀区中关村', '上海市浦东新区张江高科技园区', '电子设备', 5.0, 10.0, 5000.0, 0, '需要小心轻放', NOW(), NOW()),
('ORD20230002', '上海贸易有限公司', '021-87654321', '上海市黄浦区外滩', '广州市天河区珠江新城', '服装衣物', 3.0, 8.0, 3000.0, 1, '防潮处理', NOW(), NOW()),
('ORD20230003', '广州食品有限公司', '020-11223344', '广州市白云区人和镇', '深圳市南山区科技园', '生鲜食品', 4.5, 6.0, 4500.0, 2, '需冷链运输', NOW(), NOW()),
('ORD20230004', '深圳电子科技公司', '0755-55667788', '深圳市宝安区西乡街道', '成都市武侯区高新区', '芯片产品', 1.0, 2.0, 8000.0, 3, '高价值物品，需保险', NOW(), NOW()),
('ORD20230005', '成都机械制造公司', '028-99887766', '成都市青羊区荷花池', '重庆市渝北区龙溪街道', '机械零部件', 15.0, 12.0, 6000.0, 4, '已取消，客户原因', NOW(), NOW()),
('ORD20230006', '重庆汽车配件公司', '023-11335577', '重庆市江北区观音桥', '武汉市江汉区解放大道', '汽车配件', 8.0, 15.0, 4000.0, 0, '常规配送', NOW(), NOW()),
('ORD20230007', '武汉医药有限公司', '027-22446688', '武汉市汉阳区钟家村', '西安市雁塔区高新路', '医疗器械', 2.5, 5.0, 7500.0, 1, '医疗物资，优先配送', NOW(), NOW()),
('ORD20230008', '西安软件科技公司', '029-33557799', '西安市雁塔区高新四路', '杭州市西湖区西溪路', '服务器设备', 6.0, 9.0, 9000.0, 2, '贵重物品，专人押运', NOW(), NOW()),
('ORD20230009', '杭州网络科技公司', '0571-12344321', '杭州市滨江区长河路', '南京市鼓楼区中山北路', '办公设备', 3.5, 7.0, 3500.0, 3, '按时送达', NOW(), NOW()),
('ORD20230010', '南京教育科技公司', '025-98765432', '南京市玄武区玄武湖', '苏州市姑苏区金阊街道', '教学器材', 7.0, 14.0, 4800.0, 0, '学校用品，工作时间配送', NOW(), NOW()); 