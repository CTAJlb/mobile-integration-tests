Feature: Catalog Navigation

  @tier3
  Scenario: Return to last library catalog
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Add 'LYRASIS Reads' account
    Then Category rows are loaded
    When Restart app
      And Close account screen
    Then Category rows are loaded
      And Library 'LYRASIS Reads' is present on Catalog Screen

  @tier3
  Scenario: Browse Categories in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
      And Count of books in first lane is more than 1
    When Get names of books on screen and save them as 'listOfBooksOnMainPage'
      And Open categories by chain and chain starts from CategoryScreen:
        | Nonfiction  |
        | Education |
    Then Subcategory name is 'Education'
      And List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'
    When Open first book in Subcategory List and save it as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen

  @tier3
  Scenario: Check of the titles of books sections in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
      And Category names are correct on catalog book screen

  @tier3
  Scenario: Check of "More" button in books sections in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
      And More button is present on each section of books on catalog book screen
    When Click More button from random book section and save name of section as 'sectionInfo' on catalog book screen
    Then Book section 'sectionInfo' is opened
    When Tap Back button on subcategory screen
    Then Category rows are loaded

  @tier3
  Scenario Outline: Check of books sorting in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
    When Open categories by chain and chain starts from CategoryScreen:
      | Our Picks |
    Then Books are sorted by Author by default on subcategory screen in 'Palace Bookshelf'
      And There are sorting by '<type1>', '<type2>' and '<type3>' on subcategory screen

    Scenarios:
    | type1  | type2          | type3 |
    | Author | Recently Added | Title |

  @tier3
  Scenario: Sort Lists in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
    Then Account "Palace Bookshelf" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
    When Open categories by chain and chain starts from CategoryScreen:
      | Our Picks |
    Then Subcategory screen is present
    When Sort books by AUTHOR
    Then Subcategory screen is present
      And Books are sorted by Author ascending
    When Sort books by TITLE
    Then Subcategory screen is present
      And Books are sorted by Title ascending
    When Save list of books as 'listOfBooks'
      And Sort books by RECENTLY_ADDED
    Then Subcategory screen is present
      And List of books on subcategory screen is not equal to list of books saved as 'listOfBooks'

  @tier3
  Scenario Outline: Check of tabs at the top of the screen in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
      And There are types '<type1>', '<type2>' and '<type3>' of books on catalog book screen:
      And Section with books of '<type1>' type is opened on catalog book screen
    When Switch to '<type2>' catalog tab
    Then Section with books of '<type2>' type is opened on catalog book screen
    When Switch to '<type3>' catalog tab
    Then Section with books of '<type3>' type is opened on catalog book screen

    Scenarios:
    | type1 | type2  | type3      |
    | All   | eBooks | Audiobooks |

  @tier3
  Scenario: Check of the titles of books sections in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
    And Category names are correct on catalog book screen

  @tier3
  Scenario: Check of "More" button in books sections in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
      And More button is present on each section of books on catalog book screen
    When Click More button from random book section and save name of section as 'sectionInfo' on catalog book screen
    Then Book section 'sectionInfo' is opened
    When Tap Back button on subcategory screen
    Then Category rows are loaded

  @tier3
  Scenario Outline: Check of books sorting in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
    When Open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Books are sorted by Author by default on subcategory screen in 'LYRASIS Reads'
    And There are sorting by '<type1>', '<type2>' and '<type3>' on subcategory screen

    Scenarios:
      | type1  | type2          | type3 |
      | Author | Recently Added | Title |

  @tier3
  Scenario: Sort Lists in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
    When Open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Subcategory screen is present
    When Sort books by AUTHOR
    Then Subcategory screen is present
      And Books are sorted by Author ascending
    When Sort books by TITLE
    Then Subcategory screen is present
      And Books are sorted by Title ascending
    When Save list of books as 'listOfBooks'
      And Sort books by RECENTLY_ADDED
    Then Subcategory screen is present
      And List of books on subcategory screen is not equal to list of books saved as 'listOfBooks'


  @tier3
  Scenario Outline: Check of books availability in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
    When Open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Subcategory screen is present
      And The book availability is ALL by default on subcategory screen
      And There are availability by '<type1>', '<type2>' and '<type3>' on subcategory screen

    Scenarios:
      | type1 | type2         | type3         |
      | All   | Available now | Yours to keep |

  @tier3
  Scenario: Check all types of availability
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
    When Open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Subcategory screen is present
    When Change books visibility to show AVAILABLE_NOW
    Then All books can be loaned or downloaded
    When Change books visibility to show ALL
    Then Subcategory screen is present
    When Change books visibility to show YOURS_TO_KEEP
    Then All books can be downloaded

  @tier3 @exclude_android
  Scenario Outline: Check of books collections
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
    Then Account "LYRASIS Reads" is present on Accounts screen
    When Open Catalog
    Then Category rows are loaded
    When Open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Subcategory screen is present
      And Collections is Everything by default on subcategory screen
      And There are collection type by '<type1>' and '<type2>' on subcategory screen

    Scenarios:
    | type1      | type2         |
    | Everything | Popular Books |