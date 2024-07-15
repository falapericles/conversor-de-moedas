import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/ae8af4f8c32e428bdaee512d/latest/USD";
    private static final String[] CURRENCIES = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

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
            double value = scanner.nextDouble();

            double convertedValue = convertCurrency(option, value);
            if (convertedValue != -1) {
                System.out.println("Valor convertido: " + convertedValue);
            } else {
                System.out.println("Erro ao converter o valor. Tente novamente.");
            }
        }
    }

    private static double convertCurrency(int option, double value) {
        try {
            JsonObject rates = getExchangeRates();
            if (rates == null) {
                return -1;
            }

            double rate = 0.0;
            switch (option) {
                case 1:
                    rate = rates.get("BRL").getAsDouble();
                    break;
                case 2:
                    rate = 1 / rates.get("BRL").getAsDouble();
                    break;
                case 3:
                    rate = rates.get("BRL").getAsDouble() / rates.get("EUR").getAsDouble();
                    break;
                case 4:
                    rate = rates.get("EUR").getAsDouble() / rates.get("BRL").getAsDouble();
                    break;
                case 5:
                    rate = rates.get("BRL").getAsDouble() / rates.get("GBP").getAsDouble();
                    break;
                case 6:
                    rate = rates.get("GBP").getAsDouble() / rates.get("BRL").getAsDouble();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return -1;
            }
            return value * rate;
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
