// 全局变量
let currentUser = null;

// 页面加载完成后执行
document.addEventListener('DOMContentLoaded', function() {
    // 检查是否已登录
    checkLogin();
    
    // 绑定登录事件
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', handleLogin);
    }
    
    // 绑定退出登录事件
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', handleLogout);
    }
    
    // 加载数据
    loadPageData();
});

// 检查是否已登录
function checkLogin() {
    const user = localStorage.getItem('currentUser');
    if (user) {
        currentUser = JSON.parse(user);
        if (window.location.pathname === '/' || window.location.pathname === '/login.html') {
            window.location.href = '/admin.html';
        }
        updateUserInfo();
    } else {
        // 临时用于测试，添加一个模拟用户
        if (window.location.pathname !== '/' && window.location.pathname !== '/login.html') {
            // 设置一个临时用户用于测试（如果要使用真实登录，请删除这段代码）
            const tempUser = {
                username: 'admin',
                realName: '管理员',
                id: 1
            };
            localStorage.setItem('currentUser', JSON.stringify(tempUser));
            currentUser = tempUser;
            updateUserInfo();
            // 取消重定向到登录页
            //window.location.href = '/login.html';
        }
    }
}

// 处理登录
function handleLogin(e) {
    e.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    if (!username || !password) {
        showMessage('请输入用户名和密码');
        return;
    }
    
    // 发送登录请求
    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            // 登录成功
            localStorage.setItem('currentUser', JSON.stringify(data.data));
            window.location.href = '/admin.html';
        } else {
            // 登录失败
            showMessage(data.message || '登录失败');
        }
    })
    .catch(error => {
        console.error('登录错误:', error);
        showMessage('登录时发生错误');
    });
}

// 处理退出登录
function handleLogout(e) {
    e.preventDefault();
    
    localStorage.removeItem('currentUser');
    window.location.href = '/login.html';
}

// 更新用户信息显示
function updateUserInfo() {
    const userNameElement = document.getElementById('userName');
    if (userNameElement && currentUser) {
        userNameElement.textContent = currentUser.realName || currentUser.username;
    }
}

// 显示消息
function showMessage(message, type = 'error') {
    const messageEl = document.getElementById('message');
    if (messageEl) {
        messageEl.textContent = message;
        messageEl.className = 'message ' + type;
        messageEl.style.display = 'block';
        
        // 3秒后自动隐藏
        setTimeout(() => {
            messageEl.style.display = 'none';
        }, 3000);
    } else {
        alert(message);
    }
}

// 加载页面数据
function loadPageData() {
    const page = getCurrentPage();
    
    switch (page) {
        case 'user':
            loadUsers();
            break;
        case 'order':
            loadOrders();
            break;
        case 'vehicle':
            loadVehicles();
            break;
        default:
            // 默认加载数据统计
            loadDashboard();
    }
}

// 获取当前页面标识
function getCurrentPage() {
    const path = window.location.pathname;
    
    if (path.includes('user.html')) {
        return 'user';
    } else if (path.includes('order.html')) {
        return 'order';
    } else if (path.includes('vehicle.html')) {
        return 'vehicle';
    } else {
        return 'dashboard';
    }
}

// 加载用户列表
function loadUsers() {
    fetch('/api/user', {
        method: 'GET',
        headers: getAuthHeaders()
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            renderUserTable(data.data);
        } else {
            showMessage(data.message || '获取用户列表失败');
        }
    })
    .catch(error => {
        console.error('获取用户列表错误:', error);
        showMessage('获取用户列表时发生错误');
    });
}

// 渲染用户表格
function renderUserTable(users) {
    const tableBody = document.getElementById('userTableBody');
    if (!tableBody) return;
    
    tableBody.innerHTML = '';
    
    if (users && users.length > 0) {
        users.forEach(user => {
            const row = document.createElement('tr');
            
            row.innerHTML = `
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.realName || '-'}</td>
                <td>${user.phone || '-'}</td>
                <td>${user.email || '-'}</td>
                <td>${user.status === 1 ? '启用' : '禁用'}</td>
                <td>
                    <a href="javascript:void(0)" class="action-btn edit-btn" data-id="${user.id}">编辑</a>
                    <a href="javascript:void(0)" class="action-btn delete-btn" data-id="${user.id}">删除</a>
                </td>
            `;
            
            tableBody.appendChild(row);
        });
        
        // 绑定编辑和删除事件
        bindUserActions();
    } else {
        tableBody.innerHTML = '<tr><td colspan="7" style="text-align:center">暂无用户数据</td></tr>';
    }
}

