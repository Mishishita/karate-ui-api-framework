@e2e
Feature: End To End Banking Flow

Scenario: Flujo completo
    * def loginData = call read('classpath:api/features/login.feature')
   
    * call read('classpath:api/features/create-account.feature') loginData
    * call read('classpath:api/features/deposit.feature')
    * call read('classpath:api/features/transfer.feature')
