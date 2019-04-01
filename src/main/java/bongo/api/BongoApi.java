package bongo.api;

import java.util.List;
import java.util.Map;

public interface BongoApi {
  String getApiUrl(String stopId);
  Map<String, List<String>> getPredictionsForStopId(String stopId);
}
