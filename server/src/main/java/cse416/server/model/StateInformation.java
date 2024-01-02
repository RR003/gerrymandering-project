package cse416.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Initialize")
public class StateInformation {

    private String StateBoundary;
    private String StateName;

    public String getStateBoundary() {
        return StateBoundary;
    }

    public void setStateBoundary(String stateBoundary) {
        this.StateBoundary = stateBoundary;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        this.StateName = stateName;
    }
}
