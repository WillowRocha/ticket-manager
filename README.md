# Tickets Manager

# DESAFIO PARIPASSU #

Sistema de gerenciamento de senhas de uma fila, contendo opções como Normal e Preferencial.

# Geral
O sistema conta com 3 telas:
- Painel - /panel
- Cliente - /customer
- Gerente - /manager

### Painel
- Mostra a última semana chamada, e cinco das anteriores.

### Cliente
- Tela onde o cliente pode escolher o tipo de senha que deseja.

### Gerente
- Tela para gerenciar a chamada das senhas. Permitindo chamar a próxima senha, reiniciar a contagem, e adicionalmente gerar novas senhas.


# Ambiente
- O projeto foi desenvolvido com uma API Java, acompanhada de um banco de dados PostgresSQL, e possuindo um Frontend simples, desenvolvido em React.

## Execução
Para rodar o projeto é necessário ter o Docker instalado na máquina hospedeira.

## Ambiente Docker:
- O projeto foi separado em três containeres Docker, deixando assim, Front, API e Banco em locais separados.

### Rede
- Rode este comando para criar a rede necessária para comunicar entre API e Front

1. docker network create -d bridge ticket-manager-network

### API: 
- Navegue até o diretório API e rode os seguintes comandos:

1. `mvn clean package -DskipTests`
2. `docker build -t ticket-manager-api .`
3. `docker-compose up -d`

### FRONT: -
- Navegue até o diretório Front e rode os seguintes comandos:

Atenção 1: Utilizar o `npm` para o install, pois necessitamos do arquivo `package-lock.json`.

1. `docker build -t ticket-manager-front:dev .`
2. `docker run -d -it --rm --name front --network=ticket-manager-network -v ${PWD}:/app -v /app/node_modules -p 3001:3000 -e CHOKIDAR_USEPOLLING=true ticket-manager-front:dev`
