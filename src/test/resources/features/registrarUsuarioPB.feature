Feature: Registro Parabank

Scenario: Registrar usuario dinámico

    # Logs Karate
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

    # Logger Java
    * def LogUtil = Java.type('utils.LogUtil')

    # Usuario único
    * def id = java.lang.System.currentTimeMillis()
    * def username = 'user' + id

    * eval LogUtil.info('=======================================')
    * eval LogUtil.info('INICIO REGISTRO DE USUARIO')
    * eval LogUtil.info('Usuario generado: ' + username)

    Given url 'https://parabank.parasoft.com/parabank/register.htm'

    And form field customer.firstName = 'Maria'
    And form field customer.lastName = 'Tinoco'
    And form field customer.address.street = 'Lima'
    And form field customer.address.city = 'Lima'
    And form field customer.address.state = 'Lima'
    And form field customer.address.zipCode = '15001'
    And form field customer.phoneNumber = '999999999'
    And form field customer.ssn = id

    And form field customer.username = username
    And form field customer.password = 'test123'
    And form field repeatedPassword = 'test123'

    When method post

    * eval LogUtil.info('STATUS HTTP: ' + responseStatus)

    * print 'STATUS:', responseStatus
    * print 'USUARIO:', username

    # Guardar respuesta completa en log
    * eval LogUtil.info('RESPUESTA RECIBIDA')
    * eval LogUtil.info(response)

    Then status 200

    # Validación funcional
    And match response contains 'Your account was created successfully'

    * eval LogUtil.info('REGISTRO EXITOSO')
    * eval LogUtil.info('=======================================')