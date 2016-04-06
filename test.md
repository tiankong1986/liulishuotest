# 医生版4.1

# 验证码API

## 获取图片验证码

> 返回

```JavaScript
{
    success: true ,
    useCaptcha: < 是否启用Captcha > ,
    captchaKey: < Captcha的Key > ,
    captchaImage: < Captcha图片的Base64编码数据 >
}
```

如果useCaptcha为True，则APP需要显示图片验证码输入。

captchaKey是后台随机数生成的16位字符串，captchaCode是Key对应的验证码（不返回给APP）。captchaImage是对应的图片，Base64编码后的字符串。

后台将captchaCode保存在Redis中，Key为`captcha:<captchatKey>`，值为code（不是图片内容），30分钟后过期。

后台应将调用IP加入smsCode的调用限制里，防止有人不断获取验证码。调用限制可适当放大一些。

### URL
`/api/captcha`

### 方法
- POST


## 注册


## 注册用户
### URL
`/api/register`

###  方法
- POST

### 参数
- phone: 电话
- password: 密码
- code: 手机验证码（可选）
- captchaKey: 图形验证码（可选）
- captchaCode: 图形验证码（可选）
- invitor: 邀请者的杏仁号（可选）

情况1：提供了手机验证码。则使用验证码验证并注册。
情况2：没有提供手机验证码，但提供图形验证码。则验证图形验证码，验证后：若号码已注册则返回错误；否则，若该号码没有生成过验证码，并且未过期，则短信发送手机验证码。
情况3：没有提供手机验证码，也没有提供图形验证码。直接返回错误。

### HTTP头
- XRDid: 唯一DeviceId

注册之后服务端也会返回一个Token，处理方法和普通登录是一样的。

## 获取手机验证码

### URL
`/api/register/code`

###  方法
- POST

### 参数
- phone: 电话
- captchaKey: 图形验证码（可选）
- captchaCode: 图形验证码（可选）


# 八卦API

## 获取信息流
> 去掉地域的过滤

## 数据
> 八卦主题。如果isMine是true，则八卦主题是该医生自己发布的，右上角菜单有删除按钮。

```JavaScript
{
    id: < 八卦主题ID >
    content: < 八卦内容 > ,
    images: < 八卦图片的json(格式就是新建时的格式)数组,可选 >, **
    isHot: < 是否火爆 > ,
    isTop: < 是否置顶 > ,
    isMine: < 是否我自己发的 > ,
    isFriend: < 是否可能认识 > , 
    province: < 省 > ,（删除）后台先保留
    up: < 置顶系数>,
    commentCount: < 评论数 > ,
    likeCount: < 点赞数 >, **
    isLike: < 是否点赞 >, **
    url: < 八卦的URL >,
    created: < 发表时间 >
}
```

> 八卦评论。如果isMine是true，则八卦评论是该医生自己发布的，点击之后有删除按钮。

```JavaScript
{
    id: < 八卦评论ID >,
    content: < 八卦评论内容 > ,
    doctorCode: < 医生秘密代码，0为楼主 > ,
    isMine: < 是否我自己发的 > ,
    replyTo: < 评论对象医生的代码 > ,
    likeCount: < 点赞数 >, **
    isLike: < 是否点赞 >, **
    created: < 评论时间 >
}
```


> 八卦通知

```JavaScript
{
    id: < 八卦主题ID >
    content: < 八卦内容 > ,
    images: < json字符串 可选 >, **
    isHot: < 是否火爆 > ,
    isMine: < 是否我自己发的 > ,
    isFriend: < 是否可能认识 > , 
    province: < 省 >（删除）后台先保留 , 
    url: < 八卦的URL >
    commentCount: < 评论数 > ,
    unreadComments: < 未读评论数 > ,
    unreadParticipationCount: < 参与未读评论数 >, **
    unreadReplyComments: < 评论回复数 >, **
    unreadLikes: < 点赞数 >, **
    unreadCommentLikes: < 评论点赞数 >, **
    updated: < 八卦评论时间 >
    created: < 八卦发表时间 > ,
}
```


> 数据库修改

```
//八卦点赞统计
CREATE TABLE IF NOT EXISTS `SecretLikeDeleted` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctorId` int(11) NOT NULL COMMENT '医生ID。',
  `topicId` int(11) NOT NULL COMMENT '八卦主题ID', 
  `commentId` int(11) NOT NULL DEFAULT 0 COMMENT '八卦评论ID', 
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii ROW_FORMAT=DYNAMIC COMMENT='八卦评论。';

