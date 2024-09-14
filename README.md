### 简介
目前仍属于开发阶段，不建议操作
本项目目前使用的是腾讯的混元大模型 后续可能会继续添加其他的模型

### 环境
1. JDK 22
2. springboot 3.3.3
3. 可以降低版本，哥们纯属手贱喜欢搞这些

### 部署
1. 修改[application.yml](src%2Fmain%2Fresources%2Fapplication.properties) 主要是数据库信息
2. 执行resource/sql 里面的文件创建数据库表
3. 添加环境变量，需要将腾讯的密钥添加到环境变量中 具体获取请上对应的服务商官网
```
TENCENTCLOUD_SECRET_ID=AK****EydE;TENCENTCLOUD_SECRET_KEY=hLR****Idq
```