// 绑定用户操作事件
function bindUserActions() {
    // 绑定编辑按钮事件
    document.querySelectorAll('.edit-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const userId = this.getAttribute('data-id');
            openUserEditModal(userId);
        });
    });
    
    // 绑定删除按钮事件
    document.querySelectorAll('.delete-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const userId = this.getAttribute('data-id');
            deleteUser(userId);
        });
    });
}

// 加载订单列表
function loadOrders() {
    fetch('/api/order', {
        method: 'GET',
        headers: getAuthHeaders()
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            renderOrderTable(data.data);
        } else {
            showMessage(data.message || '获取订单列表失败');
        }
    })
    .catch(error => {
        console.error('获取订单列表错误:', error);
        showMessage('获取订单列表时发生错误');
    });
}

// 渲染订单表格
function renderOrderTable(orders) {
    const tableBody = document.getElementById('orderTableBody');
    if (!tableBody) return;
    
    tableBody.innerHTML = '';
    
    if (orders && orders.length > 0) {
        orders.forEach(order => {
            const row = document.createElement('tr');
            
            // 格式化订单状态
            let statusText = '';
            switch (order.status) {
                case 0: statusText = '待分配'; break;
                case 1: statusText = '已分配'; break;
                case 2: statusText = '运输中'; break;
                case 3: statusText = '已完成'; break;
                case 4: statusText = '已取消'; break;
                default: statusText = '未知';
            }
            
            row.innerHTML = `
                <td>${order.orderNo}</td>
                <td>${order.customerName}</td>
                <td>${order.customerPhone}</td>
                <td>${order.fromAddress}</td>
                <td>${order.toAddress}</td>
                <td>${order.goodsName}</td>
                <td>${statusText}</td>
                <td>
                    <a href="javascript:void(0)" class="action-btn edit-btn" data-id="${order.id}">编辑</a>
                    <a href="javascript:void(0)" class="action-btn delete-btn" data-id="${order.id}">删除</a>
                </td>
            `;
            
            tableBody.appendChild(row);
        });
        
        // 绑定编辑和删除事件
        bindOrderActions();
    } else {
        tableBody.innerHTML = '<tr><td colspan="8" style="text-align:center">暂无订单数据</td></tr>';
    }
}

// 绑定订单操作事件
function bindOrderActions() {
    // 绑定编辑按钮事件
    document.querySelectorAll('.edit-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const orderId = this.getAttribute('data-id');
            openOrderEditModal(orderId);
        });
    });
    
    // 绑定删除按钮事件
    document.querySelectorAll('.delete-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const orderId = this.getAttribute('data-id');
            deleteOrder(orderId);
        });
    });
}

// 加载车辆列表
function loadVehicles() {
    fetch('/api/vehicle', {
        method: 'GET',
        headers: getAuthHeaders()
    })
    .then(response => response.json())
    .then(data => {
        if (data.code === 200) {
            renderVehicleTable(data.data);
        } else {
            showMessage(data.message || '获取车辆列表失败');
        }
    })
    .catch(error => {
        console.error('获取车辆列表错误:', error);
        showMessage('获取车辆列表时发生错误');
    });
}

// 渲染车辆表格
function renderVehicleTable(vehicles) {
    const tableBody = document.getElementById('vehicleTableBody');
    if (!tableBody) return;
    
    tableBody.innerHTML = '';
    
    if (vehicles && vehicles.length > 0) {
        vehicles.forEach(vehicle => {
            const row = document.createElement('tr');
            
            // 格式化车辆状态
            let statusText = '';
            switch (vehicle.status) {
                case 0: statusText = '维修中'; break;
                case 1: statusText = '闲置中'; break;
                case 2: statusText = '运输中'; break;
                default: statusText = '未知';
            }
            
            // 处理loadCapacity可能是BigDecimal的情况
            let loadCapacity = vehicle.loadCapacity;
            if (loadCapacity && typeof loadCapacity === 'object' && loadCapacity.hasOwnProperty('value')) {
                loadCapacity = loadCapacity.value;
            }
            
            row.innerHTML = `
                <td>${vehicle.plateNumber}</td>
                <td>${vehicle.vehicleType || '-'}</td>
                <td>${loadCapacity || '-'}</td>
                <td>${statusText}</td>
                <td>${vehicle.driverName || '-'}</td>
                <td>${vehicle.driverPhone || '-'}</td>
                <td>
                    <a href="javascript:void(0)" class="action-btn edit-btn" data-id="${vehicle.id}">编辑</a>
                    <a href="javascript:void(0)" class="action-btn delete-btn" data-id="${vehicle.id}">删除</a>
                </td>
            `;
            
            tableBody.appendChild(row);
        });
        
        // 绑定编辑和删除事件
        bindVehicleActions();
    } else {
        tableBody.innerHTML = '<tr><td colspan="7" style="text-align:center">暂无车辆数据</td></tr>';
    }
}

