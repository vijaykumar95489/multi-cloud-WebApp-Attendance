FROM tomcat:8-jdk8-corretto
# https://hub.docker.com/layers/library/tomcat/7.0.109-jdk8-openjdk/images/sha256-489823486120d076cb576640c5819c6fa54948f470b46c54f02b48f462eb2c23
LABEL org.nz.itlatinos.image.authors="aleonrangel@outlook.co.nz"
LABEL maintainer="andres.nz"
ENV APP_WAR_FILE="AttendanceApp-0.0.1.war"

# Tomcat Custom settings
COPY "build/libs/$APP_WAR_FILE" /usr/local/tomcat/webapps/