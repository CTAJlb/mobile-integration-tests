Feature: Read PDF

  Background:
    Given I add "LYRASIS Reads" account from welcomeScreen
      And I enter credentials for 'LYRASIS Reads' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS Reads' from side menu
      And I open search modal

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Open document
    When I search random pdf and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
      When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And Pdf page number is 1 on reader pdf screen

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Navigate by page
    When I search random pdf and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And Pdf page number is 1 on reader pdf screen
    When I go to next page on reader pdf screen
    Then Pdf page number is 2 on reader pdf screen
    When I go to previous page on reader pdf screen
    Then Pdf page number is 1 on reader pdf screen

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Navigate by Table of Contents Menu
    And I search for 'Great Migration' and save bookName as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And Each chapter of pdf book can be opened from toc pdf screen

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Open book to last page read
    When I search random pdf and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
    When I swipe pdf page forward from 10 to 20 times on reader pdf screen
      And I save pdf page number as 'pageNumber' on reader pdf screen
      And I return to previous screen for epub and pdf
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And 'pageNumber' pdf saved page number should be equal to current page number on reader pdf screen
    When I restart app
      And I open Books
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on books screen
    When Open EBOOK book with READ action button and 'bookInfo' bookInfo on books screen
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
      And 'pageNumber' pdf saved page number should be equal to current page number on reader pdf screen

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Close book
    When I search random pdf and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
    When I return to previous screen for epub and pdf
    Then I check that book contains READ action button on book details screen

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Navigate by Page slider
    When I search random pdf and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When Click GET action button on EBOOK book with 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
    Then EBOOK book with READ action button and 'bookInfo' bookInfo is present on catalog books screen
    When Open EBOOK book with READ action button and 'bookNameInfo' bookName on catalog books screen and save book as 'bookInfo'
      And Click READ action button on book details screen
    Then 'bookInfo' book is present on reader pdf screen
    When I save pdf page number as 'pageNumber' on reader pdf screen
      And Slide page slider RIGHT on reader pdf screen
    Then 'pageNumber' pdf saved page number should not be equal to current page number on reader pdf screen
    When I save pdf page number as 'pageNumber' on reader pdf screen
      And Slide page slider LEFT on reader pdf screen
    Then 'pageNumber' pdf saved page number should not be equal to current page number on reader pdf screen
