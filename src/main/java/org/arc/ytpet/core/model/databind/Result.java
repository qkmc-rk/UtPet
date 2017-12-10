package org.arc.ytpet.core.model.databind;

public class Result {

    public enum  Rs{
        RESULT_FALSE, RESULT_TRUE
    }

    private String result;

    public Result(Rs rsEnum) {
        if(rsEnum == Rs.RESULT_FALSE){
            result = "false";
        }else if(rsEnum == Rs.RESULT_TRUE){
            result = "true";
        }else{
            result = "false";
        }

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
