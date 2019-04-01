package bongo.speechlet;

import bongo.factory.StopIntentHandlerFactory;
import bongo.intentHandlers.StopIntentHandler;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BongoSpeechlet implements Speechlet {

  private static final Logger log = LoggerFactory.getLogger(BongoSpeechlet.class);

  @Override
  public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
    log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
        session.getSessionId());
  }

  @Override
  public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
    log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
        session.getSessionId());
    return speakPlainUtterance("Welcome to Bongo");
  }

  @Override
  public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
    log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
        session.getSessionId());

    Intent intent = request.getIntent();
    String intentName = intent.getName();

    StopIntentHandlerFactory factory = new StopIntentHandlerFactory();
    StopIntentHandler handler = factory.getHandlerForIntent(intent);

    if (handler != null) {
     return speakPlainUtterance(
         handler.getPredictionsForIntent(intent)
     );
    } else if ("AMAZON.StopIntent".equals(intentName)) {
      return speakPlainUtterance("Goodbye");
    } else if ("AMAZON.CancelIntent".equals(intentName)) {
      return speakPlainUtterance("Goodbye");
    } else {
      return speakPlainUtterance("Please be patient we are working on features at the moment");
    }
  }

  @Override
  public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
  }

  public SpeechletResponse speakPlainUtterance(String utterance) {
    PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
    outputSpeech.setText(utterance);
    SimpleCard card = new SimpleCard();
    return SpeechletResponse.newTellResponse(outputSpeech);
  }
}
