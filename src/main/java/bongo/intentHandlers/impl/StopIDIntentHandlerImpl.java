package bongo.intentHandlers.impl;

import bongo.intentHandlers.StopIntentHandlerAbs;
import com.amazon.speech.slu.Intent;

import java.util.Map;

public class StopIDIntentHandlerImpl extends StopIntentHandlerAbs {

  @Override
  public String getPredictionsForIntent(Intent intent) {
    String stopID = intent.getSlot("stop_id")
        .getValue();

   return buildSentenceFromPredictions(
       getPredictionsForStopId(stopID)
   );
  }
}
