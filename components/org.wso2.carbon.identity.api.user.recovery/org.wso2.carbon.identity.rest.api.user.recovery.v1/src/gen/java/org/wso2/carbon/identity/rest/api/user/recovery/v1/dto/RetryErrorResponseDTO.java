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

package org.wso2.carbon.identity.rest.api.user.recovery.v1.dto;

import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.rest.api.user.recovery.v1.dto.APICallDTO;
import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "")
public class RetryErrorResponseDTO {

    @Valid 
    private String code = null;

    @Valid 
    private String message = null;

    @Valid 
    private String description = null;

    @Valid 
    private String resetCode = null;

    @Valid 
    private List<APICallDTO> links = new ArrayList<APICallDTO>();

    /**
    * Error code corresponding to the error
    **/
    @ApiModelProperty(value = "Error code corresponding to the error")
    @JsonProperty("code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    /**
    * Error message
    **/
    @ApiModelProperty(value = "Error message")
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    /**
    * Error description
    **/
    @ApiModelProperty(value = "Error description")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    /**
    * Password reset code used in the request
    **/
    @ApiModelProperty(value = "Password reset code used in the request")
    @JsonProperty("resetCode")
    public String getResetCode() {
        return resetCode;
    }
    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    /**
    * Contains available api calls
    **/
    @ApiModelProperty(value = "Contains available api calls")
    @JsonProperty("links")
    public List<APICallDTO> getLinks() {
        return links;
    }
    public void setLinks(List<APICallDTO> links) {
        this.links = links;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class RetryErrorResponseDTO {\n");
        
        sb.append("    code: ").append(code).append("\n");
        sb.append("    message: ").append(message).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    resetCode: ").append(resetCode).append("\n");
        sb.append("    links: ").append(links).append("\n");
        
        sb.append("}\n");
        return sb.toString();
    }
}
