package org.snakeinc.snake.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api/v1";

    public static void postScore(int value, String snake, int playerId) {
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
              "snake": "%s",
              "playerId": %d
            }
            """.formatted(value, snake, playerId);

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
            start = colon + 1;
            end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            return json.substring(start, end).trim();
        }
    }

    public static Integer createPlayer(String username) {
        HttpURLConnection conn = null;

        try {
            URL url = new URL(BASE_URL + "/players");
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String body = """
        {
          "name": "%s",
          "age": 18
        }
        """.formatted(username);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
            }

            int status = conn.getResponseCode();
            if (status != 200 && status != 201) {
                throw new RuntimeException("HTTP error while creating player: " + status);
            }

            String response = readResponse(conn.getInputStream());
            JSONObject obj = new JSONObject(response);
            return obj.getInt("id");

        } catch (Exception e) {
            throw new RuntimeException("Unable to create player", e);
        } finally {
            if (conn != null) conn.disconnect();
        }
    }


    public static Integer getPlayerIdByUsername(String username) {
        try {
            URL url = new URL(BASE_URL + "/players/username/?username=" + username);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                return null;
            }

            String response = readResponse(conn.getInputStream());
            JSONObject obj = new JSONObject(response);
            return obj.getInt("id");

        } catch (Exception e) {
            return null;
        }
    }


}
