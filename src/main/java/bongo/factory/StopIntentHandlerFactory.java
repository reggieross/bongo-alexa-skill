package bongo.factory;

import bongo.intentHandlers.StopIntentHandler;
import bongo.intentHandlers.impl.StopIDIntentHandlerImpl;
import bongo.intentHandlers.impl.StopNameIntentHandlerImpl;
import com.amazon.speech.slu.Intent;

public class StopIntentHandlerFactory {
  public StopIntentHandler r(Intent intent) {
    if (intent.getName().equals("StopId")) {
      return new StopIDIntentHandlerImpl();
    } else if (intent.getName().equals("StopName")) {
      return new StopNameIntentHandlerImpl();
    } else {
      return null;
    }
  }
}
