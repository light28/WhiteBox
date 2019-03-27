Feature: User operations feature

  Scenario: Ability for user to register
    When send user registration request with attributes:
      | firstName | Oleh                 |
      | lastName  | Maksymuk             |
      | email     | oleg030992@yandex.ru |
      | password  | Qwerty123456         |
    Then user model exists in database with attributes:
      | firstName | Oleh                 |
      | lastName  | Maksymuk             |
      | email     | oleg030992@yandex.ru |
      | password  | Qwerty123456         |

  Scenario: Ability for user to log in
    When send user login request with attributes:
      | email    | tommy.johnson@gmail.com |
      | password | 1111                    |
    Then session contains info about session user:
      | firstName | Tommy                   |
      | lastName  | Johnson                 |
      | email     | tommy.johnson@gmail.com |
      | userRole  | USER                    |

