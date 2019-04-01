package bongo.factory;

import bongo.intentHandlers.StopIntentHandler;
import bongo.intentHandlers.impl.StopIDIntentHandlerImpl;
import bongo.intentHandlers.impl.StopNameIntentHandlerImpl;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.slu.entityresolution.Resolution;
import com.amazon.speech.slu.entityresolution.Resolutions;
import com.amazon.speech.slu.entityresolution.Value;
import com.amazon.speech.slu.entityresolution.ValueWrapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StopIntentHandlerFactoryTest {

  @Test
  public void getHandlerForIntentWithStopID() {
    StopIntentHandlerFactory factory = new StopIntentHandlerFactory();
    StopIntentHandler handler = factory.getHandlerForIntent(getStopIdIntent());
    assertEquals(StopIDIntentHandlerImpl.class, handler.getClass());
  }

  @Test
  public void getHandlerForIntentWithStopName() {
    StopIntentHandlerFactory factory = new StopIntentHandlerFactory();
    StopIntentHandler handler = factory.getHandlerForIntent(getStopNameIntent());
    assertEquals(StopNameIntentHandlerImpl.class, handler.getClass());
  }

  public Intent getStopIdIntent() {
    Slot mayfloweSlot = Slot.builder()
        .withName("stop_id")
        .withValue("1000")
        .build();

    return Intent.builder()
        .withSlot(mayfloweSlot)
        .withName("StopId")
        .build();

  }
  public Intent getStopNameIntent() {
    Value value = Value.builder()
        .withId("1000")
        .withName("Mayflower Hall")
        .build();

    ValueWrapper valuewrapper = ValueWrapper.builder()
        .withValue(value)
        .build();

    Resolution resolution = Resolution.builder()
        .withValue(valuewrapper)
        .build();

    List<Resolution> resolutions = new ArrayList<>();
    resolutions.add(resolution);

    Slot mayfloweSlot = Slot.builder()
        .withName("stop_name")
        .withValue("Mayflower Hall")
        .withResolutions(Resolutions.builder().withResolutionsPerAuthority(resolutions).build())
        .build();

    return Intent.builder()
        .withSlot(mayfloweSlot)
        .withName("StopName")
        .build();

  }
}