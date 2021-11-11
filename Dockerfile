FROM openjdk:11
COPY build/libs/api-service-1.0.jar api-service-1.0.jar
ENTRYPOINT ["java","-jar","api-service-1.0.jar"]
#
# #
# # Set a variable that can be used in all stages.
# #
# ARG BUILD_HOME=api-service
# ARG APP_VERSION=0.0.1-SNAPSHOT
# ARG APP_NAME=api-service
# #
# # Gradle image for the build stage.
# #
# FROM gradle:6-jdk11-alpine as build-image
#
# #
# # Set the working directory.
# #
# ARG BUILD_HOME
# ENV APP_HOME=$BUILD_HOME
# WORKDIR $APP_HOME
#
# #
# # Copy the Gradle config, source code, and static analysis config
# # into the build container.
# #
# COPY --chown=gradle:gradle build.gradle settings.gradle gradle.properties $APP_HOME/
# COPY --chown=gradle:gradle src $APP_HOME/src
# #COPY --chown=gradle:gradle settings.gradle $APP_HOME/
#
# #
# # Build the application.
# #
# RUN gradle --no-daemon --no-watch-fs --info clean build
#
# RUN ls -ltr $APP_HOME/build/libs

#
# Java image for the application to run in.
#
# FROM openjdk:11

#
# Copy the jar file in and name it app.jar.
#
# ARG BUILD_HOME
# ARG APP_VERSION
# ARG APP_NAME
# ENV APP_HOME=$BUILD_HOME
# ENV APP_VERSION=$APP_VERSION
# ENV APP_NAME=$APP_NAME
# COPY --from=build-image $APP_HOME/build/libs/$APP_NAME-$APP_VERSION.jar app.jar

#
# The command to run when the container starts.
#
# ENTRYPOINT java -jar app.jar