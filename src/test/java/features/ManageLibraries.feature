Feature: Manage Libraries

  @tier1 @oldOs
  Scenario: Add Library
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Account 'LYRASIS Reads' is present on Accounts screen

  @tier1 @oldOs
  Scenario: Switch Library Catalogs
    When I add "Digital Public Library of America" account from welcomeScreen
      And I add 'LYRASIS Reads' account
      And Catalog is opened
      And I get names of books on screen and save them as 'nameOfBooks'
      And I switch to 'LYRASIS Reads' from side menu
    Then Category rows are loaded
      And List of books on screen is not equal to list of books saved as 'nameOfBooks'

  @tier1 @exclude_ios @oldOs
  Scenario: Remove library
    When I add "LYRASIS Reads" account from welcomeScreen
      And I add 'Digital Public Library of America' account
      And I remove 'Digital Public Library of America' account
    Then Account 'Digital Public Library of America' is not present on Accounts screen

  @tier1 @exclude_android @oldOs
  Scenario: Remove library(IOS)
    When I add "LYRASIS Reads" account from welcomeScreen
    And I add 'Digital Public Library of America' account
    And I switch to 'LYRASIS Reads' from side menu
    And I remove 'Digital Public Library of America' account
    Then Account 'Digital Public Library of America' is not present on Accounts screen

  @tier2 @oldOs
  Scenario: Switch library bookshelf
    When I add "Digital Public Library of America" account from welcomeScreen
      And I add 'LYRASIS Reads' account
      And Catalog is opened
      And I switch to 'Digital Public Library of America' from side menu
      And I open categories by chain and chain starts from CategoryScreen:
        |2021 New Public Domain Books|
      And DOWNLOAD book from Subcategory List view and save it as 'bookInfo'
      And I open Books
    Then Book 'bookInfo' is present in Books List
    When I open Catalog
      And I return to previous screen for epub and pdf
      And I switch to 'LYRASIS Reads' from side menu
      And I open Books
    Then No books are present in Books list

  @logout @returnBooks @tier2 @oldOs
  Scenario: Switch Library Reservations
    When I add "Digital Public Library of America" account from welcomeScreen
    When I add 'LYRASIS Reads' account
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS Reads' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Open 'EBOOK' book with RESERVE button from Subcategory List View with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details view at the action button RESERVE
    Then I check that book contains CANCEL action button on book details view
    When I open Holds
    Then Holds feed is loaded
      And Book 'bookInfo' is present in Holds List
    When I open Catalog
      And I open Catalog
      And I switch to 'Digital Public Library of America' from side menu
      And Open Holds
    Then Holds feed is loaded
      And No books are present in Holds list

  @logout @tier2 @oldOs
  Scenario: Store library card
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Account 'LYRASIS Reads' is present on Accounts screen
    When I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open account 'LYRASIS Reads'
      And I click the log out button on the account screen
    Then Logout is performed successfully
