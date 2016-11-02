package com.commonutils.data;

public class BodyParama
{
    private int PageIndex;
    private int PageSize;
    private String ActivityLabel;

    public BodyParama(int pageIndex, int pageSize, String activityLabel) {
        PageIndex = pageIndex;
        PageSize = pageSize;
        ActivityLabel = activityLabel;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public String getActivityLabel() {
        return ActivityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        ActivityLabel = activityLabel;
    }
}
