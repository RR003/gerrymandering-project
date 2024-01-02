package cse416.server.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import cse416.server.model.ClusterParam;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ClusterImpl {
    @Autowired
    private MongoTemplate template;

    public void postAllClusters(List<Map<String, Object>> list) {
        MongoCollection<Document> mongoCollection = template.getCollection("REAL-Clusters");
        for (Map<String, Object> object : list) {
            Document document = new Document(object);
            mongoCollection.insertOne(document);
        }
    }

    public ResponseEntity<String> getAllClusters(String stateName, String ensembleName, String distanceMeasure) {
        try {
            MongoCollection<Document> mongoCollection = template.getCollection("REAL-Clusters");

            Bson filter = Filters.and(Filters.eq("statename", stateName),
                    Filters.eq("ensembleName", ensembleName),
                    Filters.eq("distanceMeasure", distanceMeasure));
            Bson projection = Projections.exclude("_id");
            FindIterable<Document> find = mongoCollection.find(filter).sort(new Document("clusterID", 1)).projection(projection);
            MongoCursor<Document> iterator = find.iterator();

            List<String> listofDocuments = new ArrayList<>();
            while (iterator.hasNext()) {
                Document document = iterator.next();
                String json = document.toJson();
                listofDocuments.add(json);
            }

            String[] arrayOfDocuments = new String[listofDocuments.size()];
            int index = 0;
            for (String s : listofDocuments) {
                arrayOfDocuments[index++] = s;
            }

            return ResponseEntity.ok(Arrays.toString(arrayOfDocuments));
        }catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.ok("Error");
        }

    }
}
