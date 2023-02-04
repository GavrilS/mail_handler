package com.mail.commands.models;

import com.mail.commands.repository.TempState;
import com.mail.factory.models.EmailServerConnector;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateConnectorCommand implements CommandInterface {
    private boolean loadConnectorFromFile;
    private static final String DEFAULT_PATH = "src/files/email-connectors.txt";


    public CreateConnectorCommand(boolean loadConnectorFromFile) {
        this.loadConnectorFromFile = loadConnectorFromFile;
    }


    public void executeCommand(TempState currentState) {
        List<String> connectorData;
        if (loadConnectorFromFile) {
            connectorData = loadFromFile();
        } else {
            connectorData = readFromConsole();
        }
        if (connectorData == null) {
            System.out.println("Connector data was not passed! Please try again...");
        }
//        System.out.println(connectorData);
        for (String elem: connectorData) {
            String[] data = elem.split(", ");
            EmailServerConnector emailConnector = new EmailServerConnector(
                    data[0], data[1], data[2], data[3], data[4], Boolean.valueOf(data[5])
            );
            currentState.getCurrentConnectors().add(emailConnector);
        }
        System.out.println("Connector added!");
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
        System.out.println("Please provide email connector data in the format: " +
                "email_name, password, email_platform, email_server, email_port, secure_connection(true/false)");
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
        System.out.println("You can either create a connector by passing data from the command line or by loading the data" +
                "from a file!\n" +
                "Command to load data from the command line: 'create connector'!\n" +
                "Format of the data: email_address, password, email_platform, email_server, email_port, secure_connection!\n" +
                "Example smtp: user@gmail.com, user1234, gmail.com, smtp.gmail.com, 587, true\n" +
                "Example pop: user@gmail.com, user1234, gmail.com, pop.gmail.com, 995, true");
        System.out.println("Command to load data from a file: 'load connector from file'\n" +
                "When promped provide the file path to the text file with the data.\n" +
                "If not provided a default path will be used: <src/files/email-connectors.txt>\n" +
                "Data should be in the format: email_address, password, email_platform, email_server, email_port, secure_connection!\n" +
                "If there are multiple lines, each line would be used to create a connector if the data is in the right format!");
    }
}
