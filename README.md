# 项目概况

学长好，我编写的部分有：Blog部分，homepage部分。

homepage部分功能包括主页的随机推荐用户显示，条件搜索用户。

blog部分包括博客的创建，修改，分页搜索，查看，点赞等。

数据库中我的部分有使用到用户个人信息库，用户账号数据库，博客库。

更多接口文档在API FOX中，分享链接：https://apifox.com/apidoc/shared-b80a2dbe-05f6-4700-92ce-c7bf2284997e

时间2024.9.8

# 使用方法

使用时必须在请求头中携带令牌，测试时可以先将JsonWebTokenFilter注释掉，否则会导致未登入无法使用一些功能

## 主页部分：

### 1.查询用户：

使用如下路径：

请求方式：GET	

```java
/main/find_user
```

参数：

###### username string  用户名 可选

###### gender integer  性别 可选

###### ageStart integer  范围的最小年龄 可选

###### ageEnd integer  范围的最大年龄 可选

###### email string  邮箱 可选

###### qq string  qq号 可选

###### phone string  手机号 可选

###### page integer  可选 页码(不填的话默认1) 默认值: 1

###### pageSize integer  可选 每一页的大小(不填的话默认10) 默认值: 10

#### 成功：code: 1

案例：

```
{
  "code": 1,
  "msg": null,
  "data": {
    "count": 3,
    "userList": [
      {
        "id": 1,
        "username": "孙洋",
        "age": 1,
        "gender": 1,
        "birthDate": "1984-04-16",
        "email": "s.odbfqnkn@qq.com",
        "phone": "18147584514",
        "qq": "velit",
        "avator": null,
        "province": "河北省",
        "city": "大庆市",
        "introduction": "Excepteur dolore minim",
        "createTime": null,
        "updateTime": "2024-05-20T14:02:21"
      },
      {
        "id": 2,
        "username": "孟刚",
        "age": 1,
        "gender": 1,
        "birthDate": "1984-04-16",
        "email": "s.odbfqnkn@qq.com",
        "phone": "18147584514",
        "qq": "velit",
        "avator": null,
        "province": "河北省",
        "city": "大庆市",
        "introduction": "Excepteur dolore minim",
        "createTime": "2024-05-14T14:20:35",
        "updateTime": "2024-05-14T14:20:38"
      },
      {
        "id": 3,
        "username": "龙桂英",
        "age": 1,
        "gender": 1,
        "birthDate": "2009-04-17",
        "email": "y.gfcyxxtttv@qq.com",
        "phone": "13016045064",
        "qq": "",
        "avator": "https://critical-skills.oss-cn-hangzhou.aliyuncs.com/84589c0d-8ae7-42a3-a668-321d274af321.jpg",
        "province": "湖南省",
        "city": "赣州市",
        "introduction": "eiusmod",
        "createTime": "2024-06-10T18:33:34",
        "updateTime": "2024-06-10T19:18:40"
      }
    ]
  }
}
```

### 2.随机推荐用户：

使用如下路径：

请求方式：GET	

```java
/main
```

#### 成功：code: 1

案例：

```
{
  "code": 1,
  "msg": null,
  "data": {
      {
        "id": 1,
        "username": "孙洋",
        "age": 1,
        "gender": 1,
        "birthDate": "1984-04-16",
        "email": "s.odbfqnkn@qq.com",
        "phone": "18147584514",
        "qq": "velit",
        "avator": null,
        "province": "河北省",
        "city": "大庆市",
        "introduction": "Excepteur dolore minim",
        "createTime": null,
        "updateTime": "2024-05-20T14:02:21"
      }
  }
}
```

## 博客部分：

### 1.创建博客

使用如下路径：

请求方式：POST	

```java
/blogs/new
```

body参数：

ownerId integer  博客发布者ID 必需

title string  标题 必需

text string  内容 必需

photoUrl string  图片 可选

videoUrl string  视频 可选 

#### 成功：code: 1

案例：

```
{
  "code": 1,
  "msg": null,
  "data": null
}
```



### 2.查看博客

使用如下路径：

请求方式：GET

```java
/blogs/blog
```

#### 成功：code: 1

案例：

```
{
    "code": 1,
    "msg": null,
    "data": {
        "id": null,
        "ownerName": "王聪聪",
        "ownerId": 1,
        "title": "内入回",
        "text": "cupidatat minim",
        "photoUrl": "http://dummyimage.com/400x400",
        "videoUrl": "http://hibvpi.ar/lmch",
        "likes": 0,
        "dislikes": 0,
        "createTime": "2024-05-14T16:15:08",
        "updateTime": "2024-05-14T16:15:08",
        "isLike": 0
    }
}
```



### 3.分页查看博客列表

使用如下路径：

请求方式：GET

```java
/blogs
```

#### 成功：code: 1

参数：

###### page integer  默认为1，非必须

###### pageSize integer  默认为10，非必须



案例：

