@e2e
Feature: End To End Banking Flow

Scenario: Flujo completo
    * def loginData = call read('classpath:api/features/login.feature')
    * def accountData = call read('classpath:api/features/create-account.feature') loginData
   
    * call read('classpath:api/features/deposit.feature') accountData
    * call read('classpath:api/features/transfer.feature') accountData