package bongo.speechlet;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public class BongoSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
  private static final Set<String> supportedApplicationIds;

  static {
    supportedApplicationIds = new HashSet<String>();
    supportedApplicationIds.add("amzn1.ask.skill.f0a68dec-cf24-4c6f-8f44-fef58de55084");
  }

  public BongoSpeechletRequestStreamHandler() {
    super(new BongoSpeechlet(), supportedApplicationIds);
  }

  public BongoSpeechletRequestStreamHandler(Speechlet speechlet, Set<String> supportedApplicationIds) {
    super(speechlet, supportedApplicationIds);
  }
}
