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

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "")
public class FeedbackResponseMetaDTO {

    @Valid 
    private String created = null;

    @Valid 
    private String location = null;

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("created")
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("location")
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class FeedbackResponseMetaDTO {\n");
        
        sb.append("    created: ").append(created).append("\n");
        sb.append("    location: ").append(location).append("\n");
        
        sb.append("}\n");
        return sb.toString();
    }
}
