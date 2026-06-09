Feature: API testing

Scenario: Consultar usuario

    Given url "https://pokeapi.co/api/v2/pokemon/pikachu"
    When method GET
    Then status 200

    * print response

    And match response.name == "pikachu"