Feature: Catalog Navigation

  @tier2
  Scenario: Return to last library catalog
    When I add "Palace Bookshelf" account from welcomeScreen
      And I add 'LYRASIS Reads' account
    Then Category rows are loaded
    When I restart app
      And I close account screen
    Then Category rows are loaded
      And Library 'LYRASIS Reads' is present on Catalog Screen

  @tier2
  Scenario: Browse Categories in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
      And Count of books in first lane is more than 1
    When I get names of books on screen and save them as 'listOfBooksOnMainPage'
      And I open categories by chain and chain starts from CategoryScreen:
        | Fiction  |
        | Classics |
    Then Subcategory name is 'Classics'
      And Subcategory rows are loaded
      And Following subcategories are present:
        |All Classics|
    And List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'
    And I open 'All Classics' subcategory
    Then Subcategory screen is present
      And Subcategory name is 'All Classics'
    When I open first book in Subcategory List and save it as 'bookInfo'
    Then Book 'bookInfo' is opened on book details screen

  @tier2
  Scenario: Check of the titles of books sections in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
      And Category names are correct on catalog book screen

  @tier2
  Scenario: Check of "More" button in books sections in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
      And More button is present on each section of books on catalog book screen
    When I click More button from random book section and save name of section as 'sectionInfo' on catalog book screen
    Then Book section 'sectionInfo' is opened
    When I tap Back button on subcategory screen
    Then Category rows are loaded

  @tier2
  Scenario Outline: Check of books sorting in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
    When I open categories by chain and chain starts from CategoryScreen:
      | Our Picks |
    Then Books are sorted by Author by default on subcategory screen in 'Palace Bookshelf'
      And There are sorting by '<type1>', '<type2>' and '<type3>' in 'Palace Bookshelf' on subcategory screen

    Scenarios:
    | type1  | type2          | type3 |
    | Author | Recently Added | Title |

  @tier2
  Scenario: Sort Lists in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
    When I open categories by chain and chain starts from CategoryScreen:
      | Our Picks |
    Then Subcategory screen is present
    When I sort books by AUTHOR in 'Palace Bookshelf'
    Then Subcategory screen is present
      And Books are sorted by Author ascending
    When I sort books by TITLE in 'Palace Bookshelf'
    Then Subcategory screen is present
      And Books are sorted by Title ascending
    When I save list of books as 'listOfBooks'
      And I sort books by RECENTLY_ADDED in 'Palace Bookshelf'
    Then Subcategory screen is present
      And List of books on subcategory screen is not equal to list of books saved as 'listOfBooks'

  @tier2
  Scenario Outline: Check of tabs at the top of the screen in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
      And There are types '<type1>', '<type2>' and '<type3>' of books on catalog book screen:
      And Section with books of '<type1>' type is opened on catalog book screen
    When I switch to '<type2>' catalog tab
    Then Section with books of '<type2>' type is opened on catalog book screen
    When I switch to '<type3>' catalog tab
    Then Section with books of '<type3>' type is opened on catalog book screen

    Scenarios:
    | type1 | type2  | type3      |
    | All   | eBooks | Audiobooks |

  @tier2
  Scenario: Check of the titles of books sections in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
    And I open Catalog
    Then Category rows are loaded
    And Category names are correct on catalog book screen

  @tier2
  Scenario: Check of "More" button in books sections in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
      And More button is present on each section of books on catalog book screen
    When I click More button from random book section and save name of section as 'sectionInfo' on catalog book screen
    Then Book section 'sectionInfo' is opened
    When I tap Back button on subcategory screen
    Then Category rows are loaded

  @tier2
  Scenario Outline: Check of books sorting in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
    And I open Catalog
    Then Category rows are loaded
    When I open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Books are sorted by Author by default on subcategory screen in 'LYRASIS Reads'
    And There are sorting by '<type1>', '<type2>' and '<type3>' in 'LYRASIS Reads' on subcategory screen

    Scenarios:
      | type1  | type2          | type3 |
      | Author | Recently Added | Title |

  @tier2
  Scenario: Sort Lists in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
    When I open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Subcategory screen is present
    When I sort books by AUTHOR in 'LYRASIS Reads'
    Then Subcategory screen is present
      And Books are sorted by Author ascending
    When I sort books by TITLE in 'LYRASIS Reads'
    Then Subcategory screen is present
      And Books are sorted by Title ascending
    When I save list of books as 'listOfBooks'
      And I sort books by RECENTLY_ADDED in 'LYRASIS Reads'
    Then Subcategory screen is present
      And List of books on subcategory screen is not equal to list of books saved as 'listOfBooks'


  @tier2
  Scenario Outline: Check of books availability in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
    When I open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Subcategory screen is present
      And The book availability is ALL by default on subcategory screen
      And There are availability by '<type1>', '<type2>' and '<type3>' on subcategory screen

    Scenarios:
      | type1 | type2         | type3         |
      | All   | Available now | Yours to keep |

  @tier2
  Scenario: Check all types of availability
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
    When I open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Subcategory screen is present
    When Change books visibility to show AVAILABLE_NOW
    Then All books can be loaned or downloaded
    When I change books visibility to show ALL
    Then Subcategory screen is present
    When I change books visibility to show YOURS_TO_KEEP
    Then All books can be downloaded

  @tier2 @exclude_android
  Scenario Outline: Check of books collections
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open Catalog
    Then Category rows are loaded
    When I open categories by chain and chain starts from CategoryScreen:
      | Baker & Taylor Axis360 Test |
    Then Subcategory screen is present
      And Collections is Everything by default on subcategory screen
      And There are collection type by '<type1>' and '<type2>' on subcategory screen

    Scenarios:
    | type1      | type2         |
    | Everything | Popular Books |