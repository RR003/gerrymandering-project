package cse416.server.controller;

import cse416.server.dao.DistrictPlanImpl;
import cse416.server.dao.EnsembleImpl;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ensemble")
public class EnsembleController {

    @Autowired
    private EnsembleImpl ensembleImpl;

    @GetMapping("/analytics1/{stateName}/{ensemble}")
    public ResponseEntity<String> getComparisonDistrictMeasures(@PathVariable String stateName, @PathVariable String ensemble) {
        return ensembleImpl.getDistanceCompareMeasures(stateName, ensemble);
    }
    // Comparing size of Ensemble vs Number of Clusters
    @GetMapping("/getEnsemble/{stateName}")
    public ResponseEntity<String> getEnsembleAssociation(@PathVariable String stateName) {
        return ensembleImpl.getClusterAssociation(stateName);
    }

    @PostMapping("/postEnsembles")
    public ResponseEntity<String> postEnsembles(@RequestBody List<Map<String, Object>> list) {
        ensembleImpl.postEnsembles(list);
        return ResponseEntity.ok("success");
    }

}
