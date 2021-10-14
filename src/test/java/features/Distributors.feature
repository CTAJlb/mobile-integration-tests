Feature: Distributors

  @logout @returnBooks @tier2 @oldOs
  Scenario Outline: Hold from Book Detail View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And I press on the book details view at the action button RESERVE
    Then I check that book contains CANCEL_RESERVATION action button on book details view

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Axis 360           | EBOOK     | eBooks     |
      | Axis 360           | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |

  @logout @returnBooks @tier2 @oldOs
  Scenario Outline: Check out from Book Detail View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And I press on the book details view at the action button GET
    Then I check that book contains <buttonBookDetailsView> action button on book details view
    When I start reading or listening to a book with <bookType> type from book details view
    Then Book 'bookInfo' with <bookType> type is present on epub or pdf or audiobook screen

    Scenarios:
      | distributor        | bookType  | tabName    | buttonBookDetailsView |
      | Bibliotheca        | EBOOK     | eBooks     | READ                  |
      | Bibliotheca        | AUDIOBOOK | Audiobooks | LISTEN                |
      | Axis 360           | EBOOK     | eBooks     | READ                  |
      | Axis 360           | AUDIOBOOK | Audiobooks | LISTEN                |
      | Palace Marketplace | EBOOK     | eBooks     | READ                  |
      | Palace Marketplace | AUDIOBOOK | Audiobooks | LISTEN                |

  @logout @returnBooks @tier2 @oldOs
  Scenario Outline: Return book from Book Detail View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And I press on the book details view at the action button GET
      And I press on the book details view at the action button RETURN
    Then I check that book contains GET action button on book details view

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |
