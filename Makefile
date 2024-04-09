.DEFAULT_GOAL := build-run

run-dist:
	./build/install/app/bin/app

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

build:
	make -C app build

install:
	./gradlew clean install

run:
	./gradlew run

test:
	./gradlew test

report:
	make -C app report

lint:
	make -C app lint

check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run

.PHONY: build
