Feature: Catalog Navigation

  @tier1
  Scenario: Return to last library catalog
    When I add "Palace Bookshelf" account from welcomeScreen
      And I add 'LYRASIS Reads' account
      And I open Catalog
      And I switch to 'LYRASIS Reads' from side menu
    Then Category rows are loaded
    When I restart app
    Then Category rows are loaded
      And Library 'LYRASIS Reads' is present on Catalog Screen

  @tier1
  Scenario: Browse Categories
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

  @tier1
  Scenario: Sort Lists
    When I add "LYRASIS Reads" account from welcomeScreen
    Then Category rows are loaded
    When I switch to 'Audiobooks' catalog tab
    Then Category rows are loaded
      And All present books are audiobooks
    When I switch to 'eBooks' catalog tab
    Then Category rows are loaded
    When I open categories by chain and chain starts from CategoryScreen:
      | Romance |
    Then Subcategory screen is present
    When I sort books by AUTHOR
    Then Subcategory screen is present
      And Books are sorted by Author ascending
    When I sort books by TITLE
    Then Subcategory screen is present
      And Books are sorted by Title ascending
    When I save list of books as 'listOfBooks'
      And I sort books by RECENTLY_ADDED
    Then Subcategory screen is present
      And List of books on subcategory screen is not equal to list of books saved as 'listOfBooks'
      And Change books visibility to show AVAILABLE_NOW
    Then All books can be loaned or downloaded
    When I change books visibility to show ALL
    Then Subcategory screen is present
    When I change books visibility to show YOURS_TO_KEEP
    Then All books can be downloaded

  @tier1 @ignore @exclude_android
  Scenario Outline: View Book Details (IOS)
    When I add "LYRASIS Reads" account from welcomeScreen
      And I open search modal
      And I search for 'Dune' and save bookName as 'bookNameInfo'
      And I switch to 'Audiobooks' catalog tab
      And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      Then '<PUBLISHED>' published and '<PUBLISHER>' publisher and '<CATEGORIES>' categories and '<DISTRIBUTOR>' distributor are present on book details screen
    Scenarios:
      | PUBLISHED    | PUBLISHER       | CATEGORIES  | DISTRIBUTOR |
      | 29 May 2007 | Macmillan Audio | Space Opera | Bibliotheca |

  @tier1 @ignore @exclude_ios
  Scenario Outline: View Book Details
    When I add "LYRASIS Reads" account from welcomeScreen
    And I open search modal
    And I search for 'Dune' and save bookName as 'bookNameInfo'
    And I switch to 'Audiobooks' catalog tab
    And Open AUDIOBOOK book with GET action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then '<PUBLISHED>' published and '<PUBLISHER>' publisher and '<CATEGORIES>' categories and '<DISTRIBUTOR>' distributor are present on book details screen
    Scenarios:
      | PUBLISHED  | PUBLISHER       | CATEGORIES  | DISTRIBUTOR |
      | 2007-05-29 | Macmillan Audio | Space Opera | Bibliotheca |