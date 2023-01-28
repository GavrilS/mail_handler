package com.mail.factory.models;

import java.util.List;

public interface PopClientInterface {

    List<CheckedEmail> retrieveAndClean(int countMessages, boolean cleanFlagedEmails);

    String summarizeCheckedEmails(List<CheckedEmail> emailList);
}
