package com.mail.db.models;

public class EmailServerConnector {

    private String username;
    private String password;
    private String platform;
    private String host;
    private String port;
    private boolean secureConnection;

    public EmailServerConnector(String username, String password, String platform, String host, String port,
                                boolean secureConnection){
        setUsername(username);
        setPassword(password);
        setPlatform(platform);
        setHost(host);
        setPort(port);
        setSecureConnection(secureConnection);
    }

    private void setUsername(String username) {
        if (username == null || username == "") {
            System.out.println("Username cannot be empty or null!");
        }
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    private void setPassword(String password) {
        if (password == null || password == "") {
            System.out.println("Password cannot be empty or null!");
        }
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    private void setPlatform(String platform) {
        if (platform == null || platform == "") {
            System.out.println("Platform cannot be empty or null!");
        }
        this.platform = platform;
    }

    public String getPlatform() {
        return this.platform;
    }

    private void setHost(String host) {
        if (host == null || host == "") {
            System.out.println("Host cannot be empty or null!");
        }
        this.host = host;
    }

    public String getHost() {
        return this.host;
    }

    private void setPort(String port) {
        if (port == null || port == "") {
            System.out.println("Port cannot be empty or null!");
        }
        this.port = port;
    }

    public String getPort() {
        return this.port;
    }

    private void setSecureConnection(boolean secureConnection) {
        this.secureConnection = secureConnection;
    }

    public boolean getSecureConnection() {
        return this.secureConnection;
    }
}
