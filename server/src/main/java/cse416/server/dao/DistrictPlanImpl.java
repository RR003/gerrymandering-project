package cse416.server.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DistrictPlanImpl {
    @Autowired
    private MongoTemplate template;

    public ResponseEntity<String> getDistrictPlan(int districtPlanId) {
        try {
            MongoCollection<Document> mongoCollection = template.getCollection("REAL-DistrictPlans");
            Bson filter = Filters.eq("districtPlanID", districtPlanId);
            Bson projection = Projections.exclude("_id");
            Document document = mongoCollection.find(filter).projection(projection).first();
            String json = document.toJson();
            return ResponseEntity.ok(json);
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok("Error");
        }

    }

    public ResponseEntity<String> getAverageDistrictPlan(int districtPlanId) {
        try {
            MongoCollection<Document> mongoCollection = template.getCollection("AverageClusterDistrictPlan");
            Bson filter = Filters.eq("dpId", districtPlanId);
            Bson projection = Projections.exclude("_id");
            Document document = mongoCollection.find(filter).projection(projection).first();
            String json = document.toJson();
            return ResponseEntity.ok(json);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.ok("Error");
        }

    }

    public ResponseEntity<String> getDistrictPlanInformation(int clusterId) {
        try {
            MongoCollection<Document> mongoCollection = template.getCollection("REAL-DistrictPlanInfo");
            Bson filter = Filters.eq("clusterID", clusterId);
            Bson projection = Projections.exclude("_id");



            FindIterable<Document> find = mongoCollection.find(filter).sort(new Document("districtPlanID", 1)).projection(projection);
            MongoCursor<Document> iterator = find.iterator();

            List<String> listOfDocuments = new ArrayList<>();
            while (iterator.hasNext()) {
                Document doc = iterator.next();
                String json = doc.toJson();
                listOfDocuments.add(json);
            }

            String[] arrayOfDocuments = new String[listOfDocuments.size()];
            for (int i = 0; i < arrayOfDocuments.length; i++) {
                arrayOfDocuments[i] = listOfDocuments.get(i);
            }

            String result = Arrays.toString(arrayOfDocuments);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok("Error");
        }


    }

    public void postDistrictPlans(List<Map<String, Object>> list) {
        MongoCollection<Document> mongoCollection = template.getCollection("REAL-DistrictPlanInfo");
        for (Map<String, Object> object : list) {
            Document document = new Document(object);
            mongoCollection.insertOne(document);
        }
    }

    public void postAdps(List<Map<String, Object>> list) {
        MongoCollection<Document> mongoCollection = template.getCollection("AverageClusterDistrictPlan");
        for (Map<String, Object> object : list) {
            Document document = new Document(object);
            mongoCollection.insertOne(document);
        }
    }

    public void postGeoJson(Map<String, Object> doc) {
       MongoCollection<Document> mongoCollection = template.getCollection("REAL-DistrictPlanInfo");
        Bson filter = Filters.and(Filters.eq("districtPlanID", doc.get("districtPlanID")),
                Filters.eq("isAvailable", "no"));

        FindIterable<Document> find = mongoCollection.find(filter);
        MongoCursor<Document> iterator = find.iterator();
        int numberOfDocuments = 0;
        while (iterator.hasNext()) {
            numberOfDocuments++;
            Document doc2 = iterator.next();
        }
        System.out.println(numberOfDocuments);
        for (int i = 0; i < numberOfDocuments; i++) {
            filter = Filters.and(Filters.eq("districtPlanID", doc.get("districtPlanID")),
                    Filters.eq("isAvailable", "no"));
            Bson update = Updates.set("isAvailable", "yes");
            mongoCollection.findOneAndUpdate(filter, update);
        }

        mongoCollection = template.getCollection("REAL-DistrictPlans");
        Document document = new Document(doc);
        mongoCollection.insertOne(document);
    }
}
