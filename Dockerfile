FROM tomcat:10.1-jdk25

COPY target/todo-webapp.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080