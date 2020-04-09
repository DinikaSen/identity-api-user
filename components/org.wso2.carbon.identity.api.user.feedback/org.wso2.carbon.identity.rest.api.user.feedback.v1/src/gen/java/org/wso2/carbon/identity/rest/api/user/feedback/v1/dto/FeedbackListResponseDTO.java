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
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackResponseDTO;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.LinkDTO;
import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "")
public class FeedbackListResponseDTO {

    @Valid 
    private Integer totalResults = null;

    @Valid 
    private Integer startIndex = null;

    @Valid 
    private Integer itemsPerPage = null;

    @Valid 
    private List<FeedbackResponseDTO> resources = new ArrayList<FeedbackResponseDTO>();

    @Valid 
    private List<LinkDTO> links = new ArrayList<LinkDTO>();

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("totalResults")
    public Integer getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("startIndex")
    public Integer getStartIndex() {
        return startIndex;
    }
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("itemsPerPage")
    public Integer getItemsPerPage() {
        return itemsPerPage;
    }
    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
    **/
    @ApiModelProperty(value = "")
    @JsonProperty("resources")
    public List<FeedbackResponseDTO> getResources() {
        return resources;
    }
    public void setResources(List<FeedbackResponseDTO> resources) {
        this.resources = resources;
    }

    /**
    * Navigation links.
    **/
    @ApiModelProperty(value = "Navigation links.")
    @JsonProperty("links")
    public List<LinkDTO> getLinks() {
        return links;
    }
    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class FeedbackListResponseDTO {\n");
        
        sb.append("    totalResults: ").append(totalResults).append("\n");
        sb.append("    startIndex: ").append(startIndex).append("\n");
        sb.append("    itemsPerPage: ").append(itemsPerPage).append("\n");
        sb.append("    resources: ").append(resources).append("\n");
        sb.append("    links: ").append(links).append("\n");
        
        sb.append("}\n");
        return sb.toString();
    }
}
