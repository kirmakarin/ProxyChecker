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
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String targetUrl = "https://www.instagram.com/";
        List<ProxyDTO> proxies = new ArrayList<ProxyDTO>();
        List<ProxyDTO> brokenProxies = new ArrayList<ProxyDTO>();

        proxies.add(new ProxyDTO("194.183.168.4", "20028", "user7930", "koo5Vahx"));

        //toDo: add logic for fetch proxies from txt file
        for (ProxyDTO proxy : proxies) {
            //be informed that if checkProxy return false
            // it is not necessary that the proxy is not working (disconnection, url target is not correct, etc.)
            if (!checkProxy(proxy, targetUrl)) {
                brokenProxies.add(proxy);
            }
        }
        if (!brokenProxies.isEmpty()) {
            int workingProxyCounter = proxies.size() - brokenProxies.size();
            printResult(workingProxyCounter, proxies.size(), brokenProxies);
        } else {
            System.out.println("100% of proxies are working");
        }


    }

    private static Boolean checkProxy(ProxyDTO proxy, String targetUrl) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(new AuthScope(proxy.getHost(), Integer.parseInt(proxy.getPort())),
                new UsernamePasswordCredentials(proxy.getLogin(), proxy.getPassword()));

        HttpClientBuilder clientBuilder = HttpClients.custom();
        clientBuilder = clientBuilder.setDefaultCredentialsProvider(credentialsProvider);

        CloseableHttpClient httpClient = clientBuilder.build();
        //toDo: Add opportunity to select default protocol
        HttpHost proxyHost = new HttpHost(proxy.getHost(), Integer.parseInt(proxy.getPort()),
                proxy.getProtocol() != null ? proxy.getProtocol() : "http");

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        requestConfigBuilder = requestConfigBuilder.setProxy(proxyHost);
        RequestConfig config = requestConfigBuilder.build();

        //toDo: Add opportunity to select target
        HttpGet httpGet = new HttpGet(targetUrl);
        httpGet.setConfig(config);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            //toDO:  add logic for another response code
            if (httpResponse.getStatusLine().getStatusCode() == 200) { //toDo: take away const to interface
                httpClient.close();
                return true;
            } else {
                httpClient.close();
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void printResult(int workingProxyCounter, int numberOfProxy, List<ProxyDTO> brokenProxies) {
        System.out.println(workingProxyCounter / numberOfProxy * 100 + "% of working proxy");
        brokenProxies.forEach(proxy -> System.out.println(proxy.toString()));
    }

}
