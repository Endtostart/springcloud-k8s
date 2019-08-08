FROM openjdk:8-jdk-alpine
ENV APP_FILE app.jar
ENV APP_HOME /usr/app
EXPOSE 8080
COPY ./*.jar $APP_HOME/$APP_FILE
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]