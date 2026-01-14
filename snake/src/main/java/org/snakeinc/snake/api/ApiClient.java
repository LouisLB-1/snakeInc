package org.snakeinc.snake.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api/v1";

    public static void postScore(int value, int playerId) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(BASE_URL + "/scores");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonBody = """
            {
              "value": %d,
              "snake": "Anaconda",
              "playerId": %d
            }
            """.formatted(value, playerId);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
            }

            int status = connection.getResponseCode();
            if (status >= 400) {
                System.err.println("Error posting score, HTTP " + status);
            }

        } catch (Exception e) {
            System.err.println("API postScore failed");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public static Integer getBestScore(int playerId) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(BASE_URL + "/scores/best?playerId=" + playerId);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            if (status != 200) {
                System.err.println("Error fetching best score, HTTP " + status);
                return null;
            }

            String response = readResponse(connection.getInputStream());

            return parseBestScore(response);

        } catch (Exception e) {
            System.err.println("API getBestScore failed");
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) connection.disconnect();
        }
    }

    private static String readResponse(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private static Integer parseBestScore(String json) {
        JSONObject obj = new JSONObject(json);
        return obj.getInt("value");
    }

    private static String extract(String json, String field) {
        String pattern = "\"" + field + "\"";
        int index = json.indexOf(pattern);
        if (index == -1) return null;

        int colon = json.indexOf(":", index);
        int start = json.indexOf("\"", colon + 1);
        int end;

        if (start != -1) {
            end = json.indexOf("\"", start + 1);
            return json.substring(start + 1, end);
        } else {
            // valeur numérique
            start = colon + 1;
            end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            return json.substring(start, end).trim();
        }
    }
}
