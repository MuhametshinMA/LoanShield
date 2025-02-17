docker-compose:
	docker-compose up --build -d

run: docker-compose
	mvnw.cmd clean test
