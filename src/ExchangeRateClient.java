import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

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
        try {
            // Crear un cliente HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Personalización de la solicitud HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))  // Configurar la URL
                    .header("User-Agent", "Java HttpClient") // Encabezado personalizado
                    .header("Accept", "application/json") // Especificar que esperamos una respuesta en JSON
                    .timeout(java.time.Duration.ofSeconds(10)) // Configurar un tiempo de espera de 10 segundos
                    .build(); // Construir la solicitud

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Acceder a los diferentes elementos de la respuesta
            int statusCode = response.statusCode(); // Código de estado de la respuesta
            String contentType = response.headers().firstValue("Content-Type").orElse(""); // Tipo de contenido

            // Mostrar el código de estado
            System.out.println("Código de estado: " + statusCode);

            // Verificar si la respuesta es JSON
            if (contentType.contains("application/json")) {
                System.out.println("La respuesta es JSON.");

                // Usar Gson para deserializar el JSON a un objeto Java
                Gson gson = new Gson();
                ExchangeRateResponse exchangeRateResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

                // Mostrar los datos deserializados
                System.out.println("Base: " + exchangeRateResponse.getBase());
                System.out.println("Tasas de cambio: " + exchangeRateResponse.getRates());

                // Análisis detallado del JSON usando JsonParser y JsonObject
                JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

                // Acceder al campo 'base'
                String baseCurrency = jsonResponse.get("base").getAsString();
                System.out.println("Moneda base (usando JsonObject): " + baseCurrency);

                // Acceder a las tasas de cambio de la moneda base
                JsonObject rates = jsonResponse.getAsJsonObject("rates");
                System.out.println("Tasa de cambio del EUR (usando JsonObject): " + rates.get("EUR").getAsDouble());
            } else {
                System.out.println("La respuesta no es JSON.");
            }

            // Acceder a los encabezados
            response.headers().map().forEach((key, value) -> {
                System.out.println(key + ": " + value); // Mostrar todos los encabezados de la respuesta
            });

            // Mostrar el cuerpo de la respuesta
            System.out.println("Cuerpo de la respuesta: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