```
{
    "code": 1,
    "msg": null,
    "data": {
        "count": 7,
        "userList": [
            {
                "id": 1,
                "ownerName": "王聪聪",
                "ownerId": 1,
                "title": "内入回",
                "text": "cupidatat minim",
                "photoUrl": "http://dummyimage.com/400x400",
                "videoUrl": "http://hibvpi.ar/lmch",
                "likes": 0,
                "dislikes": 0,
                "shares": 0,
                "createTime": "2024-05-14T16:15:08",
                "updateTime": "2024-05-14T16:15:08"
            },
            {
                "id": 4,
                "ownerName": "张春阳",
                "ownerId": 2,
                "title": "因其三矿四",
                "text": "labore exercitation",
                "photoUrl": "http://dummyimage.com/400x400",
                "videoUrl": "http://kamxcj.ky/jtxwg",
                "likes": 0,
                "dislikes": 0,
                "shares": 0,
                "createTime": "2024-05-14T18:06:10",
                "updateTime": "2024-05-14T18:06:10"
            },
            {
                "id": 5,
                "ownerName": "王聪聪",
                "ownerId": 1,
                "title": "每统儿量",
                "text": "sit exercitation dolore est",
                "photoUrl": "http://dummyimage.com/400x400",
                "videoUrl": "http://hxebdanmg.tc/yxraigyh",
                "likes": 0,
                "dislikes": 0,
                "shares": 0,
                "createTime": "2024-05-14T18:06:18",
                "updateTime": "2024-05-14T18:06:18"
            },
            {
                "id": 6,
                "ownerName": "Jaye",
                "ownerId": 3,
                "title": "认领通标",
                "text": "Ut dolore Lorem eiusmod",
                "photoUrl": "http://dummyimage.com/400x400",
                "videoUrl": "http://bsdhh.bm/igqejbm",
                "likes": 0,
                "dislikes": 0,
                "shares": 0,
                "createTime": "2024-05-14T18:06:29",
                "updateTime": "2024-05-14T18:06:29"
            },
            {
                "id": 7,
                "ownerName": "王聪聪",
                "ownerId": 1,
                "title": "志进力1111111达",
                "text": "aute in",
                "photoUrl": "http://dummyimage.com/400x400",
                "videoUrl": "http://bfkq.ad/lmdtx",
                "likes": 0,
                "dislikes": 0,
                "shares": 0,
                "createTime": "2024-05-14T18:06:40",
                "updateTime": "2024-05-14T18:06:40"
            },
            {
                "id": 8,
                "ownerName": "Jaye",
                "ownerId": 3,
                "title": "容地利展空米",
                "text": "tempor non enim consectetur",
                "photoUrl": "http://dummyimage.com/400x400",
                "videoUrl": "http://bdnaamcddv.bd/retkgufut",
                "likes": 0,
                "dislikes": 0,
                "shares": 0,
                "createTime": "2024-05-14T18:06:49",
                "updateTime": "2024-05-14T18:06:49"
            },
            {
                "id": 9,
                "ownerName": "Jaye",
                "ownerId": 3,
                "title": "又外我上消",
                "text": "nulla",
                "photoUrl": "http://dummyimage.com/400x400",
                "videoUrl": "http://yejcv.cn/vkxji",
                "likes": 0,
                "dislikes": 0,
                "shares": 0,
                "createTime": "2024-05-14T18:06:55",
                "updateTime": "2024-05-14T18:06:55"
            }
        ]
    }
}
```



### 4.分页模糊搜索博客

使用如下路径：

请求方式：GET

```java
/blogs/search
```

#### 成功：code: 1

参数：

###### searchText string  搜索词，可空

###### page integer  默认为1，非必须

###### pageSize integer  默认为10，非必须



案例：

```
{
    "code": 1,
    "msg": null,
    "data": {
        "count": 1,
        "userList": [
            {
                "id": 1,
                "ownerName": "王聪聪",
                "ownerId": 1,
                "title": "内入回",
                "text": "cupidatat minim",
                "photoUrl": "http://dummyimage.com/400x400",
                "videoUrl": "http://hibvpi.ar/lmch",
                "likes": 0,
                "dislikes": 0,
                "shares": 0,
                "createTime": "2024-05-14T16:15:08",
                "updateTime": "2024-05-14T16:15:08"
            }
        ]
    }
}
```



### 5.删除博客

使用如下路径：

请求方式：GET

```java
/blogs/delete
```

#### 成功：code: 1

参数：

###### blogId integer 必须



案例：

```
{
    "code": 1,
    "msg": "null",
    "data": {}
}
```



### 6.点赞或反对

使用如下路径：

请求方式：GET

```java
/blogs/like
```

#### 成功：code: 1

参数：

###### targetId integer  必需 赞的博客或评论的id 或者 反对的博客id

###### isLike integer  1为点赞，0为反对 必需

###### isBlog integer  1为博客，0为评论 必需



案例：

```
{
  "code": 1,
  "msg": "null",
  "data": {}
}
```



### 7.取消点赞或取消反对

使用如下路径：

请求方式：GET

```java
/blogs/cacel
```

#### 成功：code: 1

参数：

###### targetId integer  目标id 可选

###### isBlog integer  可选 是否为博客，1为博客，为评论



案例：

```
{
    "code": 0,
    "msg": "string",
    "data": {}
}
```



