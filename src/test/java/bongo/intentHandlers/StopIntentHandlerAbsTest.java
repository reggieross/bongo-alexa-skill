package bongo.intentHandlers;

import bongo.intentHandlers.impl.StopIDIntentHandlerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StopIntentHandlerAbsTest {

  @Test
  public void buildSentenceFromPredictions() {
    StopIntentHandlerAbs handler = new StopIDIntentHandlerImpl();
    String actualUtternace = handler.buildSentenceFromPredictions(getMockPredictions());
    String expectedUtternace =
        "There is a Hawkeye Interdorm in 4, 7, 8, and 9 minutes. There is a Blue Route in 4, 7, 8, and 9 minutes.";
    assertEquals(expectedUtternace, actualUtternace);
  }

  public Map<String, List<String>> getMockPredictions() {
    Map<String, List<String>> predictions = new HashMap<>();
    List<String> times = new ArrayList<>();
    times.add("4");
    times.add("7");
    times.add("8");
    times.add("9");
    predictions.put("Hawkeye Interdorm", times);
    predictions.put("Blue Route", times);
    return  predictions;
  }
}