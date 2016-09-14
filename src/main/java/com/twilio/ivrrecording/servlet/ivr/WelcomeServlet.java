package com.twilio.ivrrecording.servlet.ivr;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.ivrrecording.servlet.WebAppServlet;
import com.twilio.sdk.verbs.Gather;
import com.twilio.sdk.verbs.Play;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

public class WelcomeServlet extends WebAppServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    Gather gather = new Gather();
    gather.setAction("/menu/show");
    gather.setNumDigits(1);

    Play play = new Play("http://howtodocs.s3.amazonaws.com/et-phone.mp3");
    play.setLoop(3);

    try {
      gather.append(play);
    } catch (TwiMLException e) {
      e.printStackTrace();
    }

    TwiMLResponse twiMLResponse = new TwiMLResponse();
    try {
      twiMLResponse.append(gather);
    } catch (TwiMLException e) {
      e.printStackTrace();
    }

    respondTwiML(response, twiMLResponse);
  }
}
