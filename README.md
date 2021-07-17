# android.financial.management
  用android实现一个简易的个人记账App

# 📋一、系统结构设计Design

## 1. 需求分析

**首先我们先来看下App的需求👇**

设计和实现一个类似个人财务管理的 `Android APP` ，数据库采用 `SQLite` （也可以直接访问 `Web ` 端 `MySQL` 数据库、或提供 `Web` 接口访问 `MySQL` 数据库）。

**APP应具备以下功能：**

- 用户注册和登录（这类 `APP` 一般面对个人，用户不需要分类别）；
- 收入和支出管理（单条收支记录的添加、删除和修改，收入和支出每一条记录至少包括日期、类型、金额和说明。）
- 收入和支出查询（根据时间段、类别等进行查询）
- 收入和支出统计（例如某个月、某个星期或指定时间段的总收支）
- 其他要求：界面设计应尽量美观，可以添加一些图片或背景。

***

基于以上需求，周一对所要实现的功能进行了整理。**请看下方思维导图：**

![需求分析](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/58442d5a051441ffb37b98d8adbe5ebc~tplv-k3u1fbpfcp-zoom-1.image)


## 2. 数据库设计

分析完成之后，接下来开始设计**数据库**。**详情见下方思维导图：**

![数据库设计](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6f7f9a5f7bac4233871c35d84527d556~tplv-k3u1fbpfcp-zoom-1.image)


因为功能较为简单，所以数据库只设计了两张表。

## 3. 界面设计

设计完数据库之后，开始楷模润色该 `APP` 的界面。基于本次课题需求，周一设计了5个界面。接下来先来看 `App` 的**具体界面设计：**

![界面设计](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9ef0ccc552c34a78afed85ca48a37f73~tplv-k3u1fbpfcp-zoom-1.image)


***

看完五个界面所需要的内容之后，接下来，我们来对它进行原型绘制。**请看下图：**

![原型图](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b9ce89a463b54d2fbd0b0e4df6213365~tplv-k3u1fbpfcp-zoom-1.image)


***

现在，原型图设计完毕。我们接着设计高保真 `App` 界面。**请看下图：**

![高保真界面1](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b1f3ffdf3f1245829db1e4500efaade2~tplv-k3u1fbpfcp-zoom-1.image)


![高保真界面2](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4313b738f85d42edafc49ea11ff994f0~tplv-k3u1fbpfcp-zoom-1.image)


## 4. 过程设计

好了，看完原型图之后，我们是不是该来想想**页面与页面之间，是怎么进行交互的呢？**

因此，现在我们来对整个过程进行设计。**详情见下方思维导图：**

![过程设计](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b79e919546ec437fa8d4afad4091aa8e~tplv-k3u1fbpfcp-zoom-1.image)


# 📘二、编码阶段Coding

## 1. 项目结构🗂️

### （1）文件目录

先来看项目的**文件目录**，**详情看下图**👇

![项目文件结构](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/fde931dee21c4a1e8189c3792c9cd77a~tplv-k3u1fbpfcp-zoom-1.image)


### （2）AndroidManifest.xml

接下来附上 `AndroidManifest.xml` 的代码，**具体代码如下：**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.financial.management">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="个人财务管理App"
        android:roundIcon="@mipmap/ic_launcher"
        android:supportsRtl="true"
        android:theme="@style/Theme.Final">
       <activity android:name="com.financial.management.ManageActivity"></activity>
        <activity android:name="com.financial.management.SearchRecordActivity" />
        <activity android:name="com.financial.management.RegisterActivity"/>
        <activity android:name="com.financial.management.UserCenterActivity" />
        <activity android:name="com.financial.management.MainActivity">

        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
        </activity>
    </application>

</manifest>
```

### （3）Activity类解读

看完上面两个内容之后，接下来是Activity类的解读时间。**详情见下表👇**

|        文件名        |                             作用                             |
| :------------------: | :----------------------------------------------------------: |
|     MainActivity     |           用户登录页面Activity，用户可以进行登录。           |
|   RegisterActivity   |           用户注册页面Activity，用户可以进行注册。           |
|  UserCenterActivity  | 个人中心Activity，对应用户个人中心的4个按钮进行功能编写，实现用户收支管理、查看收支、收支统计、退出登录四大功能。 |
|    ManageActivity    |        收支管理Activity，对用户的收入和支出进行管理。        |
| SearchRecordActivity | 查询收支Activity，通过条件对数据库中的数据进行查询，得出用户想要查询的收支结果。 |
|       DBHelper       | 创建数据库表和数据库数据，同时实现与数据库操作相关的登录和注册方法。 |
|         User         |           用户类Activity，用于获取用户ID以及密码。           |

### （4）XML解读

解读完 `Activity` 类之后，现在来对 `XML` 的各个文件进行介绍。**详情见下表👇**

| 文件名                     | 作用                                                         |
| -------------------------- | ------------------------------------------------------------ |
| activity_main.xml          | 用户登录页面，用户通过输入账号和密码，进行登录操作。         |
| activity_register.xml      | 用户注册页面，用户通过输入账号和密码，进行注册操作。         |
| activity_user_center.xml   | 个人中心页面，当用户登录成功以后，进行个人中心页面。个人中心实现收支管理、查看收支、收支统计、退出登录四大功能。 |
| activity_search_record.xml | 通过选择年份月份来进行查询该年该月份的各个收入支出详情，并且计算总金额以及根据类别来计算该类别的总金额。 |
| activity_manage.xml        | 用户对自己的日常开销进行收支管理，可以进行添加、删除和修改等操作。 |
| record_item_layout.xml     | 用来存储列表的传输数据                                       |

# 🎵三、优化方案Optimization Scheme

## (:不合理设计

本App还存在以下等诸多不合理设计，后续还将会持续优化🌈

👉年月份数据写的太固定了，没有可扩展性

👉日期应该用日期选择器来做，而不应该是以文本的形式

👉收入和支出应该以单选框来进行选择，而不应该是文本的形式

👉……

---Ending---
