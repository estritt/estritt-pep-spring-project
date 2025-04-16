package com.example.DTO;

    // I'm running into an issue with patching messages where Jackson won't parse
    // a string value of a request body field with @RequestBody and instead returns a 
    // json-looking string of the whole body, so I am throwing together this DTO to fix that

public class PatchMessageRequest {
    
    // could use javax/jakarta dependencies
    // @NotEmpty(message = "Message cannot be empty") //could easily change to not allow pure whitespace
    // @Size(max = 255, message = "<essage cannot exceed 255 characters")
    private String messageText;

    public String getMessageText() {return this.messageText;}

    public void setMessageText(String messageText) {this.messageText = messageText;}

}