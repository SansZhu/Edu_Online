---
title: DevelopHistory
---

# Edu_Online毕业设计

# 起步

本毕业设计分为系统前台和系统后台

## 系统前台

讲师管理模块：可以添加讲师，删除讲师，修改讲师信息。
课程管理模块：可以添加课程，删除课程等操作。
权限管理模块：可以更改用户权限。
统计分析模块：学生登录数，课程播放统计等操作。

## 系统前台

注册和登录模块：用户可以进行登陆和注册。
数据展示模块：用户可以在前台浏览课程。
课程播放模块：用户可以播放视频。



## 数据库

### 设计数据表

首先进行数据库的设计，因为本项目暂定拥有以上几种模块，所以要将每一个模块中所需对象提取出来

所提取出的对象为：

-  讲师
- 课程
- 用户
- 视频内容

由于需要进行对历史记录、收藏课程、课程播放量、登录数的统计，所以将这些数据也创建为对象并建立数据表

所以最初应有以下表：

- 用户(user)

  - | field name | type    |
    | ---------- | ------- |
    | user_id    | int     |
    | user_name  | varchar |
    | user_email | varchar |
    | user_phone | varchar |
    | user_photo | varchar |
    | authority  | int     |
    | password   | varchar |

- 课程(course)

  - | field name   | type    |
    | ------------ | ------- |
    | course_id    | int     |
    | course_photo | varchar |
    | teach_id     | varchar |
    | course_type  | varchar |
    | detail       | varchar |

- 课程视频内容(video)

  - | field name  | type    |
    | ----------- | ------- |
    | video_order | int     |
    | course_id   | int     |
    | video_url   | varchar |

- 讲师(teacher)

  - | field name  | type    |
    | ----------- | ------- |
    | teach_id    | int     |
    | teach_name  | varchar |
    | teach_email | varchar |
    | teach_phone | varchar |
    | teach_photo | varchar |

- 历史记录(history)

  - | field name | type |
    | ---------- | ---- |
    | user_id    | int  |
    | course_id  | int  |

- 收藏课程(collection)

  - | field name | type |
    | ---------- | ---- |
    | user_id    | int  |
    | course_id  | int  |

- 课程播放量(views)

  - | field name | type |
    | ---------- | ---- |
    | course_id  | int  |
    | view       | int  |

- 登录数(count)

  - | field name  | type |
    | ----------- | ---- |
    | date        | date |
    | login_count | int  |

### 建立数据表

本项目使用的数据库为关系型数据库管理系统MySQL，此数据库管理系统为瑞典MySQL AB公司开发，是属于Oracle其他的产品，也是当今最流行的关系型数据库管理系统之一。

本项目使用的数据库管理工具为Navicat Premium,此应用程序可以对MySQL进行快速、便捷的连接，而且给用户提供了图形化数字界面，可以简单、方便的进行对数据库的各种操作。通过Navicat Premium既可以使用sql语句进行创建数据库和数据表，也可以使用图形化界面创建数据表。本次使用的是图形化界面来创建数据表。



## 前端页面

### 用户

本项目的设计为：用户在未登录的情况下仅可以进入主页、登录页面和注册页面，当用户点击课程想进入观看时，系统提示请登录后观看课程。用户可以选择登录或者注册。如选择注册，则进入注册界面。注册成功后会自动返回登录页让用户进行登录操作。如选择登录，则进入登录界面，登录成功会返回到主页继续其他操作。所以我们需要的页面有：主页、登录页面、和注册页面。

登录成功后用户可以进行的操作有：查看主页、查看课程、播放教学视频、查看历史记录、查看收藏课程、查看本人信息和查看讲师信息。所以我们另外需要的页面有：课程页、视频播放页、历史记录页、收藏页、本人信息页、讲师列表页、讲师信息页。

管理员可以登录后台可以管理用户或者讲师的信息，添加删除课程，为课程添加删除视频，查看每日或总登录数。所以我们在管理员后台管理页有以下功能：用户管理、讲师管理、课程管理、查看数据统计。

以上，本项目暂定有以下页面：

- 主页
- 登录页
- 注册页
- 课程页
- 视频播放页
- 历史记录页
- 收藏页
- 本人信息页
- 讲师列表页
- 讲师信息页
- 后台管理页
  - 用户管理页
  - 讲师管理页
  - 课程管理页
  - 数据统计页
- 错误页

其中前端页面的头部导航栏和后端页面的左侧导航栏都可以复用。







