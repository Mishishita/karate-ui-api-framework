Feature: Login API

Scenario: Login exitoso
    Given url "https://reqres.in/api/login"
    And request
    """{"email": "eve.holt", 
    "password": "cityslicka"}"""
    When method POST
    Then status 200
    And match response.token != null

    * print response
