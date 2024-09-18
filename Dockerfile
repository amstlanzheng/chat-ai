# 使用 OpenJDK 22 作为基础镜像
FROM openjdk:22-jdk

# 设置工作目录
WORKDIR /app

# 复制 jar 文件到容器中
COPY JavaAi-0.0.1-SNAPSHOT.jar /app/JavaAi-0.0.1-SNAPSHOT.jar

# 设置自定义环境变量 (Java 代码可以读取)
ENV TENCENTCLOUD_SECRET_ID=AKIDS*****dE
ENV TENCENTCLOUD_SECRET_KEY=hLRO*****dq

# 暴露应用所需的端口（如果应用需要对外提供服务）
EXPOSE 8999

# 启动 jar 文件
CMD ["java", "-jar", "JavaAi-0.0.1-SNAPSHOT.jar"]


# docker build -t Java-ai-0.0.1 .

# docker run -p 8999:8999 java-ai-0.0.1