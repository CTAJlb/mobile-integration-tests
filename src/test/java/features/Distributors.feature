Feature: Distributors

  @logout @returnBooks @tier1
  Scenario Outline: LYRASIS: Reserving from Book Detail View
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

  @logout @returnBooks @tier1
  Scenario Outline: LYRASIS: Getting and returning books from Book Detail View
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
    When Click RETURN action button on book details screen
    Then I check that book contains GET action button on book details screen
    When Click GET action button on book details screen
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

  @logout @returnBooks @tier1
  Scenario: LYRASIS: Biblioboard audiobooks: Getting and returning books from Book Detail View
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search for "Murder at the Columbarium" and save bookName as 'bookNameInfo'
      And I switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains LISTEN action button on book details screen
    When Click RETURN action button on book details screen
    Then I check that book contains GET action button on book details screen
    When Click GET action button on book details screen
    Then I check that book contains LISTEN action button on book details screen
    When Click LISTEN action button on book details screen
      And Wait for 3 seconds
    Then Book 'bookInfo' with AUDIOBOOK type is present on epub or pdf or audiobook screen

  @logout @returnBooks @tier2
  Scenario: Overdrive: Getting and returning a book from Book Detail View
    When I turn on test mode in "Palace Bookshelf" library
      And Enable hidden libraries
      And I open Catalog
      And I add "A1AQ Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When I enter credentials for "A1QA Test Library" account
    Then Login is performed successfully
    When I open Catalog
      And I search for "Silk Road" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains READ action button on book details screen
    When Click RETURN action button on book details screen
    Then I check that book contains GET action button on book details screen
    When Click GET action button on book details screen
    Then I check that book contains READ action button on book details screen
    When Click READ action button on book details screen
    And Wait for 3 seconds
    Then Book 'bookInfo' with EBOOK type is present on epub or pdf or audiobook screen

  @logout @returnBooks @tier2
  Scenario: Overdrive: Getting and returning an audiobook from Book Detail View
    When I turn on test mode in "Palace Bookshelf" library
      And Enable hidden libraries
      And I open Catalog
      And I add "A1AQ Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When I enter credentials for "A1QA Test Library" account
    Then Login is performed successfully
    When I open Catalog
      And I search for "We Are Water" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains READ action button on book details screen
    When Click RETURN action button on book details screen
    Then I check that book contains GET action button on book details screen
    When Click GET action button on book details screen
    Then I check that book contains READ action button on book details screen
    When Click READ action button on book details screen
    And Wait for 3 seconds
    Then Book 'bookInfo' with AUDIOBOOK type is present on epub or pdf or audiobook screen

  @tier2
  Scenario: Palace Bookshelf: Getting and returning a book from Book Detail View
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
      And I search for "Jane Eyre" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
    Then I check that book contains READ action button on book details screen
    When Click RETURN action button on book details screen
    Then I check that book contains GET action button on book details screen
    When Click GET action button on book details screen
    Then I check that book contains READ action button on book details screen
    When Click READ action button on book details screen
      And Wait for 3 seconds
    Then Book 'bookInfo' with AUDIOBOOK type is present on epub or pdf or audiobook screen

  @logout @returnBooks @tier1
  Scenario Outline: LYRASIS: Check of canceling the downloading from book details view
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

  @logout @returnBooks @tier1
  Scenario: LYRASIS: Biblioboard audiobooks: Check of canceling the downloading from book details view
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search for "Let's Hear It For Beagles" and save bookName as 'bookNameInfo'
      And I switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button and cancel downloading by click CANCEL button on book detail screen
    Then I check that book contains DOWNLOAD action button on book details screen
      And I check that book contains RETURN action button on book details screen

  @tier2
  Scenario: Palace: Check of canceling the downloading from book details view
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search for "The Ragged Edge" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button and cancel downloading by click CANCEL button on book detail screen
    Then I check that book contains DOWNLOAD action button on book details screen
      And I check that book contains DELETE action button on book details screen

  @logout @returnBooks @tier2
  Scenario: Overdrive: Check of canceling the downloading from book details view
    When I turn on test mode in "Palace Bookshelf" library
      And Enable hidden libraries
      And I open Catalog
      And I add "A1AQ Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When I enter credentials for "A1QA Test Library" account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search for "The Fallen" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button and cancel downloading by click CANCEL button on book detail screen
    Then I check that book contains DOWNLOAD action button on book details screen
      And I check that book contains RETURN action button on book details screen