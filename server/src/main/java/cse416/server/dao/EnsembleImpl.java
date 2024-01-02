package cse416.server.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EnsembleImpl {
    @Autowired
    private MongoTemplate template;

    // public ResponseEntity<Document>

    public ResponseEntity<String> getDistanceCompareMeasures(String state, String ensemble) {
        try {
            MongoCollection<Document> mongoCollection = template.getCollection("Ensembles");
            Bson filter = Filters.and(Filters.eq("stateName", state),
                    Filters.eq("ensembleName", ensemble));
            Bson projection = Projections.exclude("_id");
            Document document = mongoCollection.find(filter).projection(projection).first();
            String json = document.toJson();
            return ResponseEntity.ok(json);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.ok("Error");
        }
    }

    public ResponseEntity<String> getClusterAssociation(String state) {
        try {
            MongoCollection<Document> mongoCollection = template.getCollection("Ensembles");
            Bson filter = Filters.and(Filters.eq("stateName", state));
            Bson projection = Projections.exclude("_id");
            FindIterable<Document> find = mongoCollection.find(filter).projection(projection);
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

        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.ok("Error");
        }
    }

    public void postEnsembles(List<Map<String, Object>> list) {
        MongoCollection<Document> mongoCollection = template.getCollection("Ensembles");
        for (Map<String, Object> object : list) {
            Document document = new Document(object);
            mongoCollection.insertOne(document);
        }
    }
}
