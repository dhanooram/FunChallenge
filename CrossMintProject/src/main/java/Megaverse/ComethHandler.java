package Megaverse;

public class ComethHandler extends  AstralObjectHandler{

  public void setDirections(String directions) {
    this.directions = directions;
  }

  String directions;
  public ComethHandler(String apiUrl) {
    super();
    this.apiUrl=apiUrl;
    this.directions="UP";
  }


  @Override
  public void create(int row, int column, String candidateId) {
    System.out.println("Creating Cometh at (" + row + ", " + column + ")");
    String url = getApiUrl();
    String jsonBody = createJsonBody(row, column, candidateId);
    System.out.println(jsonBody);
    makeHttpRequest(url, "POST", jsonBody);
    System.out.println("Creating Cometh at (" + row + ", " + column + ")");
  }

  @Override
  public void delete(int row, int column, String candidateId) {
    String url = getApiUrl();
    String jsonBody = createJsonBody(row, column, candidateId);
    makeHttpRequest(url, "DELETE", jsonBody);
    System.out.println("Deleting Cometh at (" + row + ", " + column + ")");
  }
  @Override
  protected String getApiUrl() {
    return this.apiUrl;
  }

  @Override
  public String createJsonBody(int row, int column, String candidateId) {
    return String.format("{\"row\": %d, \"column\": %d, \"candidateId\": \"%s\", \"direction\": \"%s\"}", row, column, candidateId, this.directions);
  }
}
