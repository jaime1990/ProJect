package com.commonutils.data;

public class BodyParamawwww
{
    private String ActivityId;
    private String Token;

    public BodyParamawwww(String activityId, String token) {
        ActivityId = activityId;
        Token = token;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
