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
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class FeedbackRequest  {
  
    private String message;
    private String email;
    private String contactNo;
    private List<String> tags = null;


    /**
    **/
    public FeedbackRequest message(String message) {

        this.message = message;
        return this;
    }
    
    @ApiModelProperty(example = "This is a sample feedback", required = true, value = "")
    @JsonProperty("message")
    @Valid
    @NotNull(message = "Property message cannot be null.")

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    /**
    **/
    public FeedbackRequest email(String email) {

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
    public FeedbackRequest contactNo(String contactNo) {

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
    public FeedbackRequest tags(List<String> tags) {

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

    public FeedbackRequest addTagsItem(String tagsItem) {
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
        FeedbackRequest feedbackRequest = (FeedbackRequest) o;
        return Objects.equals(this.message, feedbackRequest.message) &&
            Objects.equals(this.email, feedbackRequest.email) &&
            Objects.equals(this.contactNo, feedbackRequest.contactNo) &&
            Objects.equals(this.tags, feedbackRequest.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, email, contactNo, tags);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class FeedbackRequest {\n");
        
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

