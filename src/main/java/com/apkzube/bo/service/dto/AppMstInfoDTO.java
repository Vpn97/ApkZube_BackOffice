package com.apkzube.bo.service.dto;

import com.apkzube.bo.entity.AppMst;

public class AppMstInfoDTO {

    private AppMstDTO appMstDTO;

    private int totalCategory;

    private int totalProgramCategory;

    private int totalExample;

    private int totalTutorial;

    private int totalMaterial;

    private int totalBlogs;

    private int totalNewspost;

    public AppMstInfoDTO() {}

    public int getTotalProgramCategory() {
        return totalProgramCategory;
    }

    public void setTotalProgramCategory(int totalProgramCategory) {
        this.totalProgramCategory = totalProgramCategory;
    }

    public AppMstDTO getAppMstDTO() {
        return appMstDTO;
    }

    public void setAppMstDTO(AppMstDTO appMstDTO) {
        this.appMstDTO = appMstDTO;
    }

    public int getTotalCategory() {
        return totalCategory;
    }

    public void setTotalCategory(int totalCategory) {
        this.totalCategory = totalCategory;
    }

    public int getTotalExample() {
        return totalExample;
    }

    public void setTotalExample(int totalExample) {
        this.totalExample = totalExample;
    }

    public int getTotalTutorial() {
        return totalTutorial;
    }

    public void setTotalTutorial(int totalTutorial) {
        this.totalTutorial = totalTutorial;
    }

    public int getTotalMaterial() {
        return totalMaterial;
    }

    public void setTotalMaterial(int totalMaterial) {
        this.totalMaterial = totalMaterial;
    }

    public int getTotalBlogs() {
        return totalBlogs;
    }

    public void setTotalBlogs(int totalBlogs) {
        this.totalBlogs = totalBlogs;
    }

    public int getTotalNewspost() {
        return totalNewspost;
    }

    public void setTotalNewspost(int totalNewspost) {
        this.totalNewspost = totalNewspost;
    }
}
