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

package org.wso2.carbon.identity.rest.api.user.feedback.v1.core.function;

import org.wso2.carbon.identity.cloud.user.feedback.mgt.model.Feedback;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackResponseDTO;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackResponseMetaDTO;

import java.util.Optional;
import java.util.function.Function;

import static org.wso2.carbon.identity.api.user.common.ContextLoader.buildURI;
import static org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtConstants.FEEDBACK_PATH_COMPONENT;
import static org.wso2.carbon.identity.api.user.feedback.common.FeedbackMgtConstants.V1_API_PATH_COMPONENT;

/**
 * Converts the internal {@link Feedback} model to corresponding API model {@link FeedbackResponseDTO}.
 */
public class FeedbackInfoToApiModel implements Function<Feedback, FeedbackResponseDTO> {

    @Override
    public FeedbackResponseDTO apply(Feedback feedbackModelObject) {

        FeedbackResponseDTO feedbackResponse = new FeedbackResponseDTO();
        FeedbackResponseMetaDTO feedbackMetaInfo = new FeedbackResponseMetaDTO();

        feedbackResponse.setId(feedbackModelObject.getUuid());
        feedbackResponse.setMessage(feedbackModelObject.getMessage());
        feedbackResponse.setEmail(Optional.ofNullable(feedbackModelObject.getEmail()).isPresent() ?
                feedbackModelObject.getEmail() : null);
        feedbackResponse.setContactNo(Optional.ofNullable(feedbackModelObject.getEmail()).isPresent() ?
                feedbackModelObject.getContactNo() : null);
        feedbackResponse.setTags(Optional.ofNullable(feedbackModelObject.getTags()).isPresent() ?
                feedbackModelObject.getTags() : null);

        feedbackMetaInfo.setLocation(buildURI(String.format(V1_API_PATH_COMPONENT + FEEDBACK_PATH_COMPONENT +
                feedbackModelObject.getUuid())).toString());
        feedbackMetaInfo.setCreated(feedbackModelObject.getTimeCreated());

        feedbackResponse.setMeta(feedbackMetaInfo);

        return  feedbackResponse;
    }

}
