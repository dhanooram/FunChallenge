package Megaverse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AstralObjectHandler {
  protected String apiUrl;

  public abstract void create(int row, int column, String candidateId);

  public abstract void delete(int row, int column, String candidateId);

  protected String getApiUrl() {
    return apiUrl;
  }

  protected String createJsonBody(int row, int column, String candidateId) {
    return String.format("{\"row\": %d, \"column\": %d, \"candidateId\": \"%s\"}", row, column, candidateId);
  }


/*
* This API server doesn't support multiple batch in single load,
* so we can't use single rest call with many params to dram all shapes. Loading with parallel thread the program results
*  in 429 Response from the server. So there should be some Rate Limiter logic so that API server doesn't hit many load
*  at same time. The application has be designed to retry for a couple of times and the sleep type increases between each retry.
*
* */
  protected void makeHttpRequest(String url, String method, String jsonBody) {
    int maxRetries = 5;
    int retryDelayMillis = 1000; // 1 second delay between retries

    for (int retry = 0; retry < maxRetries; retry++) {
      try {
        System.out.println(url);
        System.out.println(method);
        System.out.println(jsonBody);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .method(method, HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        if (response.statusCode() == 429) {
          System.out.println("Too Many Requests. Retrying...");
          retryDelayMillis *= 2; // Exponential increase in delay
          Thread.sleep(retryDelayMillis); // Add a delay before retrying

        } else {
          System.out.println("HTTP " + method + " Response: " + response.statusCode() + " " + response.toString());
          // Exit the retry loop if the request is successful
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}