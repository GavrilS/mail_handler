package com.mail.factory.models;

import com.mail.db.models.CheckedEmail;

import java.util.List;

public interface PopClientInterface {

    List<CheckedEmail> retrieveAndClean(int countMessages, boolean cleanFlagedEmails);

    String summarizeCheckedEmails(List<CheckedEmail> emailList);
}
