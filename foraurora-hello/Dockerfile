# Base images 基础镜像
FROM java:8u111

# MAINTAINER 维护者信息
MAINTAINER hwangchao@126.com

# 保证工作目录
RUN mkdir -p /opt/foraurora-hello
WORKDIR /opt/foraurora-hello

# VOLUME：挂载目录
VOLUME /opt/foraurora-hello

COPY ./target/foraurora-hello.jar /opt/foraurora-hello/foraurora-hello.jar

#EXPOSE 容器暴露端口
EXPOSE 8999

#ENV JAVA_OPT="-Xms2g -Xmx2g "
#ENTRYPOINT 应用启动命令 参数设定
#由于tomcat的session id的生成主要通过java.security.SecureRandom生成随机数来实现，导致启动过程很慢
#通过-Djava.security.egd=file:/dev/./urandom参数可以加快随机数产生过程
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/foraurora-hello/foraurora-hello.jar"]