// 绑定车辆操作事件
function bindVehicleActions() {
    // 绑定编辑按钮事件
    document.querySelectorAll('.edit-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const vehicleId = this.getAttribute('data-id');
            openVehicleEditModal(vehicleId);
        });
    });
    
    // 绑定删除按钮事件
    document.querySelectorAll('.delete-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const vehicleId = this.getAttribute('data-id');
            deleteVehicle(vehicleId);
        });
    });
}

// 获取认证请求头
function getAuthHeaders() {
    return {
        'Content-Type': 'application/json'
    };
}

// 其他功能实现（后续可添加）
// ...

// 加载仪表盘数据
function loadDashboard() {
    // 可在这里添加仪表盘数据加载逻辑
    console.log('加载仪表盘数据');
    
    // 加载用户数量
    fetch('/api/user', { headers: getAuthHeaders() })
        .then(response => response.json())
        .then(data => {
            const userCountElement = document.getElementById('userCount');
            if (userCountElement) {
                if (data.code === 200) {
                    userCountElement.textContent = data.data ? data.data.length : 0;
                } else {
                    userCountElement.textContent = '获取失败';
                }
            }
        })
        .catch(() => {
            const userCountElement = document.getElementById('userCount');
            if (userCountElement) {
                userCountElement.textContent = '获取失败';
            }
        });
    
    // 加载订单数量
    fetch('/api/order', { headers: getAuthHeaders() })
        .then(response => response.json())
        .then(data => {
            const orderCountElement = document.getElementById('orderCount');
            if (orderCountElement) {
                if (data.code === 200) {
                    orderCountElement.textContent = data.data ? data.data.length : 0;
                } else {
                    orderCountElement.textContent = '获取失败';
                }
            }
        })
        .catch(() => {
            const orderCountElement = document.getElementById('orderCount');
            if (orderCountElement) {
                orderCountElement.textContent = '获取失败';
            }
        });
    
    // 加载车辆数量
    fetch('/api/vehicle', { headers: getAuthHeaders() })
        .then(response => response.json())
        .then(data => {
            const vehicleCountElement = document.getElementById('vehicleCount');
            if (vehicleCountElement) {
                if (data.code === 200) {
                    vehicleCountElement.textContent = data.data ? data.data.length : 0;
                } else {
                    vehicleCountElement.textContent = '获取失败';
                }
            }
        })
        .catch(() => {
            const vehicleCountElement = document.getElementById('vehicleCount');
            if (vehicleCountElement) {
                vehicleCountElement.textContent = '获取失败';
            }
        });
}

// 删除用户
function deleteUser(userId) {
    if (confirm('确定要删除该用户吗？')) {
        fetch(`/api/user/${userId}`, {
            method: 'DELETE',
            headers: getAuthHeaders()
        })
        .then(response => response.json())
        .then(data => {
            if (data.code === 200) {
                showMessage('删除用户成功', 'success');
                loadUsers(); // 重新加载用户列表
            } else {
                showMessage(data.message || '删除用户失败');
            }
        })
        .catch(error => {
            console.error('删除用户错误:', error);
            showMessage('删除用户时发生错误');
        });
    }
}

// 删除订单
function deleteOrder(orderId) {
    if (confirm('确定要删除该订单吗？')) {
        fetch(`/api/order/${orderId}`, {
            method: 'DELETE',
            headers: getAuthHeaders()
        })
        .then(response => response.json())
        .then(data => {
            if (data.code === 200) {
                showMessage('删除订单成功', 'success');
                loadOrders(); // 重新加载订单列表
            } else {
                showMessage(data.message || '删除订单失败');
            }
        })
        .catch(error => {
            console.error('删除订单错误:', error);
            showMessage('删除订单时发生错误');
        });
    }
}

// 删除车辆
function deleteVehicle(vehicleId) {
    if (confirm('确定要删除该车辆吗？')) {
        fetch(`/api/vehicle/${vehicleId}`, {
            method: 'DELETE',
            headers: getAuthHeaders()
        })
        .then(response => response.json())
        .then(data => {
            if (data.code === 200) {
                showMessage('删除车辆成功', 'success');
                loadVehicles(); // 重新加载车辆列表
            } else {
                showMessage(data.message || '删除车辆失败');
            }
        })
        .catch(error => {
            console.error('删除车辆错误:', error);
            showMessage('删除车辆时发生错误');
        });
    }
} 