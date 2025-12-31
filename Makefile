MAKEFILE_ENV=.env

include ${MAKEFILE_ENV}

define copy_env
	cp .env ./docker/.env
endef

define network_up
	docker network create -d bridge sam-blog
endef

define build
	docker compose -p ${COMPOSE_PROJECT_NAME} -f ./docker/docker-compose.yaml build
endef

define run
	docker compose -p ${COMPOSE_PROJECT_NAME} -f ./docker/docker-compose.yaml up -d
endef

define stop
	docker compose -p ${COMPOSE_PROJECT_NAME} -f ./docker/docker-compose.yaml down -v --remove-orphans
endef

define session
	docker compose -p ${COMPOSE_PROJECT_NAME} -f ./docker/docker-compose.yaml run --rm -it postgres bash
endef

define db_session
	docker compose -p ${COMPOSE_PROJECT_NAME} -f ./docker/docker-compose.yaml run -e PGPASSWORD="${POSTGRES_PASSWORD}" \
	--rm -it postgres psql -U ${POSTGRES_USER} -d ${POSTGRES_DB} -h postgres
endef

define migrate
	docker compose -p ${COMPOSE_PROJECT_NAME} -f ./docker/docker-compose.yaml run -e PGPASSWORD="${POSTGRES_PASSWORD}" \
	--rm postgres psql -U ${POSTGRES_USER} -d ${POSTGRES_DB} -h postgres -f /migrations/0001.sql
endef

define ps
	docker compose -p ${COMPOSE_PROJECT_NAME} -f ./docker/docker-compose.yaml ps 
endef

define logs_postgres
	docker compose -p ${COMPOSE_PROJECT_NAME} -f ./docker/docker-compose.yaml logs postgres
endef

define logs_pgadmin
	docker compose -p ${COMPOSE_PROJECT_NAME} -f ./docker/docker-compose.yaml logs pgadmin
endef

network_up:
	${call network_up}

build:
	${call copy_env}
	${call build}

deploy:
	${call copy_env}
	${call build}
	${call run}

stop:
	${call stop}

session:
	${call session}

db_session:
	${call db_session}

migrate:
	${call migrate}

ps:
	${call ps}

logs_postgres:
	${call logs_postgres}

logs_pgadmin:
	${call logs_pgadmin}