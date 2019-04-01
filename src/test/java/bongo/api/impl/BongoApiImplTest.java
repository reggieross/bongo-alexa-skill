package bongo.api.impl;

import bongo.api.BongoApi;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.tools.doclint.Entity.times;
import static org.junit.Assert.*;

public class BongoApiImplTest {
  BongoApiImpl api = Mockito.spy(new BongoApiImpl());

  @Test
  public void getPredictionsForStopId() throws IOException{
    Mockito.when(api.submitRequest("1000")).thenReturn(getMockResponse());
    Map<String, List<String>> predictions = api.getPredictionsForStopId("1000");

    Map<String, List<String>> actualValues = new HashMap<>();
    List<String> times = new ArrayList<>();
    times.add("4");
    times.add("33");

    actualValues.put("Hawkeye Interdorm", times);

    assertEquals(actualValues, predictions);
  }

  public String getMockResponse() {
    return "[{\"routeid\":2372,\"name\":\"Hawkeye Interdorm\"," +
        "\"shortname\":\"hawkdorm\",\"busid\":2950,\"busnumber\":\"95\"," +
        "\"seconds\":186,\"minutes\":4,\"agency\":\"uiowa\",\"agencyname\":" +
        "\"Cambus\"},{\"routeid\":2372,\"name\":\"Hawkeye Interdorm\",\"shortname" +
        "\":\"hawkdorm\",\"busid\":2924,\"busnumber\":\"108\",\"seconds\":1968,\"minutes" +
        "\":33,\"agency\":\"uiowa\",\"agencyname\":\"Cambus\"}]";
  }
}