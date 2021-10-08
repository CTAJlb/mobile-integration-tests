Feature: Manage Libraries

  @tier1 @oldOs
  Scenario: Add Library
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Account 'LYRASIS Reads' is present on Accounts screen

  @tier1 @oldOs
  Scenario: Switch Library Catalogs
    When I add "Palace Bookshelf" account from welcomeScreen
      And I get names of books on screen and save them as 'nameOfBooks'
      And I add 'LYRASIS Reads' account
    Then Category rows are loaded
      And List of books on screen is not equal to list of books saved as 'nameOfBooks'

  @tier1 @oldOs
  Scenario: Remove library
    When I add "LYRASIS Reads" account from welcomeScreen
    And I add 'Palace Bookshelf' account
    And I switch to 'LYRASIS Reads' from side menu
    And I remove 'Palace Bookshelf' account
    Then Account 'Palace Bookshelf' is not present on Accounts screen

  @tier2 @exclude_ios @oldOs
  Scenario: Switch library bookshelf(ANDROID)
    When I add "Palace Bookshelf" account from welcomeScreen
      And I add 'LYRASIS Reads' account
      And Catalog is opened
      And I switch to 'Palace Bookshelf' from side menu
       And I open categories by chain and chain starts from CategoryScreen:
        |Halloween Reads|
    And Click DOWNLOAD action button on the first EBOOK book on catalog books screen and save book as 'bookInfo'
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When I open Catalog
      And I return to previous screen for epub and pdf
      And I switch to 'LYRASIS Reads' from side menu
      And I open Books
    Then There are not books on books screen

  @tier2 @exclude_android @oldOs
  Scenario: Switch library bookshelf(IOS)
    When I add "Palace Bookshelf" account from welcomeScreen
      And I add 'LYRASIS Reads' account
      And Catalog is opened
      And I switch to 'Palace Bookshelf' from side menu
      And I open categories by chain and chain starts from CategoryScreen:
        |Halloween Reads|
      And Click GET action button on the first EBOOK book on catalog books screen and save book as 'bookInfo'
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When I open Catalog
      And I return to previous screen for epub and pdf
      And I switch to 'LYRASIS Reads' from side menu
      And I open Books
    Then There are not books on books screen

  @logout @returnBooks @tier2 @oldOs
  Scenario: Switch Library Reservations
    When I add "Internet Archive" account from welcomeScreen
    When I add 'LYRASIS Reads' account
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS Reads' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Open EBOOK book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details view at the action button RESERVE
    Then I check that book contains CANCEL_RESERVATION action button on book details view
    When I open Holds
    Then EBOOK book with CANCEL_RESERVATION action button and 'bookInfo' bookInfo is present on holds screen
    When I open Catalog
      And I open Catalog
      And I switch to 'Internet Archive' from side menu
      And I open Holds
      And There are not books on holds screen

  @logout @tier2 @oldOs
  Scenario: Store library card
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Account 'LYRASIS Reads' is present on Accounts screen
    When I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open account 'LYRASIS Reads'
      And I click the log out button on the account screen
    Then Logout is performed successfully
