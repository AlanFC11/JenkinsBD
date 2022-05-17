package com.itesm.azul.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Note {
    private int noteID;
    private String staffName;
    private String text;
    private String timestamp;


    @DynamoDbPartitionKey
    @DynamoDbAttribute("noteID")
    public int getNoteID() {
        return noteID;
    }
    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    @DynamoDbAttribute("staffName")
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }


    @DynamoDbAttribute("text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @DynamoDbAttribute("timestamp")
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}