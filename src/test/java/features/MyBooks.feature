Feature: My books module

  @tier3
  Scenario: Check of added books in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Open Catalog
      And Open search modal
      And Search several books and save them in list as 'listOfBooks':
        | One Way     |
        | Jane Eyre   |
        | The Tempest |
        | Poetry      |
      And Return back from search modal
      And Open Books
    Then Added books from 'listOfBooks' are displayed on books screen

  @tier3
  Scenario: Check of sorting in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Open Catalog
      And Open search modal
      And Search several books and save them in list as 'listOfBooks':
        | One Way     |
        | Jane Eyre   |
        | The Tempest |
        | Poetry      |
      And Return back from search modal
      And Open Books
    Then Books are sorted by Author ascending on books screen
    When Sort books by TITLE
    Then Books are sorted by Title ascending on books screen

  @logout @returnBooks @trier1
  Scenario: Return book from My Books in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Catalog
      And Open search modal
      And Search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click RETURN action button on book details screen
      And Close Book Details for IOSTab
      And Open Books
    Then EBOOK book with GET action button and 'bookInfo' bookInfo is not present on books screen

  @logout @returnBooks @tier1
  Scenario: Get a book from Book Detail View and Return from Books in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Catalog
      And Open search modal
      And Search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
    When Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
      And Close Book Details for IOSTab
      And Open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click RETURN action button on book details screen
      And Close Book Details for IOSTab
      And Open Books
      And Wait for 10 seconds
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is not present on books screen

  @logout @returnBooks @tier1
  Scenario: Get a book from Subcategory List View and Return from Books in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Catalog
      And Open search modal
      And Search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
      And Click READ action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Wait for 3 seconds
    Then 'bookInfo' book is present on epub reader screen
    When Return to previous screen for epub and pdf
      And Open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click RETURN action button on book details screen
      And Open Books
      And Wait for 7 seconds
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is not present on books screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Get a book from Subcategory List View and Return from Subcategory List View in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Catalog
      And Open search modal
      And Search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click RETURN action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with GET action button and 'bookInfo' bookInfo is present on catalog books screen

  @logout @returnBooks @tier1
  Scenario: Get a book from Subcategory List View and Read from Books in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Catalog
      And Open search modal
      And Search 'available' book of distributor 'Bibliotheca' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open Books
      And Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
      And Wait for 3 seconds
    Then 'bookInfo' book is present on epub reader screen

  @logout @returnBooks @tier1 @exclude_android
  Scenario Outline: Alert: Check of Cancel button after Return button tapping
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Catalog
      And Open search modal
      And Search 'available' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
      And Open Books
    Then <bookType> book with <buttonBook> action button and 'bookInfo' bookInfo is present on books screen
    When Open <bookType> book with <buttonBook> action button and 'bookInfo' bookInfo on books screen
      And Click RETURN button but cancel the action by clicking CANCEL button on the alert
      And Open Books
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

  @logout @returnBooks @tier1 @exclude_android
  Scenario: Alert: Biblioboard audiobooks: Check of Cancel button after Return button tapping
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Catalog
      And Open search modal
      And Search for "Magic's Daughter" and save bookName as 'bookNameInfo'
      And Switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click GET action button on book details screen
      And Open Books
    Then AUDIOBOOK book with LISTEN action button and 'bookInfo' bookInfo is present on books screen
    When Open AUDIOBOOK book with LISTEN action button and 'bookInfo' bookInfo on books screen
      And Click RETURN button but cancel the action by clicking CANCEL button on the alert
      And Open Books
    Then AUDIOBOOK book with LISTEN action button and 'bookInfo' bookInfo is present on books screen


  @logout @returnBooks @tier1
  Scenario Outline: Check buttons under the book title in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When Open Catalog
      And Open search modal
      And Search 'available' book of distributor '<distributor>' and bookType 'EBOOK' from "LYRASIS Reads" and save as 'bookNameInfo'
      And Switch to 'eBooks' catalog tab
      And Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And EBOOK book with RETURN action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
      And Wait for 5 seconds
      And Restart app
      And Open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
      And EBOOK book with RETURN action button and 'bookInfo' bookInfo is present on books screen

    Scenarios:
      | distributor        |
      | Bibliotheca        |
      | Axis 360           |
      | Palace Marketplace |
      | Biblioboard        |