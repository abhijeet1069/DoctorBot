# Makefile for Spring Boot project (Maven + ngrok)

.PHONY: clean build run start-ngrok all stop

all: clean build run start-ngrok

clean:
	mvn clean

build:
	mvn clean package

# Run Spring Boot in background
run:
	nohup java -jar target/*.jar > spring.log 2>&1 & echo $$! > spring.pid

# Start ngrok pointing to localhost:8080 with reserved domain
start-ngrok:
	nohup ngrok http --domain=choice-mule-brave.ngrok-free.app 8080 > ngrok.log 2>&1 & echo $$! > ngrok.pid

# Stop both processes
stop:
	@if [ -f spring.pid ]; then kill $$(cat spring.pid) && rm spring.pid; fi
	@if [ -f ngrok.pid ]; then kill $$(cat ngrok.pid) && rm ngrok.pid; fi