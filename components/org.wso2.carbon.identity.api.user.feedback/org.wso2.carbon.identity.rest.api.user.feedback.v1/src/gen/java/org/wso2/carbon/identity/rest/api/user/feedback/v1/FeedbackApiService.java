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

import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackRequestDTO;
import org.wso2.carbon.identity.rest.api.user.feedback.v1.dto.FeedbackUpdateRequestDTO;

import javax.ws.rs.core.Response;

public abstract class FeedbackApiService {

    public abstract Response listFeedback(String filter, Integer limit, Integer offset, String sortBy, String sortOder);

    public abstract Response deleteFeedbackById(String id);

    public abstract Response getFeedbackById(String id);

    public abstract Response updateFeedback(String id, FeedbackUpdateRequestDTO body);

    public abstract Response addFeedback(FeedbackRequestDTO body);

}
