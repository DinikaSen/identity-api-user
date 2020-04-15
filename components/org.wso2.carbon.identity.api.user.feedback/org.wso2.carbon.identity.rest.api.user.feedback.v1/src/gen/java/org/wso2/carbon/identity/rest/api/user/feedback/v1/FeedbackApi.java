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

package org.wso2.carbon.identity.rest.api.user.feedback.v1;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.Error;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.FeedbackListResponse;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.FeedbackRequest;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.FeedbackResponse;

@Path("/feedback")
@Api(description = "The feedback API")

public class FeedbackApi  {

    @Autowired
    private FeedbackApiService delegate;

    @Valid
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Add feedback.", notes = "This API is used to submit feedback given by users   <b>Permission required:</b> none ", response = Void.class, authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Feedback submission successful", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    public Response addFeedback(@ApiParam(value = "Request to initate feedback submission. Feedback message is `REQUIRED`." ,required=true) @Valid FeedbackRequest feedbackRequest) {

        return delegate.addFeedback(feedbackRequest );
    }

    @Valid
    @DELETE
    @Path("/{id}")
    
    @Produces({ "application/json" })
    @ApiOperation(value = "Delete a feedback by ID.", notes = "This API is used to delete a feedback by ID. ", response = Void.class, authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "No content", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized.", response = Void.class),
        @ApiResponse(code = 403, message = "Resource Forbidden.", response = Void.class),
        @ApiResponse(code = 404, message = "The specified resource is not found.", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    public Response deleteFeedback(@ApiParam(value = "Id of the feedback entry.",required=true) @PathParam("id") String id) {

        return delegate.deleteFeedback(id );
    }

    @Valid
    @GET
    @Path("/{id}")
    
    @Produces({ "application/json" })
    @ApiOperation(value = "Get feedback by Id.", notes = "This API is used to retrieve a feedback by ID. ", response = FeedbackResponse.class, authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = FeedbackResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized.", response = Void.class),
        @ApiResponse(code = 403, message = "Resource Forbidden.", response = Void.class),
        @ApiResponse(code = 404, message = "The specified resource is not found.", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    public Response getFeedback(@ApiParam(value = "Id of the feedback entry.",required=true) @PathParam("id") String id) {

        return delegate.getFeedback(id );
    }

    @Valid
    @GET
    
    
    @Produces({ "application/json" })
    @ApiOperation(value = "List feedback.", notes = "This API returns user feedback messages according to the specified filter, sort, and pagination parameters. ", response = FeedbackListResponse.class, authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = FeedbackListResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized.", response = Void.class),
        @ApiResponse(code = 403, message = "Resource Forbidden.", response = Void.class),
        @ApiResponse(code = 404, message = "The specified resource is not found.", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    public Response listFeedback(    @Valid @Min(0)@ApiParam(value = "Maximum number of records to return.", defaultValue="30") @DefaultValue("30")  @QueryParam("limit") Integer limit,     @Valid @Min(0)@ApiParam(value = "Number of records to skip for pagination.", defaultValue="0") @DefaultValue("0")  @QueryParam("offset") Integer offset,     @Valid@ApiParam(value = "Condition to filter the retrival of records. Filtering is supported for \"email\" and \"tag\" only.")  @QueryParam("filter") String filter,     @Valid@ApiParam(value = "Define the order how the retrieved records should be sorted. ", allowableValues="asc, desc")  @QueryParam("sortOrder") String sortOrder,     @Valid@ApiParam(value = "Attribute by which the retrieved records should be sorted. Sorting is supported for \"time_created\" only ")  @QueryParam("sortBy") String sortBy) {

        return delegate.listFeedback(limit,  offset,  filter,  sortOrder,  sortBy );
    }

    @Valid
    @PUT
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Update a feedback entry.", notes = "This API is used to update feedback entries. ", response = FeedbackResponse.class, authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Update successful.", response = FeedbackResponse.class),
        @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized.", response = Void.class),
        @ApiResponse(code = 403, message = "Resource Forbidden.", response = Void.class),
        @ApiResponse(code = 404, message = "The specified resource is not found.", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    public Response updateFeedback(@ApiParam(value = "Id of the feedback entry.",required=true) @PathParam("id") String id, @ApiParam(value = "" ,required=true) @Valid FeedbackRequest feedbackRequest) {

        return delegate.updateFeedback(id,  feedbackRequest );
    }

}
