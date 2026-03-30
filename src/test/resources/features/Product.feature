Feature: Produto API

  Scenario: Criar um novo produto com sucesso pelo método POST
    Given que eu monto um produto válido
    When eu envio uma requisição POST para o endpoint "/products/add"
    Then o status da resposta deve ser 201
    And a resposta deve conter o produto criado com um ID gerado

  Scenario: Falha na criação de um produto com dados inválidos
    Given que eu monto um produto inválido sem id e title
    When eu envio uma requisição POST para o endpoint "/products/add"
    Then O produto não será criado e o status da resposta deve ser 400

  Scenario: Sucesso na consulta de um produto existente pelo ID
    Given eu envio uma requisição GET para o endpoint "/products/195"
    Then o status da resposta deve ser 200
    And a resposta deve conter os detalhes do produto com ID 195