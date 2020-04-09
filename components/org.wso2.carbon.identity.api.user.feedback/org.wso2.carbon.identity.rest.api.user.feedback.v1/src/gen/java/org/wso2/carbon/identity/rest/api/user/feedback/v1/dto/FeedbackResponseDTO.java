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

package org.wso2.carbon.identity.rest.api.user.feedback.v1.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackResponseMetaDTO;
import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "")
public class FeedbackResponseDTO {

    @Valid 
    private FeedbackResponseMetaDTO meta = null;

    @Valid 
    private String id = null;

    @Valid 
    private String message = null;

    @Valid 
    private String email = null;

    @Valid 
    private String contactNo = null;

    @Valid 
    private List<String> tags = new ArrayList<String>();

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("meta")
    public FeedbackResponseMetaDTO getMeta() {
        return meta;
    }
    public void setMeta(FeedbackResponseMetaDTO meta) {
        this.meta = meta;
    }

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("id")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("contactNo")
    public String getContactNo() {
        return contactNo;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class FeedbackResponseDTO {\n");
        
        sb.append("    meta: ").append(meta).append("\n");
        sb.append("    id: ").append(id).append("\n");
        sb.append("    message: ").append(message).append("\n");
        sb.append("    email: ").append(email).append("\n");
        sb.append("    contactNo: ").append(contactNo).append("\n");
        sb.append("    tags: ").append(tags).append("\n");
        
        sb.append("}\n");
        return sb.toString();
    }
}
