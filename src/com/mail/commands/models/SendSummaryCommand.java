package com.mail.commands.models;

import com.mail.commands.repository.TempState;
import com.mail.commands.utilities.CommandUtility;
import com.mail.factory.constructors.SMTPConstructorImpl;
import com.mail.factory.constructors.SMTPConstructorInterface;
import com.mail.factory.models.EmailServerConnector;
import com.mail.factory.models.MessageTemplate;
import com.mail.factory.models.SMTPClientInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


public class SendSummaryCommand implements CommandInterface {


    public SendSummaryCommand() {
        // Default constructor
    }

    public void executeCommand(TempState state) {
        EmailServerConnector connector = CommandUtility.chooseConnector(state.getCurrentConnectors());
        if (connector == null) {
            System.out.println("No connector was chosen! Please try again...");
        }

        SMTPConstructorInterface smtpConstructor = new SMTPConstructorImpl();
        SMTPClientInterface smtpClient = smtpConstructor.returnSMTPClient(connector);
        MessageTemplate message = chooseMessageTemplate(state.getCurrentMessages());
        if (smtpClient == null) {
            System.out.println("The smtp client wasn't set successfully! Please try again...");
        } else if (message == null) {
            System.out.println("No message template was selected! Please try again...");
        }
        setMessageText(message, state);
        smtpClient.sendSummary(message);
        System.out.println("Summary sent successfully!");
    }


    private void setMessageText(MessageTemplate message, TempState state) {
        if (message == null) {
            System.out.println("No message was selected!");
        } else if (state.getCurrentEmails() == null || state.getCurrentEmails().size() == 0) {
            System.out.println("There are no emails to be summarized...");
        }
        message.setText(CommandUtility.summarizeCheckedEmails(state.getCurrentEmails()));
    }


    private MessageTemplate chooseMessageTemplate(List<MessageTemplate> messages) {
        if (messages.size() == 0 || messages == null) {
            System.out.println("There are no saved message templates. You must create a message template and then send a summary!");
        }
        int messageId = -1;
        try {
            for (MessageTemplate elem: messages) {
                elem.toString();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please choose which message template you want to use for the summary. Enter the id of the template: ");
            messageId = Integer.parseInt(reader.readLine());
            if (messageId == -1) {
                return null;
            }
            return messages.get(messageId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong! Please try again...");
        }
        return null;
    }

    public void executeHelp() {

    }
}
