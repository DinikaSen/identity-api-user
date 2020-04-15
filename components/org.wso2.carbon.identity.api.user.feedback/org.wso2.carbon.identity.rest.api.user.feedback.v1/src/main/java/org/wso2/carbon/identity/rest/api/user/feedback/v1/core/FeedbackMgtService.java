/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.identity.rest.api.user.feedback.v1.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.api.user.common.ContextLoader;
import org.wso2.carbon.identity.api.user.common.error.APIError;
import org.wso2.carbon.identity.api.user.common.error.ErrorResponse;
import org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtConstants;
import org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtServiceHolder;
import org.wso2.carbon.identity.cloud.user.feedback.mgt.exception.FeedbackManagementException;
import org.wso2.carbon.identity.cloud.user.feedback.mgt.model.Feedback;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.core.function.FeedbackInfoToApiModel;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.FeedbackListResponse;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.FeedbackRequest;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.FeedbackResponse;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.Link;

import static org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtConstants.ErrorMessage.ERROR_CODE_ERROR_DELETING_FEEDBACK;
import static org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtConstants.ErrorMessage.ERROR_CODE_ERROR_RETRIEVING_FEEDBACK;
import static org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtConstants.ErrorMessage.ERROR_FEEDBACK_NOT_FOUND;

/**
 * Call internal OSGI services to perform user feedback management operations.
 */
public class FeedbackMgtService {

    private static final Log LOG = LogFactory.getLog(FeedbackMgtService.class);
    private static final String FEEDBACK_PAGINATION_LINK_FORMAT = "/v1/feedback?offset=%d&limit=%d";
    private static final String PAGE_LINK_REL_NEXT = "next";
    private static final String PAGE_LINK_REL_PREVIOUS = "previous";

