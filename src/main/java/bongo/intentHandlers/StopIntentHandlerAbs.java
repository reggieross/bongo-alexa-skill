package bongo.intentHandlers;

import bongo.api.BongoApi;
import bongo.api.impl.BongoApiImpl;

import java.util.List;
import java.util.Map;

public abstract class StopIntentHandlerAbs implements StopIntentHandler {
  BongoApi api = new BongoApiImpl();

  public Map<String, List<String>> getPredictionsForStopId(String stopId) {
    return api.getPredictionsForStopId(stopId);
  }

  public String buildSentenceFromPredictions(Map<String, List<String>> predictions) {
    String predictionUtterance = "";
    for(Map.Entry<String, List<String>> entry : predictions.entrySet()) {
      predictionUtterance += "There is a " + entry.getKey() + " in " +
          concatenateMinuteValuesToString(entry.getValue()) + ". ";
    }

    return predictionUtterance;
  }

  private String concatenateMinuteValuesToString(List<String> minuteValues) {
    if (minuteValues.size() == 1) {
      return minuteValues.get(0) + " minutes";
    } else if (minuteValues.size() == 2) {
      return minuteValues.get(0) + " and " + minuteValues.get(1) + " minutes";
    }

    String concatenation = "";
    for(Integer i = 0; i < minuteValues.size() - 1; i++) {
      concatenation += (minuteValues.get(i) + ", ");
    }

    concatenation += ("and " + minuteValues.get(minuteValues.size() - 1) + " minutes");
    return concatenation;
  }
}
