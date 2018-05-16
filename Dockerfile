FROM openjdk:8-jdk-slim as build

COPY . /tmp/programm
RUN cd /tmp/programm \
    && sed -i s/\\r//g gradlew \
    && ./gradlew build --console=plain \
    && mkdir /opt/programm \
    && cp build/libs/bookstore.jar /opt/programm

FROM openjdk:8-jre-slim
COPY --from=build /tmp/programm/build/libs/bookstore.jar /opt/programm/bookstore.jar
EXPOSE 8085
WORKDIR /opt/programm
ENTRYPOINT ["java"]
CMD ["-jar", "bookstore.jar"]
