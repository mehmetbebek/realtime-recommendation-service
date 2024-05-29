package com.hepsiburada.realtime_recommendation_service_view_producer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ViewProducerAppApplication implements ApplicationRunner {

	@Value("${topic.name:views}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ViewProducerAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Map<String, Object>> productViews = readProductViewsFromFile();

		for (Map<String, Object> productView : productViews) {
			productView.put("timestamp", Instant.now().toEpochMilli());

			String messageValue = new ObjectMapper().writeValueAsString(productView);

			ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, messageValue);

			kafkaTemplate.send(producerRecord);
		}
	}


	private List<Map<String, Object>> readProductViewsFromFile() throws IOException {
		File file= ResourceUtils.getFile("classpath:product-views.json");

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(file, new TypeReference<>() {});
	}
}
