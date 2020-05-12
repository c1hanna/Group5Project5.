package bsu.comp152;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class DataHandler {
    private HttpClient dataGrabber;
    private String webLocation;

    public DataHandler(String webLocation) {
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }
    public ArrayList<jobDataType> getData(){
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create("https://jobs.github.com/positions.json?page=1")).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }catch(IOException e){
            System.out.println("Error connecting to network or site"); }
        catch (InterruptedException e){ System.out.println("Connection to site broken"); }
        if (response == null ){
            System.out.println("Something went terribly wrong, ending program");
            System.exit(-1);
        }
        var usefulData = response.body();
        var jsonInterpreter = new Gson();
        var jobData = jsonInterpreter.fromJson(usefulData, responseDataType.class);
        return jobData.results;
    }
    class responseDataType {
        String title;
        float version;
        String href;
        ArrayList<jobDataType> results;
    }
    class jobDataType {
        String field;
        String href;
        String company;
        String title;
        String jobLocation;
        String applyURL;
        String description;
        String thumbnail;
        @Override
        public String toString() {
            return "Job Field: " +field+ "\nurl:" +href+ "Job Title:" +title;
        }
    }
}

//comment3
