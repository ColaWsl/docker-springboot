# 使用 Ubuntu 22.04 作为基础镜像
FROM ubuntu:22.04

LABEL maintainer="wsl612425@163.com"

# 设置环境变量
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# 更新系统并安装必要的软件包
RUN apt update && apt install -y \
    openjdk-21-jdk \
    && rm -rf /var/lib/apt/lists/*

# 存放应用程序的目录
WORKDIR /app

# 拷贝 Spring Boot 应用程序到容器中的 /app 目录
COPY target/springboot-web-1.0.0.jar /app/app.jar

# 暴露 Spring Boot 应用程序的端口
EXPOSE 8080

# 启动 Spring Boot 应用程序
CMD ["java", "-jar", "/app/app.jar"]
