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

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackListResponseDTO;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackRequestDTO;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackResponseDTO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/feedback")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/feedback", description = "the feedback API")
public class FeedbackApi  {

    @Autowired
    private FeedbackApiService delegate;

    @Valid
    @GET
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve submitted feedbacks\n",
            notes = "This API returns user feedback messages according to the specified filter, sort, and pagination parameters.\n",
            response = FeedbackListResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Valid feedbacks are found"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "No feedback found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response feedbackGet(@ApiParam(value = "Filter expression for filtering") @QueryParam("filter")  String filter,
    @ApiParam(value = "Maximum number of records to return.", defaultValue="30") @QueryParam("limit")  Integer limit,
    @ApiParam(value = "Number of records to skip for pagination.", defaultValue="0") @QueryParam("offset")  Integer offset,
    @ApiParam(value = "Specifies the attribute whose value\nSHALL be used to order the returned responses") @QueryParam("sortBy")  String sortBy,
    @ApiParam(value = "The order in which the \"sortBy\" parameter is applied.") @QueryParam("sortOder")  String sortOder) {

        return delegate.listFeedback(filter,limit,offset,sortBy,sortOder);
    }

    @Valid
    @DELETE
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete a feedback by ID\n",
            notes = "This API is used to delete a feedback by ID\n",
            response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "No content"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response feedbackIdDelete(@ApiParam(value = "feedbackID",required=true ) @PathParam("id")  String id) {

        return delegate.deleteFeedbackById(id);
    }

    @Valid
    @GET
    @Path("/{id}")
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve a submitted feedback by ID\n",
            notes = "This API is used to retrieve a feedback by ID.\n",
            response = FeedbackResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Feedback found"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response feedbackIdGet(@ApiParam(value = "This represents the ID of the user feedback",required=true ) @PathParam("id")  String id) {

        return delegate.getFeedbackById(id);
    }

    @Valid
    @PUT
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update a feedback entry\n",
            notes = "This API is used to update feedback entries\n",
            response = FeedbackResponseDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response feedbackIdPut(@ApiParam(value = "feedbackID",required=true ) @PathParam("id")  String id,
    @ApiParam(value = "User feedback data to be updated" ,required=true ) @Valid FeedbackRequestDTO body) {

        return delegate.updateFeedback(id,body);
    }

    @Valid
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Submit a user feedback\n",
            notes = "This API is used to submit feedback given by users\n\n <b>Permission required:</b> none\n",
            response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Feedback submission successful"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error") })

    public Response feedbackPost(@ApiParam(value = "User feedback to be submitted" ,required=true ) @Valid FeedbackRequestDTO body) {

        return delegate.addFeedback(body);
    }

}
