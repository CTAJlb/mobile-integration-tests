Feature: Feed

  @tier2 @oldOs
  Scenario: Update Bookshelf List
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open categories by chain and chain starts from CategoryScreen:
        | Fiction            |
        | Historical Fiction |
      And DOWNLOAD book from Subcategory List view and save it as 'bookInfo'
      And I open Books
    Then Book 'bookInfo' with READ action button is present on Books Screen
      And Count of books is equal to 1
    When I refresh list of books
    Then Book 'bookInfo' with READ action button is present on Books Screen
      And Count of books is equal to 1