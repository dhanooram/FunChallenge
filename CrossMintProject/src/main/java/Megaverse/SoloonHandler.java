package Megaverse;

public class SoloonHandler extends  AstralObjectHandler{

  public void setColor(String color) {
    this.color = color;
  }

  String color;
  public SoloonHandler(String apiUrl) {
    super();
    this.apiUrl=apiUrl;
    this.color="white";
  }
  @Override
  public void create(int row, int column, String candidateId) {
    System.out.println("Creating Soloon at (" + row + ", " + column + ")");
    String url = getApiUrl();
    String jsonBody = createJsonBody(row, column, candidateId);
    makeHttpRequest(url, "POST", jsonBody);
    System.out.println("Creating Soloon at (" + row + ", " + column + ")");
  }

  @Override
  public void delete(int row, int column, String candidateId) {
    String url = getApiUrl();
    String jsonBody = createJsonBody(row, column, candidateId);
    makeHttpRequest(url, "DELETE", jsonBody);
    System.out.println("Deleting Soloon at (" + row + ", " + column + ")");
  }
  @Override
  protected String getApiUrl() {
    return this.apiUrl;
  }

  @Override
  public String createJsonBody(int row, int column, String candidateId) {
    return String.format("{\"row\": %d, \"column\": %d, \"candidateId\": \"%s\", \"color\": \"%s\"}", row, column, candidateId, this.color);
  }
}
