FROM amazoncorretto:17

WORKDIR /opt/service

RUN java -version

COPY build/libs/springboot-template.jar /opt/service/springboot-template.jar

COPY docker_use/startup.sh /startup.sh

RUN chmod ug+x /startup.sh

EXPOSE 8080

ENTRYPOINT ["sh", "/startup.sh"]