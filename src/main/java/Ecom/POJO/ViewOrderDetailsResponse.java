package Ecom.POJO;

import com.github.fge.uritemplate.vars.VariableMap;

public class ViewOrderDetailsResponse {
    private Data data;
    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
