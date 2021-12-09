Feature: Read PDF IOS

  Background:
    Given I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS Reads' from side menu
      And I open search modal
      And I search for 'Velociraptor' and save bookName as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen

  @logout @returnBooks @tier1 @exclude_android @oldOs
  Scenario: Open document
    Then Pdf page number is 1 on reader pdf screen

  @logout @returnBooks @tier1 @exclude_android @oldOs
  Scenario: Navigate by page
    When I go to next page on reader pdf screen
    Then Pdf page number is 2 on reader pdf screen
    When I go to previous page on reader pdf screen
    Then Pdf page number is 1 on reader pdf screen

  @logout @returnBooks @tier1 @exclude_android @oldOs
  Scenario: Navigate by Table of Contents Menu
    Then Each chapter of pdf book can be opened from toc pdf screen

  @logout @returnBooks @tier1 @exclude_android @oldOs
  Scenario: Open book to last page read
    When I swipe pdf page forward from 10 to 20 times on reader pdf screen
      And I save pdf page number as 'pageNumber' on reader pdf screen
      And I wait for 3 seconds
      And I return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And 'pageNumber' pdf saved page number should be equal to current page number on reader pdf screen
      And I wait for 3 seconds
    When I restart app
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And 'pageNumber' pdf saved page number should be equal to current page number on reader pdf screen

  @logout @returnBooks @tier1 @exclude_android @oldOs
  Scenario: Navigate by bookmarks
    When I open bookmarks pdf screen
    Then Bookmarks pdf screen is opened

  @logout @returnBooks @tier1 @exclude_android @oldOs
  Scenario: Navigate by Gallery
    When I open gallery pdf screen
    Then Gallery pdf screen is opened
    When I open random pdf page on gallery pdf screen and save pdf page number as 'pageNumber'
      And 'pageNumber' pdf saved page number should be equal to current page number on reader pdf screen
    Then 'bookInfo' book is present on reader pdf screen

  @logout @returnBooks @tier1 @exclude_android @oldOs
  Scenario: Search document
    When I open search pdf screen
    Then Search pdf screen is opened
    When I search 'contenido' text on search pdf screen
    Then Found lines should contain 'contenido' in themselves on search pdf screen

  @logout @returnBooks @tier1 @exclude_android @oldOs
  Scenario: Navigate to Search Term
    When I open search pdf screen
      And I search 'contenido' text on search pdf screen
      And I save page number as 'pageNumber' of the first found text on search pdf screen
      And I open the first found text on search pdf screen
    Then 'pageNumber' pdf saved page number should be equal to current page number on reader pdf screen
