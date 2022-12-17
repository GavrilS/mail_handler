1. Issue with security certificates:
    - the issue was resovled by updating the 'java.security' file in this path -> jdk-17.0.5/conf/security/java.security.
    - located line with 'jdk.tls.disabledAlgorithms=' and commented it along with the following line

2. Exception: 'Exception in thread "main" java.lang.NoClassDefFoundError: javax/activation/DataSource':
    - the issue was caused due to missing lib -> activation.jar; jav needs to be added to the list of External Libraries for the project

3. Exception when running for gmail: 'javax.mail.AuthenticationFailedException: [AUTH] Username and password not accepted.'