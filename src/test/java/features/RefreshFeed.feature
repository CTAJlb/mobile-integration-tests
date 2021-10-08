Feature: Feed

  @tier2 @exclude_android @oldOs
  Scenario: Update Bookshelf List(IOS)
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open categories by chain and chain starts from CategoryScreen:
        |Halloween Reads|
      And Click GET action button on the first EBOOK book on catalog books screen and save book as 'bookInfo'
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And Amount of books is equal to 1
    When I refresh list of books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And Amount of books is equal to 1

  @tier2 @exclude_ios @oldOs
  Scenario: Update Bookshelf List(ANDROID)
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open categories by chain and chain starts from CategoryScreen:
        |Halloween Reads|
      And Click DOWNLOAD action button on the first EBOOK book on catalog books screen and save book as 'bookInfo'
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And Amount of books is equal to 1
    When I refresh list of books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And Amount of books is equal to 1