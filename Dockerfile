FROM tomcat:10.1-jdk21

COPY target/todo_item_webapp-1.0.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]