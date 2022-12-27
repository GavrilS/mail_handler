package com.mail.factory.models;

import com.mail.db.models.CheckedEmails;

import javax.mail.Message;
import java.util.ArrayList;

public interface PopClientInterface {

    ArrayList<CheckedEmails> retrieveAndClean(int countMessages, boolean cleanFlagedEmails, String platform);

    String makeSummaryPerDay();
}
