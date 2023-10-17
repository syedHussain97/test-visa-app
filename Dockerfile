FROM --platform=linux/x86_64  amazoncorretto:11.0.20-alpine
VOLUME /tmp
COPY run.sh /app/run.sh
COPY target/*.jar app.jar
EXPOSE 8090
CMD /app/run.sh