//八卦点赞统计
CREATE TABLE IF NOT EXISTS `SecretLike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctorId` int(11) NOT NULL COMMENT '医生ID。',
  `topicId` int(11) NOT NULL COMMENT '八卦主题ID', 
  `commentId` int(11) NOT NULL DEFAULT 0 COMMENT '八卦评论ID', 
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii ROW_FORMAT=DYNAMIC COMMENT='八卦评论。';

ALTER TABLE `SecretTopic` ADD `images` varchar(5000) NOT NULL DEFAULT '' COMMENT 'topic图片' AFTER `content`;

ALTER TABLE `SecretTopicDeleted` ADD `images` varchar(5000) NOT NULL DEFAULT '' COMMENT 'topic图片' AFTER `content`;

ALTER TABLE `SecretParticipation` ADD COLUMN `unreadReplyComments` int(11) NOT NULL DEFAULT '0' COMMENT '该医生的评论回复未读数。' AFTER `unreadComments`;

ALTER TABLE `SecretParticipation` ADD COLUMN `unreadLikes` int(11) NOT NULL DEFAULT '0' COMMENT '该医生发表的文章的未读点赞数。' AFTER `unreadComments`;

ALTER TABLE `SecretParticipation` ADD COLUMN `unreadCommentLikes` int(11) NOT NULL DEFAULT '0' COMMENT '该医生评论的未读点赞数' AFTER `unreadComments`;

ALTER TABLE `SecretParticipation` ADD COLUMN `unreadParticipationCount` int(11) NOT NULL DEFAULT '0' COMMENT '该医生参与文章未读回复数' AFTER `unreadComments`;

ALTER TABLE `SecretTopic` ADD `likeCount` INT(11)  NOT NULL  DEFAULT '0' COMMENT '点赞数' AFTER `commentCount`;

ALTER TABLE `SecretTopicDeleted` ADD `likeCount` INT(11)  NOT NULL  DEFAULT '0' COMMENT '点赞数' AFTER `commentCount`;

ALTER TABLE `SecretComment` ADD `likeCount` INT(11)  NOT NULL  DEFAULT '0'  COMMENT '点赞数'  AFTER `reportCount`;

ALTER TABLE `SecretCommentDeleted` ADD `likeCount` INT(11)  NOT NULL  DEFAULT '0'  COMMENT '点赞数'  AFTER `reportCount`;

