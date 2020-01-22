package su.netbox;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(new AuthScope("194.183.168.4", 20028), new
                UsernamePasswordCredentials("user7930", "koo5Vahx"));

        HttpClientBuilder clientBuilder = HttpClients.custom();
        clientBuilder = clientBuilder.setDefaultCredentialsProvider(credentialsProvider);

        CloseableHttpClient httpClient = clientBuilder.build();
        HttpHost targetHost = new HttpHost("https://www.instagram.com/1rich_dad1/", 80, "http");
        HttpHost proxyHost = new HttpHost("194.183.168.4", 20028, "http");

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        requestConfigBuilder = requestConfigBuilder.setProxy(proxyHost);
        RequestConfig config = requestConfigBuilder.build();

        HttpGet httpGet = new HttpGet("https://www.instagram.com/1rich_dad1/");
        httpGet.setConfig(config);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            System.out.println(httpResponse.getStatusLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
