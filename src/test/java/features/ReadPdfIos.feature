Feature: Read PDF IOS

  Background:
    When I add "Palace Bookshelf" account from welcomeScreen
    And I open Catalog
    And I open search modal
    And I search for 'Deep into Pharo' and save bookName as 'bookNameInfo'
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen

  @tier1 @exclude_android
  Scenario: Navigate by Page
    Then Pdf page number is 1 on reader pdf screen
    When I go to next page on reader pdf screen
    Then Pdf page number is 2 on reader pdf screen
    When I go to previous page on reader pdf screen
    Then Pdf page number is 1 on reader pdf screen

  @tier1 @exclude_android
  Scenario: Navigate by Table of Contents Menu
    Then Random chapter of pdf book can be opened from toc pdf screen

  @tier1 @exclude_android
  Scenario: Open book to last page read
    When I swipe pdf page forward from 4 to 6 times on reader pdf screen
      And I save pdf page number as 'pageNumber' on reader pdf screen
      And I wait for 3 seconds
      And I return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen
      And I wait for 3 seconds
    When I restart app
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen

  @tier1 @exclude_android
  Scenario: Navigate by Bookmarks
    When I open bookmarks pdf screen
    Then Bookmarks pdf screen is opened

  @tier1 @exclude_android
  Scenario: Navigate by Gallery
    When I open gallery pdf screen
    Then Gallery pdf screen is opened
    When I open random pdf page on gallery pdf screen and save pdf page number as 'pageNumber'
      And The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen
    Then 'bookInfo' book is present on reader pdf screen

  @tier1 @exclude_android
  Scenario: Search Pdf Functionality
    When I open search pdf screen
    Then Search pdf screen is opened
    When I search 'contenido' text on search pdf screen
    Then Found lines should contain 'contenido' in themselves on search pdf screen

  @tier1 @exclude_android
  Scenario: Navigate by Pdf Search Results
    When I open search pdf screen
      And I search 'contenido' text on search pdf screen
      And I save page number as 'pageNumber' of the first found text on search pdf screen
      And I open the first found text on search pdf screen
    Then The 'pageNumber' saved page number is equal to the current page number on the reader pdf screen

  @tier1 @exclude_android
  Scenario: Navigate by Page slider
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
    When I save pdf page number as 'pageNumber' on reader pdf screen
      And Slide page slider RIGHT on reader pdf screen
    Then The 'pageNumber' saved page number is less than the current page number on the reader pdf screen
    When I save pdf page number as 'pageNumber' on reader pdf screen
      And Slide page slider LEFT on reader pdf screen
    Then The 'pageNumber' saved page number is greater than the current page number on the reader pdf screen
