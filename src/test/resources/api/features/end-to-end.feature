Feature: End To End Banking Flow

Scenario: Flujo completo
    * call read('classpath:api/features/login.feature')
    * call read('classpath:api/features/create-account.feature')
    * call read('classpath:api/features/deposit.feature')
    * call read('classpath:api/features/transfer.feature')
