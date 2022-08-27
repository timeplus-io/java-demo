
build:
	mvn clean install

run:
	mvn compile exec:java -Dexec.mainClass="timeplus.io.Sample"