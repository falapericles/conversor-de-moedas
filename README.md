# Conversor de Moedas

Este projeto é um conversor de moedas feito em Java para o Challenge One da Alura/Oracle. Ele permite converter valores entre diferentes moedas usando uma API que fornece taxas de câmbio.

## Funcionalidades

- Converte valores entre várias moedas.
- Usa uma API para pegar as taxas de câmbio atualizadas.
- Interface simples no console para o usuário.

## Tecnologias Utilizadas

- **Java 11+**
- **HttpClient** para fazer requisições HTTP.
- **Gson** para manipular dados JSON.

## Pré-requisitos

- **Java 11** ou mais recente instalado.
- **IntelliJ IDEA** ou outra IDE para Java.
- Conexão com a internet para acessar a API.

## Como Rodar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/conversor-de-moedas.git
cd conversor-de-moedas
```

### 2. Abrir o projeto na IDE

Abra o IntelliJ IDEA (ou outra IDE que você usa) e importe o projeto `conversor-de-moedas`.

### 3. Adicionar a dependência Gson

Se você estiver usando Maven, adicione isso no `pom.xml`:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.9</version>
</dependency>
```

Se estiver usando Gradle, adicione isso no `build.gradle`:

```groovy
implementation 'com.google.code.gson:gson:2.8.9'
```

### 4. Rodar a aplicação

Execute a classe `CurrencyConverter` na sua IDE.

## Como Usar

Quando você rodar a aplicação, verá este menu no console:

```
Bem-vindo ao Conversor de Moedas!
Escolha a conversão que deseja fazer:
1. USD para BRL
2. BRL para USD
3. EUR para BRL
4. BRL para EUR
5. GBP para BRL
6. BRL para GBP
0. Sair
```

Escolha uma opção digitando o número e depois digite o valor a ser convertido. O programa vai mostrar o valor convertido.

### Exemplo de Conversão

```
Escolha a conversão que deseja fazer:
1
Digite o valor a ser convertido:
100
Valor convertido: 542.97
```

## Estrutura JSON da API

A resposta da API de taxas de câmbio é assim:

```json
{
  "result": "success",
  "base_code": "USD",
  "conversion_rates": {
    "USD": 1,
    "BRL": 5.4297,
    "EUR": 0.9183,
    "GBP": 0.7714,
    ...
  }
}
```

## Considerações Finais

Este projeto ajuda a aprender sobre:

- Fazer requisições HTTP em Java.
- Manipular dados JSON com Gson.
- Criar interfaces de console em Java.

## Contribuições

Se você encontrar algum problema ou tiver sugestões, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está sob a Licença MIT.
