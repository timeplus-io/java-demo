
build:
	mvn clean install
	mvn dependency:copy-dependencies

one_jar:
	mvn clean compile assembly:single

run:
	mvn compile exec:java -Dexec.mainClass="samples.ApplicationSample"

dep:
	mvn dependency:copy-dependencies