
build:
	mvn clean install
	mvn dependency:copy-dependencies

one_jar:
	mvn clean compile assembly:single

run_app:
	mvn compile exec:java -Dexec.mainClass="samples.ApplicationSample"

run_jdbc:
	mvn compile exec:java -Dexec.mainClass="samples.JDBCSample"

dep:
	mvn dependency:copy-dependencies