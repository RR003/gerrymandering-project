package cse416.server.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitializeImpl {
    @Autowired
    private MongoTemplate template;

    public ResponseEntity<String> getStateInfo(String state) {
        try {
            MongoCollection<Document> mongoCollection = template.getCollection("Initialize");
            Bson filter = Filters.eq("stateName", state);
            Bson projection = Projections.exclude("_id");
            Document document = mongoCollection.find(filter).projection(projection).first();
            String json = document.toJson();
            return ResponseEntity.ok(json);
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok("Error");
        }

    }

    public ResponseEntity<String> getInitializationInfo() {
        try {
            String[] resultArray = new String[3];
            MongoCollection<Document> mongoCollection = template.getCollection("Initialize");

            Bson filter = Filters.eq("state", "Ohio");
            Bson projection = Projections.exclude("_id");
            Document document = mongoCollection.find(filter).projection(projection).first();
            String json = document.toJson();
            resultArray[0] = json;

            Bson filter2 = Filters.eq("state", "Colorado");
            Document document2 = mongoCollection.find(filter2).projection(projection).first();
            String json2 = document2.toJson();
            resultArray[1] = json2;

            Bson filter3 = Filters.eq("state", "Illinois");
            Document document3 = mongoCollection.find(filter3).projection(projection).first();
            String json3 = document3.toJson();
            resultArray[2] = json3;

            String result = Arrays.toString(resultArray);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok("Error");
        }

    }
}
