### redis使用实践
1. 基于redis的访问计数
1. 按照城市、用户维度存储用户的姓名、年龄等信息
    1. 使用hash结构
    2. key cityid_userid
    3. field name, age
    4. value 张三, 27
    5. `hmset 11_000000001 name "张三" age 27`
    6. `hget 11_000000001`
    1. 查询南京市下所有用户 执行 `keys 11_*`, 返回的所有key再截取userid
2. 存储某个用户所有的图书id
    1. 使用set结构
    2. key book_userid; 这里book_前缀标识是用户的图书信息，实际应用还需要添加公司业务部门等前缀，避免key冲突
    3. `sadd book_000000001 b0000001`
    4. 查看id为 000000001 的用户的图书 `SMEMBERS book_000000001`
    5. 查看id为 000000001 用户图书数 `scard book_000000001`
    6. 查看id为 000000001 和 id为 000000002 两个用户所拥有的不一样的图书 `sdiff book_000000001 book_000000002`
    7. 删除id为 000000001 的用户 id为 b0000001 的图书 `srem book_000000001 b0000001`
3. 用户书架上的图书列表
    1. 使用zset结构
    2. key bookshelf_userid
    3. 新增图书 `zadd bookshelf_000000001 b0000001 b0000002 b0000003 b0000004`
    4. 移除图书 b0000003 `zrem bookshelf_000000001 b0000003`
    5. 统计图书数 `zcard bookshelf_000000001`
1. 用户购买图书后邮寄队列
    1. 使用List结构
    2. key ordersend
    3. 某个客服收到用户购买请求新增订单 `lpush ordersend "{order and address json}"`
    4. 某个邮寄人员取出一条记录并根据其中地址信息开始执行派送 `lpop ordersend`
    5. 查看剩余待处理订单数 `llen ordersend`
1. baidu Redis发布订阅和应用场景
    1. 其一，订阅清除缓存topic，收到消息后立刻清除缓存
