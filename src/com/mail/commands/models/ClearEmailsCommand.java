package com.mail.commands.models;

import com.mail.commands.repository.TempState;
import com.mail.commands.utilities.CommandUtility;
import com.mail.factory.constructors.PopConstructorImpl;
import com.mail.factory.constructors.PopConstructorInterface;
import com.mail.factory.models.CheckedEmail;
import com.mail.factory.models.EmailServerConnector;
import com.mail.factory.models.PopClientInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ClearEmailsCommand implements CommandInterface {
    private boolean clearEmails;


    public ClearEmailsCommand(boolean clearEmails) {
        setClearEmails(clearEmails);
    }

    public void executeCommand(TempState state) {
        List<CheckedEmail> emails;
        EmailServerConnector connector = CommandUtility.chooseConnector(state.getCurrentConnectors());
        if (connector == null) {
            System.out.println("Something went wrong! Please try again...");
        }

        PopConstructorInterface popConstructor = new PopConstructorImpl();
        PopClientInterface popClient = popConstructor.returnPopClient(connector);
        int messageCountToClear = getMessageCountToClear();
        emails = popClient.retrieveAndClean(messageCountToClear, this.clearEmails);

        if (emails == null) {
            System.out.println("Something went wrong! Please try again...");
        }
        for (CheckedEmail elem: emails) {
            state.getCurrentEmails().add(elem);
        }
        if (this.clearEmails) {
            System.out.println("Emails retrieved and cleared successfully!");
        } else {
            System.out.println("Emails retrieved successfully!");
        }
    }


    private int getMessageCountToClear() {
        int messageCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please specify how many messages do you want to clear:");
            messageCount = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            System.out.println("Something went wrong! Please try again...");
            e.printStackTrace();
        }
        return messageCount;
    }


    public void executeHelp() {

    }

    private void setClearEmails(boolean clearEmails) {
        this.clearEmails = clearEmails;
    }

    private boolean getClearEmails() {
        return this.clearEmails;
    }
}
