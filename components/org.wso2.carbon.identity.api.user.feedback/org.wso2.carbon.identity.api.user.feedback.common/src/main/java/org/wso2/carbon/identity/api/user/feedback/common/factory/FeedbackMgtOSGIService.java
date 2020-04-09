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

package org.wso2.carbon.identity.api.user.feedback.common.factory;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.cloud.user.feedback.mgt.FeedbackManagementService;

import java.util.Hashtable;

/**
 * Factory Beans serves as a factory for creating other beans within the IOC container. This factory bean is used to
 * instantiate the FeedbackManagementService type of object inside the container.
 */
public class FeedbackMgtOSGIService extends AbstractFactoryBean<FeedbackManagementService> {

    private FeedbackManagementService feedbackMgtService;

    @Override
    public Class<?> getObjectType() {

        return Object.class;
    }

    protected FeedbackManagementService createInstance() throws Exception {

        if (this.feedbackMgtService == null) {
            System.out.println(FeedbackManagementService.class.toString());
            FeedbackManagementService taskOperationService =
                    (FeedbackManagementService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                            .getOSGiService(FeedbackManagementService.class, (Hashtable) null);
            if (taskOperationService == null) {
                throw new Exception("Unable to retrieve Feedback Management Service.");
            }

            this.feedbackMgtService = taskOperationService;
        }

        return this.feedbackMgtService;
    }

}

