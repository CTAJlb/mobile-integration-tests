Feature: Feed

  @tier2 @oldOs
  Scenario: Update Bookshelf List
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open categories by chain and chain starts from CategoryScreen:
        | Fiction            |
        | Historical Fiction |
      And DOWNLOAD book from Subcategory List view and save it as 'bookInfo'
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And Amount of books is equal to 1
    When I refresh list of books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And Amount of books is equal to 1