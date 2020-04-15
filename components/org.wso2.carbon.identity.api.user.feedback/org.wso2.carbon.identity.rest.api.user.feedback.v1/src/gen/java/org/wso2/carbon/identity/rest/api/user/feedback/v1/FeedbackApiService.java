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

import javax.ws.rs.core.Response;

import org.wso2.carbon.identity.rest.api.user.feedback.v1.model.FeedbackRequest;


public interface FeedbackApiService {

      public Response addFeedback(FeedbackRequest feedbackRequest);

      public Response deleteFeedback(String id);

      public Response getFeedback(String id);

      public Response listFeedback(Integer limit, Integer offset, String filter, String sortOrder, String sortBy);

      public Response updateFeedback(String id, FeedbackRequest feedbackRequest);
}
