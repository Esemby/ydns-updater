package business.model;

import persistence.YdnsConfigFile;

import java.util.List;
import java.util.Optional;

public class YdnsConfig {

    private String api;
    private String username;
    private String secret;
    private List<String> domains;

    public String getApi() {
        return api;
    }

    public String getUsername() {
        return username;
    }

    public String getSecret() {
        return secret;
    }

    public List<String> getDomains() {
        return domains;
    }

    private YdnsConfig() {
    }

    private YdnsConfig(String api, String username, String secret, List<String> domains) {
        this.api = api;
        this.username = username;
        this.secret = secret;
        this.domains = domains;
    }

    public static YdnsConfig NewYdnsConfigBuilder(String api, String username, String secret, List<String> domains){
        return new YdnsConfig(api,username,secret,domains);
    }

    public static Optional<YdnsConfig> fromFile() {
        return YdnsConfigFile.fromFile();
    }

    public void toFile(){
        YdnsConfigFile.toFile(this);
    }
}
