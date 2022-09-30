package edu.eci.arep;
import java.io.IOException;

import static spark.Spark.*;



public class SparkWebApp {

    private static int roundRobin=0;

    public static void main(String... args){
        port(getPort());
        staticFiles.location("/public");
        post("message", (req,res) -> {
            String response=null;
            System.out.println("posteando");
            System.out.println(req.body());
            try {
                response = HTTPPostConnection.post(req.body());
            }catch (IOException ex){
                ex.printStackTrace();
            }
            return response;});
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
