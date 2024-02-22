package SketchMegaverse;

import GoalMap.ToDraw;
import Megaverse.AstralObjectHandler;
import Megaverse.ComethHandler;
import Megaverse.PolyanetHandler;
import Megaverse.SoloonHandler;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public   class DrawingManager {

  // Limiting number of threads to prevent 429 Response
  private final int numThreads = 5; // Set the desired number of parallel threads

  public void drawShapes(List<ToDraw> toDrawList, String candidateId) {
    ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

    toDrawList.forEach(toDraw -> {
      executorService.execute(() -> {
        try {
          AstralObjectHandler handler = createHandler(toDraw.getShape());
          if (handler != null) {
            handler.create(toDraw.getRow(), toDraw.getCol(), candidateId);
          } else {
            // Handle unsupported shapes or log a message
          }

          Thread.sleep(3000); // To avoid too many requests at the same time
        } catch (InterruptedException e) {
          e.printStackTrace(); // Handle the interruption exception
        }
      });
    });

    executorService.shutdown();
  }

  private AstralObjectHandler createHandler(String shape) {

    if(shape.contains("POLYANET")){
      return new PolyanetHandler("https://challenge.crossmint.io/api/polyanets");
    }
    else if(shape.contains("COMETH"))
    {
      ComethHandler obj= new ComethHandler("https://challenge.crossmint.io/api/comeths");
      obj.setDirections(shape.split("_")[0].toLowerCase());
      return obj;
    }
    else if(shape.contains("SOLOON"))
    {
      SoloonHandler obj= new SoloonHandler("https://challenge.crossmint.io/api/soloons");
      obj.setColor(shape.split("_")[0].toLowerCase());
      return obj;
    }

    return null;
  }
}
