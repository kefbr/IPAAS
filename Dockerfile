FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=$(PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/ipaas_projeto

COPY /target/IPAAS-0.0.1-SNAPSHOT.jar ipaas_projeto.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar ipaas_projeto.jar --spring.profiles.active=${PROFILE}
