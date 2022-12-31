package com.mail.factory.models;

import com.mail.db.models.CheckedEmails;

import java.util.List;

public interface PopClientInterface {

    List<CheckedEmails> retrieveAndClean(int countMessages, boolean cleanFlagedEmails);

    String summarizeCheckedEmails(List<CheckedEmails> emailList);
}
