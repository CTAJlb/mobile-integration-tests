Feature: Book Transactions

  Background:
    Given I find "Digital Public Library of America" library

  @logout @returnBooks @tier2 @xml
  Scenario Outline: Hold from Book Detail View (feed) XML
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'unavailable' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
    Then Subcategory screen is present
    When I open '<bookType>' book 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When I open Holds
    Then Book 'bookInfo' is present in Holds List

    Scenarios:
      | distributor | bookType  | tabName    |
      | Bibliotheca | EBOOK     | eBooks     |
      | Bibliotheca | AUDIOBOOK | Audiobooks |
      | Axis 360    | EBOOK     | eBooks     |
      | Axis 360    | AUDIOBOOK | Audiobooks |

  @logout @returnBooks @tier2 @fixed
  Scenario: Hold from Bookshelf list
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
    When I search for 'The Land of Stories Complete Gift Set'
      And I RESERVE book by name 'The Land of Stories Complete Gift Set' and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain CANCEL button at catalog books screen
    When I open Holds
    Then Book 'bookInfo' is present in Holds List

  @tier2
  Scenario: Download from Book detail view
    When I open Catalog
      And I open category by chain:
        | Fiction |
        | Drama   |
    When I open the book details for the subsequent DOWNLOAD and save it as 'bookInfo'
    Then I check that opened book contains READ button at book details screen

  @tier2
  Scenario: Download from Bookshelf list
    When I open Catalog
      And I open category by chain:
        | Fiction |
        | Drama   |
      And DOWNLOAD book and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button at catalog books screen

  @tier2
  Scenario: Read from Bookshelf list
    When I open Catalog
      And I open category by chain:
        | Fiction |
        | Drama   |
      And DOWNLOAD book and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button at catalog books screen
    When I open Books
      And I Read book 'bookInfo'
    Then Book 'bookInfo' is present on screen

  @tier2
  Scenario: Delete book
    When I open Catalog
      And I open category by chain:
        | Fiction |
        | Drama   |
      And DOWNLOAD book and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button at catalog books screen
    When I open Books
      And I open book 'bookInfo' details by clicking on cover
      And I delete book from book details screen
      And I open Books
    Then Book 'bookInfo' is not present in Books List

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Check out from Book Detail View
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open category by chain:
        | Nonfiction         |
        | Biography & Memoir |
    When I open the book details for the subsequent GET and save it as 'bookInfo'
    Then I check that opened book contains READ button at book details screen

  @logout @returnBooks @tier2 @xml
  Scenario Outline: Check out from Book Detail View (feed) XML
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
    Then Subcategory screen is present
    When I open '<bookType>' book 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And Get book on the book details screen
    Then Opened book contains read button at book details screen
    When I open Books
    Then Book 'bookInfo' is present in Books List
    When I open book 'bookInfo' details by clicking on cover
    Then Opened book contains read button at book details screen
    When I read <bookType> book
    Then Reader screen for <bookType> type book 'bookInfo' is present

    Scenarios:
      | distributor | bookType  | tabName    |
      | Bibliotheca | EBOOK     | eBooks     |
      | Bibliotheca | AUDIOBOOK | Audiobooks |
      | Axis 360    | EBOOK     | eBooks     |
      | Axis 360    | AUDIOBOOK | Audiobooks |

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Return book
    When I add 'LYRASIS' account
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

  @logout @returnBooks @tier2 @xml
  Scenario Outline: Return book (feed) XML
    When I add 'LYRASIS' account
    And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
    And I switch to 'LYRASIS' from side menu
    And I open search modal
    And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
    And I switch to '<tabName>' catalog tab
    Then Subcategory screen is present
    When I open '<bookType>' book 'bookNameInfo' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
    And Get book on the book details screen
    Then Opened book contains read button at book details screen
    When I open Books
    Then Book 'bookInfo' is present in Books List
    When I open book 'bookInfo' details by clicking on cover
    And Press on the book details screen at the action button RETURN
    Then I check that the action button text equal to the GET
    When I open Books
    Then Book 'bookInfo' is not present in Books List

    Scenarios:
      | distributor | bookType  | tabName    |
      | Bibliotheca | EBOOK     | eBooks     |
      | Bibliotheca | AUDIOBOOK | Audiobooks |

  @logout @returnBooks @tier2 @fixed
  Scenario: Remove a Reserved Book from Bookshelf list
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
    When I search for 'A Dream of Home'
      And I RESERVE book by name 'A Dream of Home' and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain CANCEL button at catalog books screen
    When I click on the book 'bookInfo' button CANCEL on catalog books screen
    Then Book saved as 'bookInfo' should contain RESERVE button at catalog books screen

  @logout @returnBooks @tier2 @fixed
  Scenario: Remove a Reserved Book from Book Detail View
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
    When I search for 'Imagine You Are An Aluminum Atom'
      And I open '<bookType>' book with name 'Imagine You Are An Aluminum Atom' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When Press on the book details screen at the action button CANCEL
    Then I check that the action button text equal to the RESERVE

  @logout @returnBooks @tier2 @exclude_ios @fixed
  Scenario: Remove a Reserved Book from Holds screen
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
    When I search for 'The Land of Stories Complete Gift Set'
      And I open 'EBOOK' book with name 'The Land of Stories Complete Gift Set' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When I open Holds
    Then Book 'bookInfo' is present in Holds List
    When I click on the book 'bookInfo' button CANCEL on the holds screen
    Then Book saved as 'bookInfo' should contain RESERVE button at the hold screen

  @logout @returnBooks @tier2 @exclude_android @fixed
  Scenario: Remove a Reserved Book from Holds screen (iOS)
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
    When I search for 'Song of Spider-Man'
      And I open 'EBOOK' book with name 'Song of Spider-Man' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When I open Holds
    Then Book 'bookInfo' is present in Holds List
    When I click on the book 'bookInfo' button CANCEL on the holds screen
    Then Book 'bookInfo' is not present in Holds List

  @logout @returnBooks @tier3 @exclude_android @fixed
  Scenario: Cancel remove
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
    When I search for 'Sesame Street 5-Minute Stories (Sesame Street)'
      And I open 'EBOOK' book with name 'Sesame Street 5-Minute Stories (Sesame Street)' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When I open Holds
    Then Book 'bookInfo' is present in Holds List
    When I click on the book 'bookInfo' button CANCEL on the holds screen and don't click on the popup button
    Then I click at the popup approve CANCEL the button CANCEL_POPUP
      And Book saved as 'bookInfo' should contain CANCEL button at the hold screen
