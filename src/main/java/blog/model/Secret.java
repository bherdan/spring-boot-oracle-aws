package blog.model;

public class Secret {
    private static String username;
    private static String password;
    private static String engine;
    private static String host;
    private static int port;
    private static String dbname;
    private static String dbInstanceIdentifier;

    private Secret(String username, String password, String engine, String host, int port, String dbname, String dbInstanceIdentifier){
        this.username = username;
        this.password = password;
        this.engine = engine;
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.dbInstanceIdentifier = dbInstanceIdentifier;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) { this.password = password; }

    public String getEngine() {
        return engine;
    }
    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }
    public void setPort(int port) { this.port = port; }

    public String getDbname() { return dbname; }
    public void setDbname(String host) {
        this.dbname = dbname;
    }

    public String getDbInstanceIdentifier() {
        return dbInstanceIdentifier;
    }
    public void setDbInstanceIdentifier(String dbInstanceIdentifier) { this.dbInstanceIdentifier = dbInstanceIdentifier; }

    public void print(){
        String formattedString = String.format("Username: %s\n Password: %s\n Engine: {%s\n " +
                "Host: %s\n Port: %d\n DBName: %s\n Identifier: %s\n",
                username, password, engine, host, port, dbname, dbInstanceIdentifier);
        System.out.println("=================");
        System.out.println(formattedString);
        System.out.println("=================");
    }
}