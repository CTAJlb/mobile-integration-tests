Feature: Book Transactions

  @logout @returnBooks @tier2
  Scenario: Hold from Bookshelf list
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
      And I RESERVE book with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain CANCEL button at catalog books screen
    When I open Holds
    Then Book 'bookInfo' is present in Holds List

  @tier2
  Scenario: Download from Book detail view
    When I add "Digital Public Library of America" account from welcomeScreen
      And I open Catalog
      And I open category by chain:
        | Fiction |
        | Drama   |
    When I open the book details for the subsequent DOWNLOAD and save it as 'bookInfo'
    Then I check that opened book contains READ button at book details screen

  @tier2
  Scenario: Download from Bookshelf list
    When I add "Digital Public Library of America" account from welcomeScreen
      And I open Catalog
      And I open category by chain:
        | Fiction |
        | Drama   |
      And DOWNLOAD book from subcategory screen and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button at catalog books screen

  @tier2
  Scenario: Read from Bookshelf list
    When I add "Digital Public Library of America" account from welcomeScreen
      And I open Catalog
      And I open category by chain:
        | Fiction |
        | Drama   |
      And DOWNLOAD book from subcategory screen and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button at catalog books screen
    When I open Books
      And I Read book 'bookInfo'
    Then Book 'bookInfo' is present on screen

  @tier2
  Scenario: Delete book
    When I add "Digital Public Library of America" account from welcomeScreen
      And I open Catalog
      And I open category by chain:
        | Fiction |
        | Drama   |
      And DOWNLOAD book from subcategory screen and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button at catalog books screen
    When I open Books
      And I open book 'bookInfo' details by clicking on cover
      And I delete book from book details screen
      And I open Books
    Then Book 'bookInfo' is not present in Books List

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Check out from Book Detail View
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open category by chain:
        | Nonfiction         |
        | Biography & Memoir |
    When I open the book details for the subsequent GET and save it as 'bookInfo'
    Then I check that opened book contains READ button at book details screen

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Return book
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open category by chain:
        | Nonfiction   |
        | Art & Design |
        | Art          |
      And I open the book details for the subsequent GET and save it as 'bookInfo'
    Then I check that opened book contains READ button at book details screen
    When I open Books
    Then Book 'bookInfo' is present in Books List
    When I open book 'bookInfo' details by clicking on cover
      And Press on the book details screen at the action button RETURN
    Then I check that the action button text equal to the GET
    When I open Books
    Then Book 'bookInfo' is not present in Books List

  @logout @returnBooks @tier2
  Scenario: Remove a Reserved Book from Bookshelf list
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      Then Subcategory screen is present
      And I RESERVE book with title 'bookNameInfo' and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain CANCEL button at catalog books screen
    When I click on the book 'bookInfo' button CANCEL on catalog books screen
    Then Book saved as 'bookInfo' should contain RESERVE button at catalog books screen

  @logout @returnBooks @tier2
  Scenario: Remove a Reserved Book from Book Detail View
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When I open 'EBOOK' book 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When Press on the book details screen at the action button CANCEL
    Then I check that the action button text equal to the RESERVE

  @logout @returnBooks @tier2 @exclude_ios
  Scenario: Remove a Reserved Book from Holds screen
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When I open 'EBOOK' book 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When I open Holds
    Then Book 'bookInfo' is present in Holds List
    When I click on the book 'bookInfo' button CANCEL on the holds screen
    Then Book saved as 'bookInfo' should contain RESERVE button at the hold screen

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Remove a Reserved Book from Holds screen (iOS)
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When I open 'EBOOK' book 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When I open Holds
    Then Book 'bookInfo' is present in Holds List
    When I click on the book 'bookInfo' button CANCEL on the holds screen
    Then Book 'bookInfo' is not present in Holds List

  @logout @returnBooks @tier3 @exclude_android
  Scenario: Cancel remove
    When I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When I open 'EBOOK' book 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When I open Holds
    Then Book 'bookInfo' is present in Holds List
    When I click on the book 'bookInfo' button CANCEL on the holds screen and don't click on the popup button
    Then I click at the popup approve CANCEL the button CANCEL_POPUP
      And Book saved as 'bookInfo' should contain CANCEL button at the hold screen
