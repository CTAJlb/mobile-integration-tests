Feature: Distributors

  @logout @returnBooks @tier2
  Scenario Outline: Hold from Book Detail View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click RESERVE action button on book details screen
    Then I check that book contains REMOVE action button on book details screen

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Axis 360           | EBOOK     | eBooks     |
      | Axis 360           | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |
      | Biblioboard        | EBOOK     | eBooks     |

  @logout @returnBooks @tier2
  Scenario Outline: Check out from Book Detail View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains <buttonBookDetailsView> action button on book details screen
    When Click <buttonBookDetailsView> action button on book details screen
      And Wait for 3 seconds
    Then Book 'bookInfo' with <bookType> type is present on epub or pdf or audiobook screen

    Scenarios:
      | distributor        | bookType  | tabName    | buttonBookDetailsView |
      | Bibliotheca        | EBOOK     | eBooks     | READ                  |
      | Bibliotheca        | AUDIOBOOK | Audiobooks | LISTEN                |
      | Axis 360           | EBOOK     | eBooks     | READ                  |
      | Axis 360           | AUDIOBOOK | Audiobooks | LISTEN                |
      | Palace Marketplace | EBOOK     | eBooks     | READ                  |
      | Palace Marketplace | AUDIOBOOK | Audiobooks | LISTEN                |
      | Biblioboard        | EBOOK     | eBooks     | READ                  |
      | Biblioboard        | AUDIOBOOK | Audiobooks | LISTEN                |

  @logout @returnBooks @tier2
  Scenario Outline: Return book from Book Detail View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
      And Click RETURN action button on book details screen
    Then I check that book contains GET action button on book details screen

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |
      | Axis 360           | EBOOK     | eBooks     |
      | Axis 360           | AUDIOBOOK | Audiobooks |
      | Biblioboard        | EBOOK     | eBooks     | 
      | Biblioboard        | AUDIOBOOK | Audiobooks | 
    

  @logout @returnBooks @tier2
  Scenario Outline: Check of canceling the downloading from book details view
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
    And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button and cancel downloading by click CANCEL button on book detail screen
    Then I check that book contains DOWNLOAD action button on book details screen
      And I check that book contains RETURN action button on book details screen

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |
      | Axis 360           | EBOOK     | eBooks     |
      | Axis 360           | AUDIOBOOK | Audiobooks |
      | Biblioboard        | EBOOK     | eBooks     |
      | Biblioboard        | AUDIOBOOK | Audiobooks |