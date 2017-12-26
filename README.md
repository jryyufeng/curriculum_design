# curriculum_design
基于DES的信息加密助手
===================
![](https://img.shields.io/eclipse-marketplace/v/notepad4e.svg)        ![](https://img.shields.io/jenkins/t/https/jenkins.qa.ubuntu.com/view/Precise/view/All%20Precise/job/precise-desktop-amd64_default.svg)
#### <i class="icon-file"></i>功能及使用
-------------

软件基于java1.7开发，测试运行环境为windows10，已导出可运行jar文件
### 功能

**基于DES的信息加密助手** 功能简述如下:

本地加密
:文件加解密
:文件夹内容递归加密

安全传输
:本地加密
:安全传输
:接收方解密

大文件压缩
:大文件压缩为zip格式
:文件的解压




> **FunctionNote:**

> - 注册登录.
> - 本地加解密采用DES加密的密码分组链接（CBC）模式.
> - 加密过程采用多线程并发处理，对于文件夹采用递归获取文件加密的方式
> - 文件的备份，对于处理过的文件在本地存有不可见模式为只读的文件备份
> - 文件安全传输采用**socket**编程p2p传输，服务器端需要先启动，服务器采用单次接收变长数据，服务器编写可改为并发服务器以处理多个客户端同时连接
