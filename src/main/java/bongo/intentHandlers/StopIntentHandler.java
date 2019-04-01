package bongo.intentHandlers;

import com.amazon.speech.slu.Intent;

public interface StopIntentHandler {
  String getPredictionsForIntent(Intent intent);
}
