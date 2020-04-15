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
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.FeedbackResponse;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.Link;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class FeedbackListResponse  {
  
    private Integer totalResults;
    private Integer startIndex;
    private Integer count;
    private List<FeedbackResponse> resources = null;

    private List<Link> links = null;


    /**
    * The total number of results matching the query.
    **/
    public FeedbackListResponse totalResults(Integer totalResults) {

        this.totalResults = totalResults;
        return this;
    }
    
    @ApiModelProperty(example = "30", value = "The total number of results matching the query.")
    @JsonProperty("totalResults")
    @Valid
    public Integer getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    /**
    * The 1-based index of the first result in the current set of results.
    **/
    public FeedbackListResponse startIndex(Integer startIndex) {

        this.startIndex = startIndex;
        return this;
    }
    
    @ApiModelProperty(example = "5", value = "The 1-based index of the first result in the current set of results.")
    @JsonProperty("startIndex")
    @Valid
    public Integer getStartIndex() {
        return startIndex;
    }
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
    * Number of results returned in response.
    **/
    public FeedbackListResponse count(Integer count) {

        this.count = count;
        return this;
    }
    
    @ApiModelProperty(example = "1", value = "Number of results returned in response.")
    @JsonProperty("count")
    @Valid
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
    * Feedback entries matching the query.
    **/
    public FeedbackListResponse resources(List<FeedbackResponse> resources) {

        this.resources = resources;
        return this;
    }
    
    @ApiModelProperty(value = "Feedback entries matching the query.")
    @JsonProperty("resources")
    @Valid
    public List<FeedbackResponse> getResources() {
        return resources;
    }
    public void setResources(List<FeedbackResponse> resources) {
        this.resources = resources;
    }

    public FeedbackListResponse addResourcesItem(FeedbackResponse resourcesItem) {
        if (this.resources == null) {
            this.resources = new ArrayList<>();
        }
        this.resources.add(resourcesItem);
        return this;
    }

        /**
    * Navigation links.
    **/
    public FeedbackListResponse links(List<Link> links) {

        this.links = links;
        return this;
    }
    
    @ApiModelProperty(example = "[{\"href\":\"/t/carbon.super/api/user/v1/me/feedback?offset=10&limit=10\",\"rel\":\"next\"},{\"href\":\"/t/carbon.super/api/user/v1/me/feedback?offset=0&limit=5\",\"rel\":\"previous\"},{\"href\":\"/t/carbon.super/api/user/v1/me/feedback?offset=0&limit=10\",\"rel\":\"first\"},{\"href\":\"/t/carbon.super/api/user/v1/me/feedback?offset=20&limit=10\",\"rel\":\"last\"}]", value = "Navigation links.")
    @JsonProperty("links")
    @Valid
    public List<Link> getLinks() {
        return links;
    }
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public FeedbackListResponse addLinksItem(Link linksItem) {
        if (this.links == null) {
            this.links = new ArrayList<>();
        }
        this.links.add(linksItem);
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
        FeedbackListResponse feedbackListResponse = (FeedbackListResponse) o;
        return Objects.equals(this.totalResults, feedbackListResponse.totalResults) &&
            Objects.equals(this.startIndex, feedbackListResponse.startIndex) &&
            Objects.equals(this.count, feedbackListResponse.count) &&
            Objects.equals(this.resources, feedbackListResponse.resources) &&
            Objects.equals(this.links, feedbackListResponse.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalResults, startIndex, count, resources, links);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class FeedbackListResponse {\n");
        
        sb.append("    totalResults: ").append(toIndentedString(totalResults)).append("\n");
        sb.append("    startIndex: ").append(toIndentedString(startIndex)).append("\n");
        sb.append("    count: ").append(toIndentedString(count)).append("\n");
        sb.append("    resources: ").append(toIndentedString(resources)).append("\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

