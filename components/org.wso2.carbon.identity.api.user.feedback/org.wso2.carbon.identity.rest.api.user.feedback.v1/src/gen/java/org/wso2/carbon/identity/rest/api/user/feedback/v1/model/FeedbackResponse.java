/*
* Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.wso2.carbon.identity.rest.api.user.feedback.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.FeedbackResponseMeta;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class FeedbackResponse  {
  
    private FeedbackResponseMeta meta;
    private String id;
    private String message;
    private String email;
    private String contactNo;
    private List<String> tags = null;


    /**
    **/
    public FeedbackResponse meta(FeedbackResponseMeta meta) {

        this.meta = meta;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("meta")
    @Valid
    public FeedbackResponseMeta getMeta() {
        return meta;
    }
    public void setMeta(FeedbackResponseMeta meta) {
        this.meta = meta;
    }

    /**
    **/
    public FeedbackResponse id(String id) {

        this.id = id;
        return this;
    }
    
    @ApiModelProperty(example = "7bac6a86-1f21-4937-9fb1-5be4a93ef469", value = "")
    @JsonProperty("id")
    @Valid
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    /**
    **/
    public FeedbackResponse message(String message) {

        this.message = message;
        return this;
    }
    
    @ApiModelProperty(example = "This is a sample feedback", value = "")
    @JsonProperty("message")
    @Valid
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    /**
    **/
    public FeedbackResponse email(String email) {

        this.email = email;
        return this;
    }
    
    @ApiModelProperty(example = "kim@gmail.com", value = "")
    @JsonProperty("email")
    @Valid
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    /**
    **/
    public FeedbackResponse contactNo(String contactNo) {

        this.contactNo = contactNo;
        return this;
    }
    
    @ApiModelProperty(example = "123456789", value = "")
    @JsonProperty("contactNo")
    @Valid
    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
    **/
    public FeedbackResponse tags(List<String> tags) {

        this.tags = tags;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("tags")
    @Valid
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public FeedbackResponse addTagsItem(String tagsItem) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tagsItem);
        return this;
    }

    

    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeedbackResponse feedbackResponse = (FeedbackResponse) o;
        return Objects.equals(this.meta, feedbackResponse.meta) &&
            Objects.equals(this.id, feedbackResponse.id) &&
            Objects.equals(this.message, feedbackResponse.message) &&
            Objects.equals(this.email, feedbackResponse.email) &&
            Objects.equals(this.contactNo, feedbackResponse.contactNo) &&
            Objects.equals(this.tags, feedbackResponse.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meta, id, message, email, contactNo, tags);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class FeedbackResponse {\n");
        
        sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    contactNo: ").append(toIndentedString(contactNo)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
    * Convert the given object to string with each line indented by 4 spaces
    * (except the first line).
    */
    private String toIndentedString(java.lang.Object o) {

        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n");
    }
}

