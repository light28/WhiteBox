Feature: Epam homework feature

  Scenario:  user is exist in database
    When send user login request:
      | email     | vlad_galakas@gmail.com |
      | password  | 1111                   |
    Then user model exists in database:
      | firstName | Vlad                   |
      | lastName  | Galakas                |
      | email     | vlad_galakas@gmail.com |
      | password  | 1111                   |

  Scenario:  user is not exist in database
    When send user login request:
      | email     | vlad_galakas@gmail.com |
      | password  | 12345                  |
    Then user model exists in database:
      | firstName | Vlad                   |
      | lastName  | Galakas                |
      | email     | vlad_galakas@gmail.com |
      | password  | 1111                   |