package cse416.server.controller;

import cse416.server.dao.ClusterImpl;
import cse416.server.model.ClusterParam;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cluster")
public class ClusterController {

    @Autowired
    private ClusterImpl clusterImpl;

    @GetMapping("/getAll/{stateName}/{ensembleName}/{distanceMeasure}")
    public ResponseEntity<String> getAllClusters(@PathVariable String stateName,
                                                         @PathVariable String ensembleName,
                                                         @PathVariable String distanceMeasure) {
        System.out.println(stateName + " " + ensembleName + " " + distanceMeasure);
        return clusterImpl.getAllClusters(stateName, ensembleName, distanceMeasure);
    }

    @PostMapping("/postClusters")
    public ResponseEntity<String> postClusters(@RequestBody List<Map<String, Object>> list) {
        clusterImpl.postAllClusters(list);
        return ResponseEntity.ok("success!");
    }
}
