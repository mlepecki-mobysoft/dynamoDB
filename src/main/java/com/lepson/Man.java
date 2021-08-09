package com.lepson;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Man {
    public static void main(String[] args) {

        AmazonDynamoDB amazonDynamoDBClient = AmazonDynamoDBClient.builder()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("",
                                "")
                )).build();



        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDBClient);

//        createTable(dynamoDB);

        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDBClient);

//        ProductCatalogItem item = new ProductCatalogItem();
//        item.setId("103");
//        item.setTitle("Book 102 Title");
//        item.setISBN("222-2222222222");
//        item.setBookAuthors(new HashSet<String>(Arrays.asList("Author 1", "Author 2")));
//        item.setSomeProp("Test");
//        item.setCar(new Car("asd","kop"));
//        item.setName("Polonez");
//
//        mapper.save(item);

        ProductCatalogItem load = mapper.load(ProductCatalogItem.class, "102");

        System.out.println(load);


//
//        List<String> tableNames = amazonDynamoDBClient.listTables().getTableNames();
//        tableNames.forEach(System.out::println);
//        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDBClient);
//        Table table = dynamoDB.getTable("users");
//
//        System.out.println(table.getItem(new PrimaryKey("id", "123")).toJSONPretty());

    }

    private static void createTable(DynamoDB amazonDynamoDBClient) {

        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("S"));

        List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH)); // Partition

        CreateTableRequest request = new CreateTableRequest().withTableName("ProductCatalog").withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions).withProvisionedThroughput(
                        new ProvisionedThroughput(1L,1L)
                );

        amazonDynamoDBClient.createTable(request);
    }
}
