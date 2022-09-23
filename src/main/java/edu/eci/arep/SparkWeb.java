package edu.eci.arep;


import static spark.Spark.port;
import static spark.Spark.get;

public class SparkWeb {

    public static void main(String... args){
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
