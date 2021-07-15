Feature: Feed

  @tier2
  Scenario: Update Bookshelf List
    When I add "Acton Public Library" account from welcomeScreen
      And I switch to 'Digital Public Library of America' from side menu
      And I open category by chain:
      | Fiction |
      | Drama   |
      And DOWNLOAD book from subcategory list view and save it as 'bookInfo'
      And I open Books
    Then Book 'bookInfo' is present in Books List
      And Count of books is equal to 1
    When I refresh list of books
    Then Book 'bookInfo' is present in Books List
      And Count of books is equal to 1

    #delete after checking
  @tier2 @exclude_ios
  Scenario: Add Digital Public Library of America account
    When I add "Digital Public Library of America" account from welcomeScreen