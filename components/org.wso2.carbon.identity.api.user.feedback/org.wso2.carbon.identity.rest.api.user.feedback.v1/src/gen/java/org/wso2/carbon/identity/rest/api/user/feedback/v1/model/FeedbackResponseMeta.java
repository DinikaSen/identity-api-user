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
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class FeedbackResponseMeta  {
  
    private String created;
    private String location;

    /**
    **/
    public FeedbackResponseMeta created(String created) {

        this.created = created;
        return this;
    }
    
    @ApiModelProperty(example = "2020-03-25T11:27:36", value = "")
    @JsonProperty("created")
    @Valid
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }

    /**
    **/
    public FeedbackResponseMeta location(String location) {

        this.location = location;
        return this;
    }
    
    @ApiModelProperty(example = "https://localhost:9443/feedback/7bac6a86-1f21-4937-9fb1-5be4a93ef469", value = "")
    @JsonProperty("location")
    @Valid
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeedbackResponseMeta feedbackResponseMeta = (FeedbackResponseMeta) o;
        return Objects.equals(this.created, feedbackResponseMeta.created) &&
            Objects.equals(this.location, feedbackResponseMeta.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, location);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class FeedbackResponseMeta {\n");
        
        sb.append("    created: ").append(toIndentedString(created)).append("\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
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

