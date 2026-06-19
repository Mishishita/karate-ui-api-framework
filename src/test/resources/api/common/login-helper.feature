@smoke
Feature: Common Login

  Scenario:

    * def user = read('file:target/testdata/user.json')

    Given url 'https://parabank.parasoft.com/parabank/services/bank'
    And path 'login', user.username, user.password
    When method get
    Then status 200

    * def customerId = response.customer.id
    * print 'customerId:',customerId
    * print 'Login reutilizable ejecutado'

    #Actualmente no se usa este login pero lo dejaremos si más adelante se necesita.