    /**
     * Add a user feedback entry.
     *
     * @param feedbackPostRequest FeedbackObjectDTO object.
     * @return Created Feedback instance
     */
    public Feedback addFeedbackEntry(FeedbackRequest feedbackPostRequest) {

        Feedback feedback = createFeedbackObject(feedbackPostRequest);
        try {
            Feedback feedbackResult =
                    FeedbackMgtServiceHolder.getFeedbackManagementService().createFeedbackEntry(feedback);
            return feedbackResult;
        } catch (FeedbackManagementException e) {
            throw handleException(e, FeedbackMgtConstants.ErrorMessage.ERROR_CODE_ERROR_ADDING_FEEDBACK,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * List user feedback entries.
     *
     * @param limit     maximum no of feedback entries to be returned in the result set (optional).
     * @param offset    zero based index of the first feedback entry to be returned in the result set (optional).
     * @param filter    filter to search for feedback entries (optional).
     * @param sortOrder sort order, ascending or descending (optional).
     * @param sortBy    attribute to sort from (optional).
     * @return List of feedback entries matching the given criteria.
     */
    public FeedbackListResponse listFeedback(String filter, int limit, int offset, String sortBy,
                                             String sortOrder) {

        try {
            List<Feedback> feedbackInfoList = FeedbackMgtServiceHolder.getFeedbackManagementService().
                    listFeedbackEntries(filter, limit, offset, sortBy, sortOrder);
            int totalResults = FeedbackMgtServiceHolder.getFeedbackManagementService()
                    .getCountOfFeedbackResults(filter);
            return buildFeedbackListResponse(limit, offset, totalResults, feedbackInfoList);

        } catch (FeedbackManagementException e) {
            throw handleException(e, FeedbackMgtConstants.ErrorMessage.ERROR_CODE_ERROR_LISTING_FEEDBACK,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get feedback by Feedback ID.
     *
     * @param feedbackId unique identifier of the feedback.
     * @return Feedback instance for the given ID.
     */
    public FeedbackResponse getFeedback(String feedbackId) {

        try {
            Feedback feedbackInfo =
                    FeedbackMgtServiceHolder.getFeedbackManagementService().
                            getFeedbackEntry(feedbackId);
            if (feedbackInfo == null) {
                throw handleNotFoundError(feedbackId, ERROR_FEEDBACK_NOT_FOUND);
            }
            return buildFeedbackResponse(feedbackInfo);
        } catch (FeedbackManagementException e) {
            throw handleException(e, ERROR_CODE_ERROR_RETRIEVING_FEEDBACK, Response.Status.INTERNAL_SERVER_ERROR,
                    feedbackId);
        }
    }

    /**
     * Delete a feedback entry.
     *
     * @param feedbackId unique identifier of the feedback.
     */
    public void deleteFeedback(String feedbackId) {

        try {
            Feedback feedbackInfo = FeedbackMgtServiceHolder.getFeedbackManagementService().
                            getFeedbackEntry(feedbackId);
            if (feedbackInfo == null) {
                throw handleNotFoundError(feedbackId, ERROR_FEEDBACK_NOT_FOUND);
            }
            FeedbackMgtServiceHolder.getFeedbackManagementService().deleteFeedbackEntry(feedbackId);
        } catch (FeedbackManagementException e) {
            throw handleException(e, ERROR_CODE_ERROR_DELETING_FEEDBACK, Response.Status.INTERNAL_SERVER_ERROR,
                    feedbackId);
        }
    }

    /**
     * Update a user feedback entry.
     *
     * @param feedbackId       unique identifier of the feedback.
     * @param updateRequestDTO FeedbackObjectDTO object with new data.
     * @return Updated Feedback instance
     */
    public FeedbackResponse updateFeedbackEntry(String feedbackId, FeedbackRequest updateRequestDTO) {

        try {
            Feedback feedbackToBeUpdated =
                    FeedbackMgtServiceHolder.getFeedbackManagementService().getFeedbackEntry(feedbackId);
            if (feedbackToBeUpdated == null) {
                throw handleNotFoundError(feedbackId, ERROR_FEEDBACK_NOT_FOUND);
            }
            Feedback feedbackResult = FeedbackMgtServiceHolder.getFeedbackManagementService()
                    .updateFeedbackEntry(feedbackId, createFeedbackObject(updateRequestDTO));
            return buildFeedbackResponse(feedbackResult);
        } catch (FeedbackManagementException e) {
            throw handleException(e, FeedbackMgtConstants.ErrorMessage.ERROR_CODE_ERROR_UPDATING_FEEDBACK,
                    Response.Status.INTERNAL_SERVER_ERROR, feedbackId);
        }
    }

    /**
     * Create feedback object from feedback request.
     *
     * @param feedbackRequest FeedbackObjectDTO
     * @return Feedback Object
     */
    private Feedback createFeedbackObject(FeedbackRequest feedbackRequest) {

        Feedback feedback = new Feedback();
        feedback.setMessage(feedbackRequest.getMessage());
        if (feedbackRequest.getMessage() != null && StringUtils.isNotBlank(feedbackRequest.getMessage())) {
            feedback.setMessage(feedbackRequest.getMessage());
        }
        if (feedbackRequest.getEmail() != null && StringUtils.isNotBlank(feedbackRequest.getEmail())) {
            feedback.setEmail(feedbackRequest.getEmail());
        }
        if (feedbackRequest.getContactNo() != null && StringUtils.isNotBlank(feedbackRequest.getContactNo())) {
            feedback.setContactNo(feedbackRequest.getContactNo());
        }
        if (feedbackRequest.getTags() != null && !feedbackRequest.getTags().isEmpty()) {
            feedback.setTags((ArrayList<String>) feedbackRequest.getTags());
        }
        return feedback;
    }

    /**
     * Build response object from feedback object.
     *
     * @param feedbackInfoModel Feedback object
     * @return FeedbackResponse instance
     */
    private FeedbackResponse buildFeedbackResponse(Feedback feedbackInfoModel) {

        return new FeedbackInfoToApiModel().apply(feedbackInfoModel);
    }

    /**
     * Build feedback list response from feedback entries retrieved.
     *
     * @param limit         max entries in list
     * @param offset        index of first feedback entry
     * @param total         total number of entries matching list criteria
     * @param feedbackInfos list of feedback objects retrieved
     * @return FeedbackListResponse instance
     */
    private FeedbackListResponse buildFeedbackListResponse(int limit, int offset, int total,
                                                           List<Feedback> feedbackInfos) {

        List<FeedbackResponse> feedbackInfoList = buildFeedbackListResponses(feedbackInfos);
        List<Link> feedbackResponseLinks = buildPaginationLinks(limit, offset, total);
        FeedbackListResponse feedbackListResponse = new FeedbackListResponse();
        feedbackListResponse.setResources(feedbackInfoList);
        feedbackListResponse.setCount(feedbackInfoList.size());
        feedbackListResponse.setStartIndex(offset + 1);
        feedbackListResponse.setTotalResults(total);
        feedbackListResponse.setLinks(feedbackResponseLinks);

        return feedbackListResponse;
    }

    private List<FeedbackResponse> buildFeedbackListResponses(List<Feedback> feedbackInfoList) {

        return feedbackInfoList.stream().map(new FeedbackInfoToApiModel()).collect(Collectors.toList());
    }

    /**
     * Build pagination links.
     *
     * @param limit  max entries in a single list
     * @param offset zero-based starting index
     * @param total  total number of entries in all pages
     * @return Links to previous and next pages
     */
    private List<Link> buildPaginationLinks(int limit, int offset, int total) {

        List<Link> links = new ArrayList<>();

        // Next Link
        if ((offset + limit) < total) {
            links.add(buildPageLink(PAGE_LINK_REL_NEXT, (offset + limit), limit));
        }

        // Previous Link
        // Previous link matters only if offset is greater than 0.
        if (offset > 0) {
            if ((offset - limit) >= 0) { // A previous page of size 'limit' exists
                links.add(buildPageLink(PAGE_LINK_REL_PREVIOUS, calculateOffsetForPreviousLink(offset, limit, total),
                        limit));
            } else { // A previous page exists but it's size is less than the specified limit
                links.add(buildPageLink(PAGE_LINK_REL_PREVIOUS, 0, offset));
            }
        }

        return links;
    }

    private int calculateOffsetForPreviousLink(int offset, int limit, int total) {

        int newOffset = (offset - limit);
        if (newOffset < total) {
            return newOffset;
        }

        return calculateOffsetForPreviousLink(newOffset, limit, total);
    }

    private Link buildPageLink(String rel, int offset, int limit) {

        Link links = new Link();
        links.setRel(rel);
        links.setHref(ContextLoader.buildURI(String.format(FEEDBACK_PAGINATION_LINK_FORMAT, offset, limit)));
        return links;
    }

    private APIError handleException(Exception e, FeedbackMgtConstants.ErrorMessage
            errorEnum, Response.Status status, String... data) {

        ErrorResponse errorResponse = getErrorBuilder(errorEnum, data).build(LOG, e, errorEnum.getDescription());
        return new APIError(status, errorResponse);
    }

    private ErrorResponse.Builder getErrorBuilder(FeedbackMgtConstants.ErrorMessage errorMsg, String... data) {

        return new ErrorResponse.Builder().withCode(errorMsg.getCode()).withMessage(errorMsg.getMessage())
                .withDescription(buildErrorDescription(errorMsg, data));
    }

    private String buildErrorDescription(FeedbackMgtConstants.ErrorMessage errorEnum, String... data) {

        String errorDescription;

        if (ArrayUtils.isNotEmpty(data)) {
            errorDescription = String.format(errorEnum.getDescription(), data);
        } else {
            errorDescription = errorEnum.getDescription();
        }

        return errorDescription;
    }

    private APIError handleNotFoundError(String resourceId, FeedbackMgtConstants.ErrorMessage errorMessage) {

        Response.Status status = Response.Status.NOT_FOUND;
        ErrorResponse errorResponse =
                getErrorBuilder(errorMessage, resourceId).build(LOG, errorMessage.getDescription());

        return new APIError(status, errorResponse);
    }

}
