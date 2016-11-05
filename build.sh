export JAVA_HOME=/usr/lib/jvm/oracle-java8-jdk-amd64 
mvn clean test cobertura:cobertura cobertura:check checkstyle:checkstyle checkstyle:check
