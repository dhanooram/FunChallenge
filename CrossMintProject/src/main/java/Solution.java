import GoalMap.GoalMapReader;
import GoalMap.ToDraw;
import SketchMegaverse.DrawingManager;
import java.util.List;




public class Solution {
  public static void main(String[] args) {
    String apiUrl = "https://challenge.crossmint.io";
    // Create goal map reader
    GoalMapReader goalMapReader = new GoalMapReader();
    String candidateId = "088546ad-c321-4910-88f2-3837876c037d";
    List<ToDraw> toDrawList = goalMapReader.readGoalMap(candidateId);
    for (ToDraw toDraw : toDrawList) {
      System.out.println(toDraw.getRow()+" "+ toDraw.getCol()+" "+toDraw.getShape());
    }

    DrawingManager drawingManager = new DrawingManager();
    drawingManager.drawShapes(toDrawList, candidateId);
    //new PolyanetHandler("https://challenge.crossmint.io/api/polyanets").create(2,7,candidateId);

  }
}