```

## 忽略医生所有未读消息
> 返回

```JavaScript
{
    success: true
}
```

### URL
`/api/secret/notices/ignore`

### 方法
- POST


## 获取评论流
> 返回

```JavaScript
{
    success: true,
    lastId: < 最后一个Id，作为下次调用的maxId > ,
    secretComments: [ < 八卦评论数组 > ],
    godComments: [ < 神回复评论数组 > ],（可选） **
    likeCount: < 主题总赞数 >,（可选）**
    commentCount: < 主题评论数 > (可选) **
}
```

- 神回复的要求必须有大于等于2个赞，点赞数从大到小取前三个
- 只有当maxId为空的时候才会返回神回复，加载更多时不返回
- 只有当maxId为空的时候返回主题对应的赞数和回复数

### URL
`/api/secret/<topicId>/comments`

### 方法
- GET

### 参数
- maxId: 上次请求最后的id。可选，如果设置，返回大于该ID的记录。

## 发布主题
> 返回

```JavaScript
{
    success: true,
    secretTopic: < 八卦主题 > 
}
```

### URL
`/api/secret/new`

### 方法
- POST (form-data)

### 参数
- content: 八卦内容（不超过200字）。
- images:  八卦图片url的json字符串。
- json格式：
```
[
  {
    "picUrl":<图片地址>,
    "size":<图片大小>,
    "w":<图片宽度>,
    "h":<图片高度>,
    "original":< 是否原图,ture|false >
  }
...]
```
> images最多八张

## 赞八卦
### URL
`/api/secret/<topicId>/like`

### 方法
- POST

## 赞评论
### URL
`/api/secret/<topicId>/comment/<commentId>/like`

### 方法
- POST


## 群聊邀请

> 返回

- 添加群成员时，对医生认证状态进行check

```JavaScript
{
    success: true
    doctorIds: < 没有认证通过的医生 ** 可选字段 >
}
```
### URL

`/api/groupchat/<groupId>/add`(老的)

### 方法

- POST
- 
### 参数

doctorIds: 邀请的医生id数组


## 群聊邀请链接

`/api/groupchat/<groupId>/invite`

### 数据结构调整

``` JavaScript
ALTER TABLE `GroupChat` ADD COLUMN `groupMark` varchar(10) NOT NULL DEFAULT '' COMMENT '群标记' AFTER `thumbnail`;
```

### 返回

```
{
    success: true,
    url: < 群聊邀请的URL >
}
```
### 实现

> URL

`/page/groupchat/<groupMark>/<doctorSerial>/invite`

> 后台创建邀请URL时查询groupMark，如果为空生成一个随机字符

## 下拉加载消息

- 初始通过websocket返回
- App进行query

### 查询(query)

- 患者消息，科室消息，好友消息，群聊消息，小杏消息
- Query的stanza(老的已存在): (message, departMessage, friendMessage, groupChatMessage, support)
- params: (patientId, departId, friendId, groupId, -)
- 例如：患者消息
```
{
    "stanza": "message", 
    "op": "query",
    "params": {
        "patientId": < 患者ID >
        "deliverId": < 最小的ID >
    },
    "guid": < 消息GUID > 
}
```
### 返回值

- 患者消息，科室消息，好友消息，群聊消息，小杏消息返回的message结构与初始化数据(init时)结构相同
- 返回结果的stanza(加上s，区分ws的返回): (messages, departMessages, friendMessages, groupChatMessages, supports)
- 例如：患者消息
- 
```
{
    "stanza": "messages" ,
    "messages": [{
        "doctorId": < 医生ID >,
        "patientId": < 患者ID >,
        "receiverId": < 收ID >
        "content": < 消息内容 >,
        "typ": < 消息类别 >,
        "guid": < 消息guid >,
        "status": < 消息状态 >,
        "visibility": < 消息的可见性 >
        "id": < 消息编号 >,
        "created": < 创建时间 >
        }...]
    "updated": updated,
    "guid": < Stanza的GUID >
}
```
### 实现

- 如果请求参数有deliverId, 返回小于deliverId的二十条消息.
- 如果没有参数deliverId, 返回最新的20条消息

## 快捷回复数据后台读取

- 数据库表结构

```
CREATE TABLE IF NOT EXISTS `QuickReplyMessage` (
  `id` int(11) NOT NULL,
  `doctorId` int(11) NOT NULL COMMENT '医生ID' ,
  `quickMessage` varchar(10000) CHARACTER SET utf8mb4 NOT NULL COMMENT '消息内容',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idxDoctorId` (`doctorId`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii ROW_FORMAT=DYNAMIC COMMENT='医生快捷回复消息';
```

### 获取医生所有的快捷消息

- 数据库中没有记录，返回空（modified：false），since之后有更新再推送。

```JavaScript
{
    "stanza": "quickMessage", 
    "op": "query",
    "params": {
        "since": < 上次更新的时间 >
    },
    "guid": < 消息GUID > 
}
```

### 返回值: 

```JavaScript
{
    "stanza": "quickMessage",
    "quickMessage" : {
    	"result": [
	    "", "" ...
	    ] < 快捷消息内容Json >,
    	"modified" : < 是否修改 >
    }
    "updated": < 更新时间 >
}
```
### 修改快捷回复消息

`URL: /api/quickreply`

### 方法

- POST (x-www-form-urlencoded)

### 参数
- content 快捷消息内容Json

```JavaScript
{
    "quickMessage": ["",""...]
}
```

### 返回 

```JavaScript
{
    success: true
}
```

## 语音消息和图片消息

> 增加一个extra，不同的消息保存的内容不一样
- 语音消息：语音长度
- 图片消息：是否原图、原图大小，单位是字节

> 结构
- 语音

```JavaScript
{ 
	"duration": < 长度，单位秒 >
}
```

- 图片

```JavaScript
{ 
    "size": < 图片文件大小 >,
    "w": < 图片宽度 >,
    "h": < 图片高度>,
    "original": < 是否原图,ture|false >
}
```

### API修改

```
/api/patient/$id/chat
/api/patient/chat
/api/friend/$id/chat
/api/friend/chat
/api/groupchat/$gid/chat
/api/suppor
```
都需要增加extra参数，可选

> 数据库
```
CREATE TABLE `MessageExtra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deliverId` int(11) NOT NULL COMMENT '消息的Id',
  `typ` int(4) NOT NULL COMMENT '消息类型',
  `extra` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idxDeliver` (`deliverId`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;
```

- MessageVO增加extra: Option[String]
- 所有获取消息的地方，语音消息和图片消息需要去检查MessageExtra表中的记录

## 首页侧滑协议

### android

```JavaScript
随访与工作
 添加患者：xr://patient.add 		       
 日程：xr://event
 我的患者：xr://patient.all		      
*群发：xr://chat.multiple（新加）
 我的科室：xr://depart			       
 患教资料：xr://document.mine
 UpToDate：http://xingren.com/page/uptodate/landing（固定网址）			       
 随访模板：xr://template.mine
 私人助理小杏：xr://xiaoxing		       
 调查表：xr://survey/my

生活与休闲
 匿名夜班八卦：xr://secret.intro（新加，以前的secret改为八卦详情）	       
 杏仁日报：/page/daily
 杏仁生活委员：/page/events/life

职业与休闲
 添加好友：xr://friend.add		      
*所有好友：xr://friend.all（新加）
 发起群聊：xr://groupChat.new 		   
 名医带教：/page/study/history
 影响力：xr://friend.invite			   
 转诊平台：xr://referral
 可能认识的人：xr://friend.recommend
*心意：xr://present（新加）

个人与品牌
 我的主页：xr://home.page	
 门诊时间：xr://account.clinicPlan
*个人资料：xr://profile(新加)                  
*个人介绍：xr://introduction（新加）
 收费套餐：xr://account.service

 杏仁大学：/page/skills	        
 设置：xr://setting
```
##上传音频文件到COS

### 返回

```
{
  "success":true,
  "url":""
}
```
### URL

`/api/voice/upload`

### 方法

- POST (multipart/form-data)

## 图片处理

- 上传图片到万象图片V2
- 除了八卦的feeds是public，其他图片都是private

> 新增请求参数，返回结果的:

<a name="construct"></a>结构A:

``` 
[
  {
    "picUrl":<图片地址>,
    "size":<图片大小>,
    "w": < 图片宽度 >,
    "h": < 图片高度>,
    "original":< 是否原图,ture|false >
  }
...]
```

> 说明

- 相关接口请求参数增加字段,格式为[结构A](#construct)
- 相关接口返回值追加参数[结构A](#construct)
- 4.1版本处理图片使用[结构A](#construct), 老的字段保留
- API需要同时***维护新老两个字段***，方便不同版本之间兼容
- 数据结构调整，***所涉及到的地方***要考虑是否使用新加的字段

### 相关接口说明

### 诊疗记录

> 数据结构

PatientBookPhoto表新增images

***SQL***

``` JavaScript
ALTER TABLE `PatientBookPhoto` ADD `images` varchar(300) COMMENT '图片信息' AFTER `note`;
```
***结构***

```
{
    "doctorId": < 医生Id >,
    "patientId": < 患者Id >,
    "itemId": < 诊疗记录Id >,
    "path": < 图片地址 >,
    "note": < 备注 >,
    "images": < 新图片 新增 可选 [结构A]> **,
    "deleted": < 是否删除 >,
    "created": < 创建时间 >,
    "updated": < 更新时间 >,
    "id: < Id >
}
```

> url

```
/api/patient/$patientId<[0-9]+>/book/new

```
> 参数

```
{
   "visited": < 诊疗日期(3.7) >,
   "text": < 值(更早版本) >,
   "note": < 文本(3.7) >,
   "photos": < 图片(更早版本)>,
   "photos[]": < 图片数组(3.7) >
   "images": < 图片信息 可选字段 [结构A]>**
}
```

> 诊疗记录列表

注意：微信的web页面需要修改

***url***

```
/api/patient/$patientId<[0-9]+>/book/list
```

***返回***

```
{ 
  "success": < false|true >,
  "increment": < 是否全量刷新 >,
  "books": < 诊疗记录 >,
  "minSeq": < 最小seq >,
  "images": < 新图片 可选 结构A>
}
```

### 首诊记录

***结构***

```
{
    "doctorId": < 医生Id >,
    "patientId": < 患者Id >,
    "name": < 属性名称 >,
    "value": < 属性值 >,
    "typ": < 字段类别 >,
    "section": < 属性区段 >,
    "images": < 新图片 新增 可选 [结构A]> **,
    "updated": < 更新时间 >,
    "id: < Id >
}
```

> url

```
/api/patient/$pid<[0-9]+>/property/new
```

> 请求参数

```
{
   "name": < 名称 >,
   "value": < 值 >,
   "typ": < 类型 >,
   "images": < 图片信息 可选 [结构A]> **
   "section": < 列别 >
}
```
- 新增property type 9,为支持原图的图片格式，在返回VO的时候，把type=9的类别处理成兼容type=3的格式，增加返回images字段

> 属性获取

***url***
 
/api/patient/$pid<[0-9]+>/property

***返回***

```
{
    "doctorId": < 医生ID >,
    "patientId": < 患者ID >,
    "name": < 属性名 >,
    "value": < 属性值 >,
    "typ": < 字段列别 >,
    "section": < 属性类别 >,
    "images": < 新图片 可选 [结构A]>**
    "updated": < 属性时间 >,
    "id":
}
```

### 动态发布

>数据结构调整

DoctorFeedImage增加字段images

***SQL***

``` javaScript
ALTER TABLE `DoctorFeedImage` ADD `images` varchar(300) COMMENT '图片信息' AFTER `path`;
```

***结构***

```
{
    "loginId": < 医生Id >,
    "feedId": < 动态Id >,
    "path": < 图片地址 >,
    "images": < 新图片 新增 可选 [结构A]> **,
    "created": < 更新时间 >,
    "id: < Id >
}
```

> url

```
/api/feed
```

> 参数

```
{
   "content": < 名称 >,
   "isNotify": < 值 >,
   "photo": < 类型 >,
   "photo[]": < 图片列表 >,
   "images": < 图片信息 可选 [结构A]> **
}
```

> 图片返回

注意：科室主页，医生主页调整Web

***url***

```
/api/friend/$id<[0-9]+>/images
```
***返回值***

```
{
   "images": < 名称 >,
   "extra": < 图片信息 可选 [结构A]> **
}
```


### 认证

>数据结构调整

DoctorCertifyPhoto增加字段images

***SQL***

``` javaScript
ALTER TABLE `DoctorCertifyPhoto` ADD `images` varchar(300) COMMENT '图片信息' AFTER `photo`;
```

***结构***

```
{
    "certifyId": < 证书ID >,
    "photo": < 图片地址 >,
    "images": < 新图片 新增 可选 [结构A]> **,
    "id: < Id >
}
```

> url

```
/api/account/certify
```
> 方法

POST

> 参数

新增参数images 格式 < 字段 > **

> 读取最新认证

***url***  

```
/api/account/certify
```

***方法***

POST

***返回***

```
{ 
  "typ": < 认证类型 >,
  "status": < 认证状态 >,
  "message": < 消息 >,
  "tip": < tip >,
  "photos": < 照片 >,
  "hasInvitor": < 是否邀请 >,
  "images": < 图片信息 可选 [结构A] **>
  "id": Long = 0
}
```

### 聊天消息调整

- 通过webSocket发送消息,可处理IMG类型
- IMG类型时, content字段为图片,extra为图片信息，结构为
```
{ 
    "size": < 图片文件大小 >,
    "w": < 图片宽度 >,
    "h": < 图片高度>,
    "original": < 是否原图,ture|false >
}
```
- 存入MessageExtra表



## 职业站
stanza： worksite
> 增加banner

### 返回
```
{
    stanza: "worksite",
    modified: <是否修改>,
    results: {
	    presentIntro: [{
	        typ: < 礼物类型 > ,
	        abbr: < 缩写 > ,
	        thumbnail: < 小图 > ,
	        name: < 名字 > ,
	        subname: < 二级名字 > ,
	        description: < 描述 > ,
	        image: < 图片 > ,
	        url: < 链接 > 
	    }, ... ]，
	    notice: {
			xiaoxing: < 小杏聊天提醒 >,
	        temp: < 温度 >,
	        pic: < 天气图片>
		},
	    skill: {
	        url: < 跳转的URL >,
	        pic: < banner图片 >
	    }，
	    dashboard: {
    	  items: [{
    	  	name: <项目名称>,
    	  	number: <项目数字>,
    	  	increase: <增长百分比数字>
    	  }...],
        report: < 周报的url >
        },
        banner: {
          	url: < 跳转的URL >,
	        pic: < banner图片 >
        }
    }
}

```
