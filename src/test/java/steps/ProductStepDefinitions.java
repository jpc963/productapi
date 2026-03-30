package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductStepDefinitions {
  @Autowired
  private TestRestTemplate restTemplate;

  private Map<String, Object> payload;
  private ResponseEntity<Map<String, Object>> response;

  @Given("que eu monto um produto válido")
  public void queEuMontoUmProdutoValido() {
    payload = new HashMap<>();
    payload.put("id", 195);
    payload.put("title", "Hyaluronic Acid Serum");
    payload.put("price", 19);
    payload.put("discountPercentage", 13.31);
    payload.put("stock", 110);
    payload.put("rating", 4.83);
    payload.put("images", Arrays.asList(
        "https://i.dummyjson.com/data/products/16/1.png",
        "https://i.dummyjson.com/data/products/16/2.webp",
        "https://i.dummyjson.com/data/products/16/3.jpg",
        "https://i.dummyjson.com/data/products/16/4.jpg",
        "https://i.dummyjson.com/data/products/16/thumbnail.jpg"));
    payload.put("thumbnail", "https://i.dummyjson.com/data/products/16/thumbnail.jpg");
    payload.put("description",
        "L'Oreal Paris introduces Hyaluron Expert Replumping Serum formulated with 1.5% Hyaluronic Acid");
    payload.put("brand", "L'Oreal Paris");
    payload.put("category", "skincare");
  }

  @Given("que eu monto um produto inválido sem id e title")
  public void queEuMontoUmProdutoInvalidoSemIdETitle() {
    payload = new HashMap<>();
    payload.put("price", 19);
    payload.put("discountPercentage", 13.31);
    payload.put("stock", 110);
    payload.put("rating", 4.83);
    payload.put("images", Arrays.asList(
        "https://i.dummyjson.com/data/products/16/1.png",
        "https://i.dummyjson.com/data/products/16/2.webp",
        "https://i.dummyjson.com/data/products/16/3.jpg",
        "https://i.dummyjson.com/data/products/16/4.jpg",
        "https://i.dummyjson.com/data/products/16/thumbnail.jpg"));
    payload.put("thumbnail", "https://i.dummyjson.com/data/products/16/thumbnail.jpg");
    payload.put("description",
        "L'Oreal Paris introduces Hyaluron Expert Replumping Serum formulated with 1.5% Hyaluronic Acid");
    payload.put("brand", "L'Oreal Paris");
    payload.put("category", "skincare");
  }

  @Given("eu envio uma requisição GET para o endpoint {string}")
  public void euEnvioUmaRequisicaoGetPara(String endpoint) {
    response = restTemplate.exchange(endpoint, HttpMethod.GET, null,
        new ParameterizedTypeReference<Map<String, Object>>() {
        });
  }

  @When("eu envio uma requisição POST para o endpoint {string}")
  public void euEnvioUmaRequisicaoPostPara(String endpoint) {
    HttpEntity<Map<String, Object>> req = new HttpEntity<>(payload);
    response = restTemplate.exchange(endpoint, HttpMethod.POST, req,
        new ParameterizedTypeReference<Map<String, Object>>() {
        });
  }

  @Then("o status da resposta deve ser {int}")
  public void oStatusDaRespostaDeveSer(Integer expectedStatus) {
    assertEquals(expectedStatus.intValue(), response.getStatusCodeValue());
  }

  @Then("O produto não será criado e o status da resposta deve ser {int}")
  public void oProdutoNaoSeraCriadoEOStatusDaRespostaDeveSer(Integer expectedStatus) {
    assertEquals(expectedStatus.intValue(), response.getStatusCodeValue());
  }

  @And("a resposta deve conter o produto criado com um ID gerado")
  public void aRespostaDeveConterOProdutoCriadoComUmIdGerado() {
    Map<String, Object> responseBody = response.getBody();
    assertNotNull(responseBody);
    assertNotNull(responseBody.get("id"));
    assertEquals(payload.get("id"), responseBody.get("id"));
  }

  @And("a resposta deve conter os detalhes do produto com ID {int}")
  public void aRespostaDeveConterOsDetalhesDoProdutoComId(Integer expectedId) {
    Map<String, Object> responseBody = response.getBody();
    assertNotNull(responseBody);
    assertEquals(expectedId, (responseBody.get("id")));
  }

}