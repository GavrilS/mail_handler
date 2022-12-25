package com.mail_factory_models;

import javax.mail.Message;
import java.util.ArrayList;

public interface PopClientInterface {

    ArrayList<Message> retrieveAndClean(int countMessages);

    String makeSummaryPerDay();
}
