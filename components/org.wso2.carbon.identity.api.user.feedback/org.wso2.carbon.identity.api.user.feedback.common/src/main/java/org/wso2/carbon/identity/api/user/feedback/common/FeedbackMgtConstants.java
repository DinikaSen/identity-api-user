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

package org.wso2.carbon.identity.api.user.feedback.common;

/**
 * Feedback Management constant class.
 */
public class FeedbackMgtConstants {

    public static final String FEEDBACK_PATH_COMPONENT = "/feedback/";
    public static final String V1_API_PATH_COMPONENT = "/v1";
    public static final String FEEDBACK_MGT_PREFIX = "FM-";

    private FeedbackMgtConstants() {

    }

    /**
     * Enum for error messages.
     */
    public enum ErrorMessage {
        ERROR_CODE_ERROR_LISTING_FEEDBACK("65001", "Unable to list existing user feedbacks.",
                "Server encountered an error while listing the user feedback entries."),
        ERROR_CODE_ERROR_ADDING_FEEDBACK("65002", "Unable to add user feedback entry.",
                "Server encountered an error while adding the user feedback entry."),
        ERROR_CODE_ERROR_DELETING_FEEDBACK("65004", "Unable to delete feedback entry.",
                "Server encountered an error while deleting the feedback entry for the ID %s."),
        ERROR_CODE_ERROR_RETRIEVING_FEEDBACK("65003", "Unable to retrieve feedback entry.",
                "Server encountered an error while retrieving the feedback entry for ID %s."),
        ERROR_CODE_ERROR_UPDATING_FEEDBACK("65005", "Unable to update feedback entry.",
                "Server encountered an error while updating the feedback entry for ID %s."),
        ERROR_FEEDBACK_NOT_FOUND("60006", "Feedback not found.",
                "Feedback cannot be found for the provided ID: %s."),
        ERROR_CODE_UNSUPPORTED_FILTER_OPERATION("10002",
                "Filter operation not supported.",
                "Filter operation: %s is not supported."),
        ERROR_CODE_UNSUPPORTED_FILTER_ATTRIBUTE("10003", "Filtering not supported for the given attribute.",
                "Filtering from %s is not supported."),
        ERROR_CODE_UNSUPPORTED_SORT_BY_ATTRIBUTE("10003", "Sorting not supported for the given attribute.",
                "Sorting by %s is not supported."),
        ERROR_CODE_INVALID_SORT_ORDER("10003", "Sort order parameter is invalid.",
                "Sort order %s is invalid. Only 'asc' and 'desc' are supported"),
        ERROR_CODE_INVALID_FILTER_QUERY("10004", "Invalid filter query.",
                "Provided filter query is invalid."),
        ERROR_FEEDBACK_OFFSET_VALIDATION("60009", "Invalid offset.",
                "Offset should be greater than or equal to 0");

        private final String code;
        private final String message;
        private final String description;

        ErrorMessage(String code, String message, String description) {

            this.code = code;
            this.message = message;
            this.description = description;
        }

        public String getCode() {

            return FEEDBACK_MGT_PREFIX + this.code;
        }

        public String getMessage() {

            return this.message;
        }

        public String getDescription() {

            return this.description;
        }

        public String toString() {

            return this.code + " | " + this.message;
        }
    }
}
