接口设计：

### URL
`/period/products`

### 方法
- GET

### 参数
- 无。

## 产品列表
> 返回(json)

```JavaScript
[{
“product_id”:<产品Id>, 
“title”:<产品名称>, 
“period”: <期限>, 
“interest_rate”:<利率>, 
“collect_rate”:<募集比例>, 
“available_amount”:<剩余金额>, 
“total_amount”:<总金额>, 
“collect_start_at”:<募集开始时间>, 
“interest_start_at”:<计息开始时间>, 
“product_end_at”:<产品结束时间>, 
“status”:<状态>
}…]
```
- 例如：
[
{“product_id”:1, “title”:“16030101”, “period”: 12, “interest_rate”:“0.15”, “collect_rate”:“0.75”, 
“available_amount”:6000, “total_amount”:100000, “collect_start_at”:“2016-04-15 12:00:00”, “interest_start_at”:“2016-04-15 12:00:00”, 
“product_end_at”:“2016-04-15 12:00:00”, “status”:1
} …
]


### URL
`/period/product/info`

### 方法
- GET

### 参数
- product_id, 产品id。

## 产品详情
> 返回(json)

```JavaScript
{
“product_id”:<产品Id>, 
“title”:<产品名称>, 
“period”: <期限>, 
“interest_rate”:<利率>, 
“collect_rate”:<募集比例>, 
“available_amount”:<剩余金额>, 
“total_amount”:<总金额>, 
“collect_start_at”:<募集开始时间>, 
“interest_start_at”:<计息开始时间>, 
“product_end_at”:<产品结束时间>, 
“status”:<状态>
}
```
- 例如：
{“product_id”:1, “title”:“16030101”, “period”: 12, “interest_rate”:“0.15”, “collect_rate”:“0.75”, 
“available_amount”:6000, “total_amount”:100000, “collect_start_at”:“2016-04-15 12:00:00”, “interest_start_at”:“2016-04-15 12:00:00”, 
“product_end_at”:“2016-04-15 12:00:00”, “status”:1
}


### URL
`/period/product/orders`

### 方法
- GET

### 参数
- product_id 产品id
- page_index 页序

## 购买记录
> 返回(json)

```JavaScript
{
“product_id”:<产品Id>, 
“user”:<用户名>, 
“amount”: <金额>, 
“invest_at”:<投资时间>, 
“page_count”:<总页数>
}
```
- 例如：
{“product_id”:1, “user”:“13916155765”, “amount”: 3000, “invest_at”:“2016-04-15 12:00:00”, “page_count”:20}


### URL
`/period/invest`

### 方法
- POST

### 参数
- product_id 产品id
- amount 金额
- account_id 用户id

## 购买
> 返回(json)

```JavaScript
{
“res_code”:<响应码>, 
“title”:<产品名称>, 
“messsage”: <出错原因>, 
“interest_start_at”:<开始计息时间>
}
```
- 例如：
{“res_code”:1, “title”:“16030101”, “messsage”: “余额不足”, “interest_start_at”:“2016-04-15 12:00:00”}

### URL
`/period/invest/info`

### 方法
- GET

### 参数
- account_id 用户id

## 个人投资情况
> 返回(json)

```JavaScript
{
“total_amount”:<总金额>, 
“list”:
     [
	{
	“product_id”:<产品Id>, 
	“title”:<产品名称>, 
	“period”: <期限>, 
	“interest_rate”:<利率>, 
	“invest_amount”:<投资金额>, 
	“estimate_interest”:<预计收益>, 
	“accumulative_interest”:<累计收益>, 
	“next_interest_at”:<下次结息时间>, 
	“interest_start_at”:<计息开始时间>, 
	“product_end_at”:<产品结束时间>
	}…
      ]

}
```
- 例如：
{
“total_amount”:30000,
list:[
	{“product_id”:1, “title”:“16030101”, “period”: 12, “interest_rate”:“0.15”, “invest_amount”:6000, 
	“interest_start_at”:“2016-04-15 12:00:00”, “product_end_at”:“2016-04-15 12:00:00”, “estimate_interest”:“120.6”, 	
	“accumulative_interest”:“12.6”, “next_interest_at”:“2016-04-15 12:00:00”} …
	]
}


### URL
`/period/withdraw`

### 方法
- POST

### 参数
- product_id 产品id
- account_id 用户id
- payment_password 支付密码

## 全部赎回
> 返回(json)

```JavaScript
{
“res_code”:<响应码>, 
“title”:<产品名称>, 
“messsage”: <失败原因>, 
“allow_withdraw_at”:<赎回时间>
}
```
- 例如：
{“res_code”:1, “title”:“16030101”, “messsage”: “你的资金处于锁定期无法赎回”, “allow_withdraw_at”:“2016-04-15 12:00:00”}




6、改造总资产查询：加上长虹计划投资额、昨日收益：加上昨日长虹收益、累计收益：加上长虹累计收益
