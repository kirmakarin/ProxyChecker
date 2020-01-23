package su.netbox;

public class ProxyDTO {
    String host;
    String port;
    String login;
    String password;
    String protocol;

    public ProxyDTO(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public ProxyDTO(String host, String port, String login, String password) {
        this.host = host;
        this.port = port;
        this.login = login;
        this.password = password;
    }

    public ProxyDTO(String host, String port, String login, String password, String protocol) {
        this.host = host;
        this.port = port;
        this.login = login;
        this.password = password;
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return host + ":" + port + ":" + login + ":" + password;
    }
}
