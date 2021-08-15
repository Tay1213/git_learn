package Http;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient01 {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        CloseableHttpResponse response = client.execute(httpGet);
        try {
            HttpEntity entity = response.getEntity();
            if (entity!=null){
                String res = EntityUtils.toString(entity);
                System.out.println(res);
            }
        } finally {
            response.close();
            client.close();
        }
    }
}
