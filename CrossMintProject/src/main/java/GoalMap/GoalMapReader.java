package GoalMap;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GoalMapReader {
  public List<ToDraw> readGoalMap(String candidateId) {
    List<ToDraw> toDrawList = new ArrayList<>();
    try {
      // Make API call
      String apiUrl = "https://challenge.crossmint.io/api/map/"+candidateId+"/goal";
      URL url = new URL(apiUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      // Read API response
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();

      // Parse JSON response
      JSONObject jsonResponse = new JSONObject(response.toString());
      JSONArray goalArray = jsonResponse.getJSONArray("goal");

      // Iterate over the array to identify shapes
      for (int row = 0; row < goalArray.length(); row++) {
        JSONArray rowArray = goalArray.getJSONArray(row);
        for (int col = 0; col < rowArray.length(); col++) {
          String cellValue = rowArray.getString(col);
          if (!cellValue.equals("SPACE")) {
            // Add ToDraw object to the list
            toDrawList.add(new ToDraw(row, col, cellValue));
          }
        }
      }


      // Close the connection
      connection.disconnect();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return  toDrawList;  // Return a placeholder list for now
  }
}
