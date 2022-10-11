Feature: My books module

  @tier1
  Scenario: Check of added books in Palace
    When I add 'Palace Bookshelf' account from welcomeScreen
      And I open search modal
      And I search several books and save them in list as 'listOfBooks':
        | One Way     |
        | Jane Eyre   |
        | The Tempest |
        | Poetry      |
    And I return back from search modal
    And I open Books
    Then Added books from 'listOfBooks' are displayed on books screen

  @tier1
  Scenario: Check of sorting in Palace
    When I add 'Palace Bookshelf' account from welcomeScreen
      And I open search modal
      And I search several books and save them in list as 'listOfBooks':
        | One Way     |
        | Jane Eyre   |
        | The Tempest |
        | Poetry      |
      And I return back from search modal
      And I open Books
    Then Books are sorted by Author ascending on books screen
    When I sort books by TITLE in 'Palace Bookshelf'
    Then Books are sorted by Title ascending on books screen

  @logout @returnBooks @trier1
  Scenario: Return book from My Books in LYRASIS
    When I add 'LYRASIS Reads' account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click RETURN action button on book details screen
      And I close Book Details for IOSTab
      And I open Books
    Then EBOOK book with GET action button and 'bookInfo' bookInfo is not present on books screen

  @logout @returnBooks @tier2
  Scenario: Get a book from Book Detail View and Return from Books in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    When Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
      And I close Book Details for IOSTab
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click RETURN action button on book details screen
      And I close Book Details for IOSTab
      And I open Books
      And Wait for 10 seconds
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is not present on books screen

  @logout @returnBooks @tier2
  Scenario: Get a book from Subcategory List View and Return from Books in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
      And Click READ action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Wait for 3 seconds
    Then 'bookInfo' book is present on epub reader screen
    When I return to previous screen for epub and pdf
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click RETURN action button on book details screen
      And I open Books
      And Wait for 7 seconds
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is not present on books screen

  @logout @returnBooks @tier2 @exclude_android
  Scenario: Get a book from Subcategory List View and Return from Subcategory List View in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click RETURN action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And EBOOK book with GET action button and 'bookInfo' bookInfo is present on catalog books screen

  @logout @returnBooks @tier2
  Scenario: Get a book from Subcategory List View and Read from Books in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When I open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
      And Wait for 3 seconds
    Then 'bookInfo' book is present on epub reader screen

  @logout @returnBooks @tier2 @exclude_android
  Scenario Outline: Alert: Check of Cancel button after Return button tapping
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
      And I open Books
    Then <bookType> book with <buttonBook> action button and 'bookInfo' bookInfo is present on books screen
    When Open <bookType> book with <buttonBook> action button and 'bookInfo' bookInfo on books screen
      And Click RETURN button but cancel the action by clicking CANCEL button on the alert
      And I open Books
    Then <bookType> book with <buttonBook> action button and 'bookInfo' bookInfo is present on books screen

    Scenarios:
    | distributor        | bookType  | tabName    | buttonBook |
    | Bibliotheca        | EBOOK     | eBooks     | READ       |
    | Bibliotheca        | AUDIOBOOK | Audiobooks | LISTEN     |
    | Axis 360           | EBOOK     | eBooks     | READ       |
    | Axis 360           | AUDIOBOOK | Audiobooks | LISTEN     |
    | Palace Marketplace | EBOOK     | eBooks     | READ       |
    | Palace Marketplace | AUDIOBOOK | Audiobooks | LISTEN     |
    | Biblioboard        | EBOOK     | eBooks     | READ       |
    | Biblioboard        | AUDIOBOOK | Audiobooks | LISTEN     |

  @logout @returnBooks @tier2
  Scenario Outline: Check buttons under the book title in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And EBOOK book with RETURN action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
      And Wait for 5 seconds
      And I restart app
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And EBOOK book with RETURN action button and 'bookInfo' bookInfo is present on books screen

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Axis 360           |
      | Palace Marketplace |
      | Biblioboard        |