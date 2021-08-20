package com.apkzube.bo.web.rest.response;

import java.util.List;

public class DataUpdateResponse {

    private boolean status;

    private String successMsg;

    private List<ErrorDTO> errorDTOList;

    public DataUpdateResponse() {}

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public List<ErrorDTO> getErrorDTOList() {
        return errorDTOList;
    }

    public void setErrorDTOList(List<ErrorDTO> errorDTOList) {
        this.errorDTOList = errorDTOList;
    }
}
