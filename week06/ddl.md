```sql
-- auto-generated definition
create table product_order
(
    order_id       int auto_increment
        primary key,
    user_id        int                                 not null,
    sku_id         int                                 not null,
    count          int                                 not null,
    original_price decimal(10, 2)                      null comment '原始价格',
    actual_price   decimal(10, 2)                      not null comment '真实购买的价格(比如折扣后)',
    order_code     varchar(255)                        null comment '用户看到的订单号',
    status         varchar(255)                        not null comment '订单状态',
    weight         decimal(20, 4)                      null comment '重量, 单位(克)',
    create_time    timestamp default CURRENT_TIMESTAMP not null,
    update_time    timestamp default CURRENT_TIMESTAMP not null
);


```

```sql

-- auto-generated definition
create table sku
(
    sku_id      int auto_increment
        primary key,
    sku_name    varchar(255)                        not null comment '商品名称',
    sku_desc    text                                null comment '说明',
    create_time timestamp default CURRENT_TIMESTAMP null,
    update_time timestamp default CURRENT_TIMESTAMP null
)
    comment '商品';


```


```sql
-- auto-generated definition
create table user
(
    user_id     int auto_increment
        primary key,
    name        varchar(255)                        not null,
    password    varchar(255)                        not null,
    mobile      varchar(255)                        not null,
    level_id    int                                 null comment '客户级别',
    create_time timestamp default CURRENT_TIMESTAMP null,
    update_time timestamp default CURRENT_TIMESTAMP null
);


```