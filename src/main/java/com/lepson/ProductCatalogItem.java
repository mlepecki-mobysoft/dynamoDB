package com.lepson;

import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.ToString;

@DynamoDBTable(tableName="ProductCatalog")
@ToString
public class ProductCatalogItem {

    private String id;
    private String title;
    private String ISBN;
    private Set<String> bookAuthors;
    private String someProp;
    private String name;

    @DynamoDBAttribute(attributeName="Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Car car;

    @DynamoDBHashKey(attributeName="Id")
    public String getId() { return id; }
    public void setId(String id) {this.id = id; }

    @DynamoDBAttribute(attributeName="Title")
    public String getTitle() {return title; }
    public void setTitle(String title) { this.title = title; }

    @DynamoDBAttribute(attributeName="ISBN")
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    @DynamoDBAttribute(attributeName="Authors")
    public Set<String> getBookAuthors() { return bookAuthors; }
    public void setBookAuthors(Set<String> bookAuthors) { this.bookAuthors = bookAuthors; }

    @DynamoDBAttribute(attributeName = "Car")
    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    @DynamoDBIgnore
    public String getSomeProp() { return someProp; }
    public void setSomeProp(String someProp) { this.someProp = someProp; }
}
