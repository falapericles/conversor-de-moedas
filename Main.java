import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    
    //preencher com a URL da API
    private static final String API_URL = " ";
    private static final String[] MOEDAS = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bem-vindo ao Conversor de Moedas!");
            System.out.println("Escolha a conversão que deseja fazer:");
            System.out.println("1. USD para BRL");
            System.out.println("2. BRL para USD");
            System.out.println("3. EUR para BRL");
            System.out.println("4. BRL para EUR");
            System.out.println("5. GBP para BRL");
            System.out.println("6. BRL para GBP");
            System.out.println("0. Sair");

            int option = scanner.nextInt();
            if (option == 0) {
                System.out.println("Saindo...");
                break;
            }

            System.out.println("Digite o valor a ser convertido:");
            double valor = scanner.nextDouble();

            // fazendo a conversão da moeda
            double valorConvertido = converterMoeda(option, valor);
            if (valorConvertido != -1) {
                System.out.println("Valor convertido: " + valorConvertido);
            } else {
                System.out.println("Erro ao converter o valor. Tente novamente.");
            }
        }
    }

    private static double converterMoeda(int opcao, double valor) {
        try {
            JsonObject rates = getExchangeRates();
            if (rates == null) {
                return -1;
            }

            double cotacao = 0.0;

            // selecionando a taxa de câmbio com base na opção escolhida
            switch (opcao) {
                case 1:
                    cotacao = rates.get("BRL").getAsDouble();
                    break;
                case 2:
                    cotacao = 1 / rates.get("BRL").getAsDouble();
                    break;
                case 3:
                    cotacao = rates.get("BRL").getAsDouble() / rates.get("EUR").getAsDouble();
                    break;
                case 4:
                    cotacao = rates.get("EUR").getAsDouble() / rates.get("BRL").getAsDouble();
                    break;
                case 5:
                    cotacao = rates.get("BRL").getAsDouble() / rates.get("GBP").getAsDouble();
                    break;
                case 6:
                    cotacao = rates.get("GBP").getAsDouble() / rates.get("BRL").getAsDouble();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return -1;
            }
            return valor * cotacao;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static JsonObject getExchangeRates() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        return json.getAsJsonObject("conversion_rates");
    }
}
