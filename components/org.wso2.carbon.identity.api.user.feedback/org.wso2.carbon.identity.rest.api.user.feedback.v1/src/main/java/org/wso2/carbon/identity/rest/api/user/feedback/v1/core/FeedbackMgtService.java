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
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackListResponseDTO;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackRequestDTO;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackResponseDTO;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.LinkDTO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtConstants.ErrorMessage.*;

/**
 * Call internal OSGI services to perform username feedback management operations.
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
     */
    public Feedback addFeedbackEntry(FeedbackRequestDTO feedbackPostRequest) {

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
     */
    public FeedbackListResponseDTO listFeedback(String filter, int limit, int offset, String sortBy,
                                                String sortOrder) {

//        validateSortingParameters(sortBy, sortOrder);
        try {
            List<Feedback> feedbackInfoList = FeedbackMgtServiceHolder.getFeedbackManagementService().
                    listFeedbackEntries(filter, limit, offset, sortBy, sortOrder);
            int totalResults = FeedbackMgtServiceHolder.getFeedbackManagementService()
                    .getCountOfFeedbackResults(filter);
            return buildFeedbackListResponse(limit, offset, totalResults, feedbackInfoList);

        } catch (FeedbackManagementException e) {
            throw handleException(e, FeedbackMgtConstants.ErrorMessage.ERROR_CODE_ERROR_RETRIEVING_FEEDBACK,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get feedback by Feedback ID.
     *
     * @param feedbackId unique identifier of the feedback
     * @return a Feedback instance.
     */
    public FeedbackResponseDTO getFeedback(String feedbackId) {

        try {
            Feedback feedbackInfo =
                    FeedbackMgtServiceHolder.getFeedbackManagementService().
                            getFeedbackEntry(feedbackId);
            if (feedbackInfo == null) {
                throw handleNotFoundError(feedbackId, ERROR_FEEDBACK_NOT_FOUND);
            }
            return buildFeedbackResponse(feedbackInfo);
        } catch (FeedbackManagementException e) {
            throw handleException(e, ERROR_CODE_ERROR_RETRIEVING_FEEDBACK, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete a feedback entry.
     *
     * @param feedbackId feedback entry ID.
     */
    public void deleteFeedback(String feedbackId) {

        try {
            FeedbackMgtServiceHolder.getFeedbackManagementService().deleteFeedbackEntry(feedbackId);
        } catch (FeedbackManagementException e) {
            throw handleException(e, ERROR_CODE_ERROR_DELETING_FEEDBACK, Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Update a user feedback entry.
     *
     * @param updateRequestDTO FeedbackObjectDTO object.
     */
    public FeedbackResponseDTO updateFeedbackEntry(String feedbackId, FeedbackRequestDTO updateRequestDTO) {

        Feedback feedback = createFeedbackObject(updateRequestDTO);
        try {
            Feedback feedbackResult =
                    FeedbackMgtServiceHolder.getFeedbackManagementService().updateFeedbackEntry(feedbackId, feedback);
            return buildFeedbackResponse(feedbackResult);
        } catch (FeedbackManagementException e) {
            throw handleException(e, FeedbackMgtConstants.ErrorMessage.ERROR_CODE_ERROR_ADDING_FEEDBACK,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create feedback object.
     *
     * @param feedbackRequest FeedbackObjectDTO
     * @return feedback Feedback Object
     */
    private Feedback createFeedbackObject(FeedbackRequestDTO feedbackRequest) {

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

    private FeedbackResponseDTO buildFeedbackResponse(Feedback feedbackInfoModel) {

        return new FeedbackInfoToApiModel().apply(feedbackInfoModel);
    }

    private FeedbackListResponseDTO buildFeedbackListResponse(int limit, int offset, int total,
                                                              List<Feedback> feedbackInfos) {

        List<FeedbackResponseDTO> feedbackInfoList = buildFeedbackListResponses(feedbackInfos);
        List<LinkDTO> feedbackResponseLinks = buildPaginationLinks(limit, offset, total);
        FeedbackListResponseDTO feedbackListResponse = new FeedbackListResponseDTO();
        feedbackListResponse.setResources(feedbackInfoList);
        feedbackListResponse.setItemsPerPage(feedbackInfoList.size());
        feedbackListResponse.setStartIndex(offset + 1);
        feedbackListResponse.setTotalResults(total);
        feedbackListResponse.setLinks(feedbackResponseLinks);

        return feedbackListResponse;
    }

    private List<FeedbackResponseDTO> buildFeedbackListResponses(List<Feedback> feedbackInfoList) {

        return feedbackInfoList.stream().map(new FeedbackInfoToApiModel()).collect(Collectors.toList());
    }

    private List<LinkDTO> buildPaginationLinks(int limit, int offset, int total) {

        List<LinkDTO> links = new ArrayList<>();

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

    private LinkDTO buildPageLink(String rel, int offset, int limit) {
        LinkDTO links = new LinkDTO();
        links.setRel(rel);
        links.setHref(ContextLoader.buildURI(String.format(FEEDBACK_PAGINATION_LINK_FORMAT, offset, limit)).toString());
        return links;
    }

 /*   private boolean validateSortingParameters(String sortBy, String sortOrder) {

        boolean isValid = false;
        if (StringUtils.isNotBlank(sortBy) && StringUtils.isNotBlank(sortOrder)) {
            if (isSortableAttribute(sortBy)) {
                if (isSortableAttribute(sortBy)) {
                    isValid = true;
                } else {
                    throw buildError(ERROR_CODE_INVALID_SORT_ORDER, Response.Status.BAD_REQUEST,
                            sortOrder);
                }
            } else {
                throw buildError(ERROR_CODE_UNSUPPORTED_SORT_BY_ATTRIBUTE, Response.Status.BAD_REQUEST,
                        sortBy);
            }

        }
        return isValid;
    }*/

    /*
     */

    /**
     * Check the whether feedback entry exist.
     *
     * @param feedbackId ID of the feedback entry.
     * @return isAvailable boolean.
     *//*
    public boolean isFeedbackEntryAvailable(String feedbackId) {

        boolean isAvailable;
        try {
            isAvailable =
                    FeedbackMgtServiceHolder.getFeedbackManagementService().isFeedbackAvailable(feedbackId);
        } catch (FeedbackManagementException e) {
            throw handleException(e, ERROR_FEEDBACK_NOT_FOUND, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return isAvailable;
    }*/
    private APIError handleException(Exception e, FeedbackMgtConstants.ErrorMessage
            errorEnum, Response.Status status, String... data) {

        ErrorResponse errorResponse = getErrorBuilder(errorEnum, data).build(LOG, e, errorEnum.getDescription());
        return new APIError(status, errorResponse);
    }

    private APIError buildError(FeedbackMgtConstants.ErrorMessage
                                        errorEnum, Response.Status status, String... data) {

        ErrorResponse errorResponse = getErrorBuilder(errorEnum, data).build(LOG, null, errorEnum.getDescription());
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
/*
    private boolean isFilterableAttribute(String attribute) {

        return Arrays.stream(FilterableAttributes.values()).anyMatch(filterableAttribute -> filterableAttribute.name()
                .equals(attribute));
    }

    private boolean isSortableAttribute(String attribute) {

        return Arrays.stream(SortableAttributes.values()).anyMatch(sortableAttribute -> sortableAttribute.name()
                .equals(attribute));
    }*/

    /*private enum AttributeOperators {
        eq, sw, co, ew;
    }

    private enum FilterableAttributes {
        email, tag;
    }

    private enum SortOrderOperators {
        asc, desc;
    }

    private enum SortableAttributes {
        time_created;
    }*/

}
