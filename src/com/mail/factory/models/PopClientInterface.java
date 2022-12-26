package com.mail.factory.models;

import javax.mail.Message;
import java.util.ArrayList;

public interface PopClientInterface {

    ArrayList<Message> retrieveAndClean(int countMessages, boolean cleanFlagedEmails);

    String makeSummaryPerDay();
}
