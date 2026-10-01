.PHONY: dependencies clean test install help

dependencies:
	./mvnw dependency:tree

clean:
	./mvnw clean

test:
	./mvnw clean test

install:
	./mvnw clean install

help:
	@echo 'make dependencies - Show the dependencies'
	@echo 'make clean - Clean the project'
	@echo 'make test - Run the tests'
	@echo 'make install - Install the project'
	@echo 'make help - Show this help message'
