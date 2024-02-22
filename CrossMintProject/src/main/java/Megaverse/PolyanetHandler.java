package Megaverse;

public class PolyanetHandler extends AstralObjectHandler {
  public PolyanetHandler(String apiUrl) {
    super();
    this.apiUrl=apiUrl;
  }

  @Override
  public void create(int row, int column, String candidateId) {
    System.out.println("Creating Polyanet at (" + row + ", " + column + ")");
    String url = getApiUrl();
    String jsonBody = createJsonBody(row, column, candidateId);
    makeHttpRequest(url, "POST", jsonBody);
    System.out.println("Creating Polyanet at (" + row + ", " + column + ")");
  }

  @Override
  public void delete(int row, int column, String candidateId) {
    String url = getApiUrl();
    String jsonBody = createJsonBody(row, column, candidateId);
    makeHttpRequest(url, "DELETE", jsonBody);
    System.out.println("Deleting Polyanet at (" + row + ", " + column + ")");
  }

  @Override
  protected String getApiUrl() {
    return this.apiUrl;
  }
}