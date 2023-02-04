package com.mail.commands.models;

import com.mail.commands.repository.TempState;
import com.mail.factory.models.MessageTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateMessageCommand implements CommandInterface {
    private boolean loadMessageFromFile;
    private static final String DEFAULT_PATH = "src/files/message-templates.txt";


    public CreateMessageCommand(boolean loadMessageFromFile) {
        this.loadMessageFromFile = loadMessageFromFile;
    }


    public void executeCommand(TempState currentState) {
        List<String> messageTemplates;
        if (this.loadMessageFromFile) {
            messageTemplates = loadFromFile();
        } else {
            messageTemplates = readFromConsole();
        }
        if (messageTemplates == null) {
            System.out.println("Connector data was not passed! Please try again...");
        }
//        System.out.println(messageTemplates);
        for (String elem: messageTemplates) {
            String[] data = elem.split(", ");
            MessageTemplate message = new MessageTemplate(
                    data[0], data[1], data[2]
            );
            currentState.getCurrentMessages().add(message);
        }
        System.out.println("Message template added!");
//        for (MessageTemplate elem: currentState.getCurrentMessages()) {
//            elem.toString();
//        }
    }

    private List<String> loadFromFile() {
        List<String> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please provide the path to the file:");
        try {
            String path = reader.readLine();
            if (path.equals("")) {
                path = DEFAULT_PATH;
            }
            File fileObj = new File(path);
            Scanner scanner = new Scanner(fileObj);
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem, please try again!");
        }
        return data;
    }

    private List<String> readFromConsole() {
        List<String> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please provide message template data in the format: " +
                "sender, recipient, subject");
        try {
            String input = reader.readLine();
            data.add(input);
        } catch (IOException e) {
            System.out.println("Something went wrong! Please try again...");
            e.printStackTrace();
        }
        return data;
    }

    public void executeHelp() {
        System.out.println("You can either create a message template by passing data from the command line or by loading the data" +
                "from a file!\n" +
                "Command to load data from the command line: 'create message template'!\n" +
                "Format of the data: sender, recipient, subject!\n" +
                "Example: user@gmail.com, other_user@gmail.com, This is a test email!\n");
        System.out.println("Command to load data from a file: 'load message templates from file'\n" +
                "When promped provide the file path to the text file with the data.\n" +
                "If not provided a default path will be used: <src/files/message-templates.txt>\n" +
                "Data should be in the format: sender, recipient, subject!\n" +
                "If there are multiple lines, each line would be used to create a connector if the data is in the right format!");
    }
}
