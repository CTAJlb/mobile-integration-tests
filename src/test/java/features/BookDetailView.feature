Feature: Book detail view screen

  @tier2
  Scenario Outline: Check of a book title and author in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Book 'bookInfo' has correct title and author name on book details screen
      And The book cover is displayed on book details screen

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Axis 360           | EBOOK     | eBooks     |
      | Axis 360           | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |
      | Biblioboard        | EBOOK     | eBooks     |

  @tier2
  Scenario: Biblioboard audiobook: Check of a book title and author in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
      And I switch to 'Audiobooks' catalog tab
      And I open 'New BiblioBoard Test' category
      And I swipe catalog of books down on catalog book screen
      And Open random book on catalog book screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Book 'bookInfo' has correct title and author name on book details screen
      And The book cover is displayed on book details screen

  @tier2
  Scenario Outline: Check of a "More..." button in Description section in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Description is not empty in book details screen
      And Button More in Description is available on book details screen

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Axis 360           | EBOOK     | eBooks     |
      | Axis 360           | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |
      | Biblioboard        | EBOOK     | eBooks     |

  @tier2
  Scenario: Biblioboard audiobook: Check of a "More..." button in Description section in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
      And I switch to 'Audiobooks' catalog tab
      And I open 'New BiblioBoard Test' category
      And I swipe catalog of books down on catalog book screen
      And I swipe catalog of books down on catalog book screen
      And Open random book on catalog book screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Description is not empty in book details screen
      And Button More in Description is available on book details screen

  @tier2
  Scenario Outline: Check fields in Information section in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Publisher and Categories in Information section are displayed on book details screen
      And Publisher and Categories in Information section are correct on book details screen
      And Distributor is equal to '<distributor>' on book details screen

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Axis 360           | EBOOK     | eBooks     |
      | Axis 360           | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |
      | Biblioboard        | EBOOK     | eBooks     |

  @tier2
  Scenario: Biblioboard audiobook: Check fields in Information section in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
      And I switch to 'Audiobooks' catalog tab
      And I open 'New BiblioBoard Test' category
      And I swipe catalog of books down on catalog book screen
      And Open random book on catalog book screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Publisher and Categories in Information section are displayed on book details screen
      And Publisher and Categories in Information section are correct on book details screen
      And Distributor is equal to 'Biblioboard' on book details screen

  @tier2
  Scenario Outline: Check related books section in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' from "LYRASIS Reads" and save as 'bookNameInfo'
      And I switch to '<tabName>' catalog tab
      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Related books section is displayed on book details screen
      And There is a list of related books on book details screen
      And More button in related books section is available on book details screen

    Scenarios:
      | distributor        | bookType  | tabName    |
      | Bibliotheca        | EBOOK     | eBooks     |
      | Bibliotheca        | AUDIOBOOK | Audiobooks |
      | Axis 360           | EBOOK     | eBooks     |
      | Axis 360           | AUDIOBOOK | Audiobooks |
      | Palace Marketplace | EBOOK     | eBooks     |
      | Palace Marketplace | AUDIOBOOK | Audiobooks |
      | Biblioboard        | EBOOK     | eBooks     |

  @tier2
  Scenario: BiblioBoard audiobook: Check related books section in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search for "Kate & Ruby" and save bookName as 'bookNameInfo'
      And I switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Related books section is displayed on book details screen
      And There is a list of related books on book details screen
      And More button in related books section is available on book details screen

