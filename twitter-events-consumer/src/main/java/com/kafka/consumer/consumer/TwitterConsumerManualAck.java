package com.kafka.consumer.consumer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TwitterConsumerManualAck implements AcknowledgingMessageListener<Integer, String> {

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    private RestHighLevelClient client;

    public TwitterConsumerManualAck(RestHighLevelClient client) {
        this.client = client;
    }


    /*private ElasticSearchRestClient elasticSearchRestClient;

    public TwitterConsumerManualAck(ElasticSearchRestClient elasticSearchRestClient) {
        this.elasticSearchRestClient = elasticSearchRestClient;
    }*/

    @SneakyThrows
    @Override
    @KafkaListener(topics = {"twitter-events"})
    public void onMessage(ConsumerRecord consumerRecord, Acknowledgment acknowledgment) {
        log.info("ConsumerRecord : {}",consumerRecord);

       // RestHighLevelClient client = createClient();

        String jsonString = "{\"name\": \"Rahul\"}";
        IndexRequest indexRequest = new IndexRequest("twitter", "tweets").source(jsonString, XContentType.JSON);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        String id = indexResponse.getId();
        log.info("ID: {}", id);

        // close client
        client.close();
        acknowledgment.acknowledge();

    }

    /*public RestHighLevelClient createClient() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        RestClientBuilder builder = RestClient.builder(
                new HttpHost("https://i-o-optimized-deployment-9d7656.es.us-west1.gcp.cloud.es.io", 443, "https"));
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        });

        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }*/
}
