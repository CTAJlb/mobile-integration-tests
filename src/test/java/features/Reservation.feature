Feature: Reservation of book in LYRASIS

  @logout @returnBooks @tier1
  Scenario: Hold from Subcategory List View and Remove a Reserved Book from Holds
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click RESERVE action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is present on catalog books screen
      And I close Book Details for IOSTab
    When I open Holds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is present on holds screen
    When Open EBOOK book with REMOVE action button and 'bookInfo' bookInfo on holds screen
      And Click REMOVE action button on book details screen
      And I open Holds
      And Wait for 7 seconds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is not present on holds screen

  @logout @returnBooks @tier1
  Scenario: Hold from Subcategory List View and Remove a Reserved Book from Subcategory List View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click RESERVE action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click REMOVE action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with RESERVE action button and 'bookInfo' bookInfo is present on catalog books screen

  @logout @returnBooks @tier1
  Scenario: Hold from Book Detail View and and Remove a Reserved Book from Holds
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Palace Marketplace' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Open EBOOK book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
    When Click RESERVE action button on book details screen
      And I close Book Details for IOSTab
    When I open Holds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is present on holds screen
    When Open EBOOK book with REMOVE action button and 'bookInfo' bookInfo on holds screen
      And Click REMOVE action button on book details screen
      And I open Holds
      And Wait for 7 seconds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is not present on holds screen

  @logout @returnBooks @tier1
  Scenario: Hold from Book Detail View and Remove a Reserved Book from Book Detail View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Axis 360' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Open EBOOK book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
    When Click RESERVE action button on book details screen
      And Click REMOVE action button on book details screen
    Then I check that book contains RESERVE action button on book details screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Hold from Book Detail View and Cancel remove from holds tab (IOS)
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Open EBOOK book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
    When Click RESERVE action button on book details screen
    Then I check that book contains REMOVE action button on book details screen
    When Click REMOVE action button on book details screen and click CANCEL action button on alert. Only for ios
    Then I check that book contains REMOVE action button on book details screen
    When I close Book Details for IOSTab
      And I open Holds
    Then EBOOK book with REMOVE action button and 'bookInfo' bookInfo is present on holds screen

  @logout @returnBooks @tier1 @exclude_ios
    Scenario: Check books sorting in Reservations
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Click RESERVE action button on AUDIOBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'AUDIOBOOK' from "LYRASIS Reads" and save as 'bookNameInfo2'
      And Click RESERVE action button on AUDIOBOOK book with 'bookNameInfo2' bookName on catalog books screen and save book as 'bookInfo2'
      And Clear search field on catalog books screen
      And I search 'unavailable' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo3'
      And Click RESERVE action button on EBOOK book with 'bookNameInfo3' bookName on catalog books screen and save book as 'bookInfo3'
      And I open Holds
    Then Books are sorted by Title by default on holds screen
    Then Books are sorted by Title ascending on holds screen
      And There are sorting by 'Title' and 'Author' in 'LYRASIS Reads' on holds screen
    When I sort books by AUTHOR in 'LYRASIS Reads'
    Then Books are sorted by Author ascending on holds screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario Outline: Alert: Check of Cancel button after Remove button tapping
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'unavailable' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with RESERVE action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click RESERVE action button on book details screen
      And I open Holds
    Then <bookType> book with REMOVE action button and 'bookInfo' bookInfo is present on holds screen
    When Open <bookType> book with REMOVE action button and 'bookInfo' bookInfo on holds screen
      And Click REMOVE button but cancel the action by clicking CANCEL button on the alert
      And I open Holds
    Then <bookType> book with REMOVE action button and 'bookInfo' bookInfo is present on holds screen

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Axis 360           | EBOOK     | eBooks     |
      | Axis 360           | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |
      | Biblioboard        | EBOOK     | eBooks     |
      | Biblioboard        | AUDIOBOOK | Audiobooks |