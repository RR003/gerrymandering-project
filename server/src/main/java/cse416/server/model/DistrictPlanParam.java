package cse416.server.model;

import java.util.List;

public class DistrictPlanParam {
    private List<Integer> listOfIds;

    public List<Integer> getListOfIds() {
        return listOfIds;
    }

    public void setListOfIds(List<Integer> listOfIds) {
        this.listOfIds = listOfIds;
    }

    @Override
    public String toString() {
        return "DistrictPlanParam{" +
                "listOfIds=" + listOfIds +
                '}';
    }
}
