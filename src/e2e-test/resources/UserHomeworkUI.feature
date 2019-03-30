Feature: User login

  Scenario: Ability for user to login (success)
    Given browser opened at http://localhost:8080 url
    When user submit login form with attributes:
      | email     | vlad_galakas@gmail.com        |
      | password  | 1111                          |
    Then account details displayed on accounts grid page
      | accountCode | epam-lab-group              |
      | accountName | Vlad Galakas                |

  Scenario: Ability for user to login (invalid login)
    Given browser opened at http://localhost:8080 url
    When user submit login form with attributes:
      | email     | vlad@gmail.com                |
      | password  | 1111                          |
    Then account details displayed on accounts grid page
      | accountCode | epam-lab-group              |
      | accountName | Vlad Galakas                |

  Scenario: Ability for user to login (invalid password)
    Given browser opened at http://localhost:8080 url
    When user submit login form with attributes:
      | email     | vlad_galakas@gmail.com        |
      | password  | 11115                         |
    Then account details displayed on accounts grid page
      | accountCode | epam-lab-group              |
      | accountName | Vlad Galakas                |
