package edu.eci.arep;


import edu.eci.arep.repository.MongoDbRepository;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class SparkWeb {


    public static void main(String... args){
        MongoDbRepository mongoDbRepository = new MongoDbRepository();
        port(getPort());
        get("message", (req,res) -> {
            mongoDbRepository.createConnection();

            List<String> messages = mongoDbRepository.getAllItems();

            mongoDbRepository.closeConnection();
            return messages;
        });
        post("message", (req,res) -> {
            mongoDbRepository.createConnection();

            if(req.body()!=null){
                mongoDbRepository.addItem(req.body());
            }

            List<String> messages = mongoDbRepository.getAllItems();

            mongoDbRepository.closeConnection();

            return messages;
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
