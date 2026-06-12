Feature: Leer credenciales desde JSON

Scenario: Leer credenciales

* def user = read('file:target/testdata/user.json')

* print user.username
* print user.password