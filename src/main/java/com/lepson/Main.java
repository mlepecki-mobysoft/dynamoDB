package com.lepson;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //1. create Credentials and dynamoDB client
        AmazonDynamoDB amazonDynamoDBClient = AmazonDynamoDBClient.builder()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("",
                                "")
                )).build();

        //2.Create table on DynamoDB site
        createTable(amazonDynamoDBClient);

        //3. Get DynamoDBMapper instance
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);

//        4. Save item in DB
//        ProductCatalogItem item = new ProductCatalogItem();
//        item.setId("102");
//        item.setTitle("Book 102 Title");
//        item.setISBN("222-2222222222");
//        item.setBookAuthors(new HashSet<String>(Arrays.asList("Author 1", "Author 2")));
//        item.setSomeProp("Test");
//        item.setCar(new Car("asd","kop"));
//        item.setName("Polonez");
//
//        mapper.save(item);

        //5. Load item from DB
        ProductCatalogItem load = mapper.load(ProductCatalogItem.class, "102");

        System.out.println(load);

    }

    private static void createTable(AmazonDynamoDB amazonDynamoDBClient) {

        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("S"));

        List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH)); // Partition

        CreateTableRequest request = new CreateTableRequest().withTableName("ProductCatalog").withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions).withProvisionedThroughput(
                        new ProvisionedThroughput(1L, 1L)
                );

        amazonDynamoDBClient.createTable(request);
    }
}
