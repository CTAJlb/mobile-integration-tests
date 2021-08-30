Feature: Feed

  @tier2 @oldOs
  Scenario: Update Bookshelf List
    When I add "Digital Public Library of America" account from welcomeScreen
      And I open categories by chain and chain starts from CategoryScreen:
      | Fiction |
      | Drama   |
      And DOWNLOAD book from subcategory list view and save it as 'bookInfo'
      And I open Books
    Then Book 'bookInfo' is present in Books List
      And Count of books is equal to 1
    When I refresh list of books
    Then Book 'bookInfo' is present in Books List
      And Count of books is equal to 1