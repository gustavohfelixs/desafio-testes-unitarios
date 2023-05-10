## Desafio 1 Testes unitários
===========================
**Pré-requisitos**
- JAVA 8
- Maven 3.3+
- IDE de sua preferência

*****
**Primeiro desafio da minha trilha de estudos no mundo de testes unitários - [curso Testes Unitários em JAVA*](https://www.udemy.com/course/testes-unitarios-em-java/learn)**
## Introdução
**Testes Unitários** são testes focados em unidades (parece óbvio mas não é), mas o que eles são?? Basicamente você pegar partes isoladas de uma aplicação e criar um teste para garantir a integridade daquela parte, imagine comigo
temos o método `fazSomaEntreDoisNumeros()`, você o cria e está tudo funcionando, logo mais surgem muitas outras classes e métodos
mas mexendo no código algo estranho acontece, ele para de funcionar. Onde está o erro? Caso você tenha os testes unitários fica muito mais fácil de rastrear isso tudo.

#### Como utilizar o projeto?
1. Baixe o arquivo para seu computador como zip
2. extraia o arquivo zip
3. Import para sua IDE como projeto Maven

## Instale as dependências
`mvn clean install`

### Clean and Build
`mvn clean package`

### Executar projeto
`mvn spring-boot:run` <br>Ou<br> `java -jar ./target/App-0.0.1-SNAPSHOT.jar` <br><br> Você também pode rodar de a classe **AppApplication** em sua IDE que está no caminho `br.com.gfelix.app.AppApplication`

### Conclusão
No ambiente produto é essêncial o uso de testes unitários pois "separar" o código em partes específicas nos da rastreabilidade se algo der errado. Ainda estou me aprofundando
e recomendo demais o curso do prof Francisco Wagner - [link](https://www.udemy.com/course/testes-unitarios-em-java/learn)