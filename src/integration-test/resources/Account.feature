Feature: Account operations feature


  Background: Log in
    Given user tommy.johnson@gmail.com logs in into app

  Scenario: Ability for user to create new account
    When send create-update account request with attributes:
      | accountCode  | adidas-ftw                                        |
      | accountName  | Adidas Footwear department                        |
      | accountImage | http://pngimg.com/uploads/adidas/adidas_PNG18.png |
    Then account model exists in database with attributes:
      | code       | adidas-ftw                                        |
      | name       | Adidas Footwear department                        |
      | urlToImage | http://pngimg.com/uploads/adidas/adidas_PNG18.png |
      | balance    | 0.0                                               |
    And user tommy.johnson@gmail.com has adidas-ftw account assigned


  Scenario: Ability for user to update existing account
    Given account model exists in database with attributes:
      | code       | adidas-ftw                                        |
      | name       | Adidas Footwear department                        |
      | urlToImage | http://pngimg.com/uploads/adidas/adidas_PNG18.png |
      | balance    | 0.0                                               |
    When send create-update account request with attributes:
      | accountCode    | adidas-ftw                                        |
      | accountName    | Adidas Footwear department 2                      |
      | accountImage   | http://pngimg.com/uploads/adidas/adidas_PNG18.png |
      | accountBalance | 250000.00                                         |
    Then account model exists in database with attributes:
      | code       | adidas-ftw                                        |
      | name       | Adidas Footwear department 2                      |
      | urlToImage | http://pngimg.com/uploads/adidas/adidas_PNG18.png |
      | balance    | 250000.00                                         |