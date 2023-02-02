package com.mail.commands.models;

import com.mail.commands.repository.TempState;
import com.mail.factory.models.EmailServerConnector;

public class CreateConnectorCommand implements CommandInterface {
    private boolean loadConnectorFromFile;


    public CreateConnectorCommand(boolean loadConnectorFromFile) {
        this.loadConnectorFromFile = loadConnectorFromFile;
    }


    public void executeCommand(TempState currentState) {
        String connectorData;
        if (loadConnectorFromFile) {
            connectorData = loadFromFile();
        } else {
            connectorData = readFromConsole();
        }
        if (connectorData == null) {
            System.out.println("Connector data was not passed! Please try again...");
        }
        String[] connectorArray = connectorData.split(System.lineSeparator());
        for (String connector: connectorArray) {
            String[] connParts = connector.split(", ");
            EmailServerConnector emailConnector = new EmailServerConnector(
                    connParts[0], connParts[1], connParts[2], connParts[3], connParts[4], Boolean.valueOf(connParts[5])
            );
            currentState.getCurrentConnectors().add(emailConnector);
        }
        System.out.println("Connector added!");
    }

    private String loadFromFile() {

    }

    private String readFromConsole() {

    }
}
