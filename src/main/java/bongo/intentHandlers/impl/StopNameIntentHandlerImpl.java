package bongo.intentHandlers.impl;

import bongo.intentHandlers.StopIntentHandlerAbs;
import com.amazon.speech.slu.Intent;

public class StopNameIntentHandlerImpl extends StopIntentHandlerAbs {
  @Override
  public String getPredictionsForIntent(Intent intent) {
    String stopID = getSlotIdFromIntent(intent);
    return buildSentenceFromPredictions(
        getPredictionsForStopId(stopID)
    );
  }


  public String getSlotIdFromIntent(Intent intent) {
    return intent.getSlot("stop_name")
        .getResolutions()
        .getResolutionsPerAuthority()
        .get(0)
        .getValueWrapperAtIndex(0)
        .getValue()
        .getId();
  }
}
