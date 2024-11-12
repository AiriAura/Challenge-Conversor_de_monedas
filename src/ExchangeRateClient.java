import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

class ExchangeRateResponse {
    @SerializedName("base")
    private String base;

    @SerializedName("rates")
    private Map<String, Double> rates;

    // Getters
    public String getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}

public class ExchangeRateClient {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD"; // URL de la API de tasas de cambio

    public static void main(String[] args) {
        // Crear un cliente HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Personalización de la solicitud HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))  // Configurar la URL
                .header("User-Agent", "Java HttpClient") // Encabezado personalizado
                .header("Accept", "application/json") // Especificar que esperamos una respuesta en JSON
                .timeout(java.time.Duration.ofSeconds(10)) // Configurar un tiempo de espera de 10 segundos
                .build(); // Construir la solicitud

        try {
            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar si la respuesta es JSON
            String contentType = response.headers().firstValue("Content-Type").orElse(""); // Tipo de contenido
            if (contentType.contains("application/json")) {
                System.out.println("La respuesta es JSON.");

                // Usar Gson para deserializar el JSON a un objeto Java
                Gson gson = new Gson();
                ExchangeRateResponse exchangeRateResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

                // Análisis detallado del JSON usando JsonParser y JsonObject
                JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonObject rates = jsonResponse.getAsJsonObject("rates");

                // Menú interactivo
                Scanner scanner = new Scanner(System.in);
                int option;
                do {
                    // Mostrar el menú
                    System.out.println("\n--- Menú de Conversión de Monedas ---");
                    System.out.println("1. Realizar una conversión de moneda");
                    System.out.println("2. Salir");
                    System.out.print("Seleccione una opción: ");
                    option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            // Pedir al usuario el valor y las monedas
                            System.out.print("Ingrese el valor a convertir: ");
                            double amount = scanner.nextDouble();

                            System.out.print("Ingrese la moneda de origen (USD, ARS, BOB, BRL): ");
                            String fromCurrency = scanner.next().toUpperCase();

                            System.out.print("Ingrese la moneda de destino (ARS, BOB, BRL): ");
                            String toCurrency = scanner.next().toUpperCase();

                            // Realizar la conversión
                            double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency, rates);
                            System.out.printf("Resultado de la conversión: %.2f %s = %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);
                            break;

                        case 2:
                            System.out.println("Gracias por usar el convertidor. ¡Hasta luego!");
                            break;

                        default:
                            System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    }
                } while (option != 2); // El ciclo se repite hasta que el usuario elige salir

            } else {
                System.out.println("La respuesta no es JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para convertir una moneda a otra utilizando las tasas de cambio
    public static double convertCurrency(double amount, String fromCurrency, String toCurrency, JsonObject rates) {
        // Obtener la tasa de cambio de la moneda de origen y destino
        double fromRate = rates.get(fromCurrency).getAsDouble();
        double toRate = rates.get(toCurrency).getAsDouble();

        // Realizar la conversión
        double convertedAmount = (amount / fromRate) * toRate;
        return convertedAmount;
    }
}
