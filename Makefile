
build:
	mvn clean install

run_app:
	mvn compile exec:java -Dexec.mainClass="samples.ApplicationSample"

run_jdbc:
	mvn compile exec:java -Dexec.mainClass="samples.JDBCSample"