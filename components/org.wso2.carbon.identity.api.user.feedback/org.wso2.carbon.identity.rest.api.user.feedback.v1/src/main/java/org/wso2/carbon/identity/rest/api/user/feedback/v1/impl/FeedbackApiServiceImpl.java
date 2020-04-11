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

package org.wso2.carbon.identity.rest.api.user.feedback.v1.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.api.user.common.ContextLoader;
import org.wso2.carbon.identity.cloud.user.feedback.mgt.model.Feedback;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.FeedbackApiService;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.core.FeedbackMgtService;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackListResponseDTO;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackRequestDTO;

import javax.ws.rs.core.Response;
import java.net.URI;

import static org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtConstants.FEEDBACK_PATH_COMPONENT;
import static org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtConstants.V1_API_PATH_COMPONENT;

/**
* Implementation of FeedbackApi Service.
*/
public class FeedbackApiServiceImpl extends FeedbackApiService {

    @Autowired
    private FeedbackMgtService feedbackMgtService;

    @Override
    public Response listFeedback(String filter, Integer limit, Integer offset, String sortBy, String sortOrder) {
        FeedbackListResponseDTO feedbackListResponse = feedbackMgtService.listFeedback(filter, limit, offset,
                sortBy, sortOrder);
        return Response.ok().entity(feedbackListResponse).build();
    }

    @Override
    public Response deleteFeedbackById(String id) {

        feedbackMgtService.deleteFeedback(id);
        return Response.noContent().build();
    }

    @Override
    public Response getFeedbackById(String id) {

        return Response.ok().entity(feedbackMgtService.getFeedback(id)).build();
    }

    @Override
    public Response updateFeedback(String id, FeedbackRequestDTO updateRequest) {

        return Response.ok().entity(feedbackMgtService.updateFeedbackEntry(id, updateRequest)).build();
    }

    @Override
    public Response addFeedback(FeedbackRequestDTO feedbackRequest) {

        Feedback feedback = feedbackMgtService.addFeedbackEntry(feedbackRequest);
        return Response.created(getFeedbackLocation(feedback.getUuid())).build();
    }

    private URI getFeedbackLocation(String uuid) {
        System.out.println("in mine : " + ContextLoader.buildURI(String.format(V1_API_PATH_COMPONENT + FEEDBACK_PATH_COMPONENT + uuid)).toString());
        return ContextLoader.buildURI(String.format(V1_API_PATH_COMPONENT + FEEDBACK_PATH_COMPONENT + uuid));
    }
}
