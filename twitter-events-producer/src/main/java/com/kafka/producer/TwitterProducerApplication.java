package com.kafka.producer;

import com.kafka.producer.producer.TwitterEventProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecordBuilder;

@SpringBootApplication
public class TwitterProducerApplication implements CommandLineRunner {

	private TwitterEventProducer twitterEventProducer;

	public TwitterProducerApplication(TwitterEventProducer twitterEventProducer) {
		this.twitterEventProducer = twitterEventProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(TwitterProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//twitterEventProducer.sendLibraryEventAsyncUsingProducerRecord();

		// step 0: define schema
		Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse("{\n" +
				"  \"type\": \"record\",\n" +
				"  \"namespace\": \"com.example\",\n" +
				"  \"name\": \"Customer\",\n" +
				"  \"doc\": \"Avro schema for customer\",\n" +
				"  \"fields\": [\n" +
				"    {\n" +
				"      \"name\": \"first_name\",\n" +
				"      \"type\": \"string\",\n" +
				"      \"doc\": \"First Name of customer\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"name\": \"last_name\",\n" +
				"      \"type\": \"string\",\n" +
				"      \"doc\": \"Last Name of customer\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"name\": \"age\",\n" +
				"      \"type\": \"int\",\n" +
				"      \"doc\": \"Age of customer\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"name\": \"height\",\n" +
				"      \"type\": \"float\",\n" +
				"      \"doc\": \"Height of customer\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"name\": \"weight\",\n" +
				"      \"type\": \"float\",\n" +
				"      \"doc\": \"Weight of customer\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"name\": \"automated_email\",\n" +
				"      \"type\": \"boolean\",\n" +
				"      \"doc\": \"Automated Email of customer\"\n" +
				"    }\n" +
				"\n" +
				"  ]\n" +
				"}\n");

		// step 1: create a generic record
		GenericRecordBuilder customBuilder = new GenericRecordBuilder(schema);
		customBuilder.set("first_name","John");
		customBuilder.set("last_name","Doe");
		customBuilder.set("age",25);
		customBuilder.set("height",170f);
		customBuilder.set("weight",80.5);
		customBuilder.set("automated_email",false);
		GenericData.Record customer = customBuilder.build();

		System.out.println(customer);

		// step 2: write that generic record to a file

		// step 3: read that generic record from file

		// step 4: interpret as a generic record
	}
}
