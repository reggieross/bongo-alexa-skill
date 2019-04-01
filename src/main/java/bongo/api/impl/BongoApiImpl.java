package bongo.api.impl;

import bongo.api.BongoApi;
import bongo.speechlet.BongoSpeechlet;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BongoApiImpl implements BongoApi {
  static final Logger log = LoggerFactory.getLogger(BongoSpeechlet.class);
  private final String API_KEY = "";

  public Map<String, List<String>> getPredictionsForStopId(String stopId) {
    try {
      String response = submitRequest(stopId);
      return parsePredictionsFromString(response);
    } catch (IOException ioe) {
      log.error("There was an HTTP request error to the Bongo API: " + ioe.getMessage());
      return new HashMap();
    }
  }

  public String submitRequest(
      String stopId
  ) throws IOException {
    OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(180, TimeUnit.SECONDS)
        .readTimeout(180, TimeUnit.SECONDS)
        .build();

    Request request = new Request
        .Builder()
        .url(getApiUrl(stopId))
        .get()
        .build();

    Response response =  client.newCall(request).execute();
    return response.body().string();
  }

  private Map<String, List<String>> parsePredictionsFromString(String response) {
    JSONParser parser = new JSONParser();
    Map<String, List<String>> predictionMap = new HashMap<>();
    try {
      JSONArray predictionJson = (JSONArray) parser.parse(response);
      predictionJson.forEach(prediction -> {
        String busRoute = (String)((JSONObject)prediction).get("name");
        Long predictionTime = (Long) ((JSONObject) prediction).get("minutes");

        if(predictionMap.containsKey(busRoute)) {
          predictionMap.get(busRoute).add(predictionTime.toString());
        } else {
          List<String> times = new ArrayList<>();
          times.add(predictionTime.toString());
          predictionMap.put(busRoute, times);
        }
      });
    } catch (Exception e) {
      log.error("There was an error parsing tbe response from bongo: " + e.getMessage());
    }

    return predictionMap;
  }

  @Override
  public String getApiUrl(String stopId) {
    return UriComponentsBuilder.newInstance()
        .scheme("http")
        .host("api.bongo.org")
        .path("predictions/" + stopId)
        .build()
        .toString();
  }
}
