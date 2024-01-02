package cse416.server.model;

public class ClusterParam {
    private String stateName;
    private String ensemble;
    private String distanceMeasure;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getEnsemble() {
        return ensemble;
    }

    public void setEnsemble(String ensemble) {
        this.ensemble = ensemble;
    }

    public String getDistanceMeasure() {
        return distanceMeasure;
    }

    public void setDistanceMeasure(String distanceMeasure) {
        this.distanceMeasure = distanceMeasure;
    }

    @Override
    public String toString() {
        return "ClusterParam{" +
                "stateName='" + stateName + '\'' +
                ", ensemble='" + ensemble + '\'' +
                ", distanceMeasure='" + distanceMeasure + '\'' +
                '}';
    }
}