#  @tier1
#  Scenario Outline: Check of sorting books in related books in LYRASIS
#    When I add "LYRASIS Reads" account from welcomeScreen
#      And I open Catalog
#      And I open search modal
#    And I search 'available' book of distributor '<distributor>' and bookType '<bookType>' and save as 'bookNameInfo'
#    And I switch to '<tabName>' catalog tab
#      And Open <bookType> book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
#    Then Book 'bookInfo' is opened on book details screen
#    When Tap More... button in related books on book details view
#      And I sort books by AUTHOR
#    Then Books are sorted by Author ascending
#    When I save list of books as 'listOfBooks'
#      And I sort books by RECENTLY_ADDED
#    Then List of books on subcategory screen is not equal to list of books saved as 'listOfBooks'
#    When I sort books by TITLE
#    Then Books are sorted by Title ascending
#
#    Scenarios:
#      | distributor        | bookType  | tabName    |
#      | Bibliotheca        | EBOOK     | eBooks     |
#      | Bibliotheca        | AUDIOBOOK | Audiobooks |
#      | Axis 360           | EBOOK     | eBooks     |
#      | Axis 360           | AUDIOBOOK | Audiobooks |
#      | Palace Marketplace | EBOOK     | eBooks     |
#      | Palace Marketplace | AUDIOBOOK | Audiobooks |
#      | Biblioboard        | EBOOK     | eBooks     |
#      | Biblioboard        | AUDIOBOOK | Audiobooks |

  @tier3
  Scenario: Check of a book title and author in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search for "Persuasion" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Book 'bookInfo' has correct title and author name on book details screen
      And The book cover is displayed on book details screen

  @tier2
  Scenario: Check of a "More..." button in Description section in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search for "Persuasion" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Description is not empty in book details screen
      And Button More in Description is available on book details screen

  @tier2
  Scenario: Check fields in Information section in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search for "Persuasion" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Publisher and Categories in Information section are correct on book details screen

  @tier2
  Scenario: Check related books section in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search for "Persuasion" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Related books section is displayed on book details screen
      And There is a list of related books on book details screen
      And More button in related books section is available on book details screen

#  @tier1
#  Scenario: Check of sorting books in related books in Palace
#    When I add "Palace Bookshelf" account from welcomeScreen
#      And I open Catalog
#      And I open search modal
#      And I search for "Persuasion" and save bookName as 'bookNameInfo'
#      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
#    Then Book 'bookInfo' is opened on book details screen
#    When Tap More... button in related books on book details view
#    And I sort books by AUTHOR
#    Then Books are sorted by Author ascending
#    When I save list of books as 'listOfBooks'
#    And I sort books by RECENTLY_ADDED
#    Then List of books on subcategory screen is not equal to list of books saved as 'listOfBooks'
#    When I sort books by TITLE
#    Then Books are sorted by Title ascending

  @tier2
  Scenario: Check of a book title and author in Overdrive
    When I turn on test mode in "Palace Bookshelf" library
      And Enable hidden libraries
      And I open Catalog
      And I add "A1QA Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When I open Catalog
      And I open search modal
      And I search for "The Woman in White" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Book 'bookInfo' has correct title and author name on book details screen
      And The book cover is displayed on book details screen

  @tier2
  Scenario: Check of a "More..." button in Description section in Overdrive
    When I turn on test mode in "Palace Bookshelf" library
      And Enable hidden libraries
      And I open Catalog
      And I add "A1QA Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When I open Catalog
      And I open search modal
      And I search for "The Oregon Trail" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Description is not empty in book details screen
      And Button More in Description is available on book details screen

  @tier2
  Scenario: Check fields in Information section in Overdrive
    When I turn on test mode in "Palace Bookshelf" library
      And Enable hidden libraries
      And I open Catalog
      And I add "A1QA Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When I open Catalog
      And I open search modal
      And I search for "The Count of Monte Cristo" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Publisher and Categories in Information section are correct on book details screen
      And Distributor is equal to 'Overdrive' on book details screen

  @tier2
  Scenario: Check related books section in Overdrive
    When I turn on test mode in "Palace Bookshelf" library
      And Enable hidden libraries
      And I open Catalog
      And I add "A1QA Test Library" account by the logo
    Then Account "A1QA Test Library" is present on Accounts screen
    When I open Catalog
      And I open search modal
      And I search for "The Woman in White" and save bookName as 'bookNameInfo'
      And Open EBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen
      And Related books section is displayed on book details screen
      And There is a list of related books on book details screen
      And More button in related books section is available on book details screen