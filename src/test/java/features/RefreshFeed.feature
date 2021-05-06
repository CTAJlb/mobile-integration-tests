Feature: Feed

  @tier2
  Scenario: Update Bookshelf List
    Given I find "Digital Public Library of America" library
    When I open Catalog
      And I switch to 'Digital Public Library of America' from side menu
    When I open category by chain:
      | Fiction |
      | Drama   |
      And DOWNLOAD book and save it as 'bookInfo'
      And I open Books
    Then Book 'bookInfo' is present in Books List
      And Count of books is equal to 1
    When I refresh list of books
    Then Book 'bookInfo' is present in Books List
      And Count of books is equal to 1