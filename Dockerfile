FROM openjdk
ADD target/spring-boot-cart-shop.jar spring-boot-cart-shop.jar
COPY src/main/resources/db/migration db/migration
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-boot-cart-shop.jar"]










