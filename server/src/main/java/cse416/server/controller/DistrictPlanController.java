package cse416.server.controller;

import cse416.server.dao.DistrictPlanImpl;
import cse416.server.model.ClusterParam;
import cse416.server.model.DistrictPlanParam;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dp")
public class DistrictPlanController {

    @Autowired
    private DistrictPlanImpl districtPlanImpl;

    @GetMapping("/getDistrictPlan/{id}")
    public ResponseEntity<String> getDistrictPlan(@PathVariable int id) {
        return districtPlanImpl.getDistrictPlan(id);
    }

    @GetMapping("/getAverageDistrictPlan/{id}")
    public ResponseEntity<String> getAverageDistrictPlan(@PathVariable int id) {
        return districtPlanImpl.getAverageDistrictPlan(id);
    }

    @GetMapping("/getDistrictPlanInfo/{clusterId}")
    public ResponseEntity<String> getDistrictPlanInformation(@PathVariable int clusterId) {
        System.out.println("Selected Cluster: " + clusterId);
        ResponseEntity<String> res = districtPlanImpl.getDistrictPlanInformation(clusterId);
        System.out.println(res.toString());
        return res;
    }

    @PostMapping("/postDistrictPlans")
    public ResponseEntity<String> postDistrictPlanInfo(@RequestBody List<Map<String, Object>> list) {
        districtPlanImpl.postDistrictPlans(list);
        return ResponseEntity.ok("success");
    }

    /*@PostMapping("/postGeoJsons")
    public ResponseEntity<String> postGeoJsons(@RequestBody List<Map<String, Object>> list) {
        districtPlanImpl.postGeoJsons(list);
        return ResponseEntity.ok("success");
    }*/

    @PostMapping("/postGeoJsonReal")
    public ResponseEntity<String> postGeoJson(@RequestBody List<Map<String, Object>> list) {
        for (Map<String, Object> o : list) {
            districtPlanImpl.postGeoJson(o);
        }
        return ResponseEntity.ok("success");
    }

    @PostMapping("/postAdps")
    public ResponseEntity<String> postAdps(@RequestBody List<Map<String, Object>> list) {
        districtPlanImpl.postAdps(list);
        return ResponseEntity.ok("success");
    }
}
