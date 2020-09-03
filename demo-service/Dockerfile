FROM openjre:11

MAINTAINER COGNIZANT

WORKDIR /opt/software


ADD /usermanagement-api/target/usermanagement-api-11.0.0.jar usermanagement-api-11.0.0.jar
ADD T-TeleSec_GlobalRoot_Class_2.pem T-TeleSec_GlobalRoot_Class_2.pem


EXPOSE 15970
RUN echo "yes" | keytool -import -trustcacerts -file /opt/software/T-TeleSec_GlobalRoot_Class_2.pem -alias my-root-ca1 -keystore /opt/software/jre-11-openjdk/lib/security/cacerts -storepass changeit

ENTRYPOINT echo '10.14.70.5 kds.telekom.de' >> /etc/hosts & java -jar usermanagement-api-11.0.0.jar -Dfile.encoding="UTF-8" --spring.profiles.active=${PROFILE_NAME} --spring.cloud.config.uri=http://${CONFIG_SERVER_IP}:8888

RUN rm -f opt/software/T-TeleSec_GlobalRoot_Class_2.pem
