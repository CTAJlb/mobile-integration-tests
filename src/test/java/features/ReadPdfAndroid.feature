Feature: Read PDF

  Background:
    Given I add "LYRASIS" account from welcomeScreen
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
    When I search for pdf and save as 'bookNameInfo'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
      And I GET or DOWNLOAD book by name 'bookNameInfo' and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button at catalog books screen
    When I open book 'bookInfo' details by clicking on cover
      And Press on the book details screen at the action button READ
    Then Pdf book 'bookInfo' is present on screen

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Open document
      And Pdf book page number is 1

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Navigate by page
      And Pdf book page number is 1
    When I go to next page in pdf book
    Then Pdf book page number is 2
    When I go to previous page in pdf book
    Then Pdf book page number is 1

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Navigate by Table of Contents Menu
      And Each chapter of pdf book can be opened from Table of Contents

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Open book to last page read
    When I scroll pdf page forward from 10 to 20 times
      And I save pdf page number as 'pageNumber'
      And I return to previous screen
      And Press on the book details screen at the action button READ
    Then Pdf book 'bookInfo' is present on screen
      And Pdf page number 'pageNumber' is correct
    When I restart app
      And I open Books
    Then Book 'bookInfo' is present in Books List
    When I open book 'bookInfo' details by clicking on cover
      And Press on the book details screen at the action button READ
    Then Pdf book 'bookInfo' is present on screen
      And Pdf page number 'pageNumber' is correct

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Close book
    When I return to previous screen
    Then I check that opened book contains READ button at book details screen

  @logout @returnBooks @tier1 @exclude_ios
  Scenario: Navigate by Page slider
    When I save pdf page number as 'pageNumber'
      And Slide page slider RIGHT
    Then Pdf saved page number 'pageNumber' should not be equal to current
    When I save pdf page number as 'pageNumber'
      And Slide page slider LEFT
    Then Pdf saved page number 'pageNumber' should not be equal to current
