Feature: Read EPUB

  Background:
    When I add "Digital Public Library of America" account from welcomeScreen
      And I open Catalog
      And I open search modal
      And I search for 'Flower Fables'
      And DOWNLOAD book from Subcategory List view and save it as 'bookInfo'
      And I open book 'bookInfo' details by clicking on cover
      And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen

  @tier1 @oldOs
  Scenario: Navigate by Page
    When I click on right book corner
      And I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on EpubReaderScreen
      And I click on right book corner
    Then Next page is open and old page has 'pageNumberKey' pageNumber and 'chapterNameKey' chapterName
    When I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on EpubReaderScreen
      And I click on left book corner
    Then Previous page is open and old page has 'pageNumberKey' pageNumber and 'chapterNameKey' chapterName

  @tier1 @oldOs
  Scenario: Navigate by Table of Contents Menu
    Then Each chapter can be opened from Table of Contents

  @tier1 @oldOs
  Scenario: Navigate View options
    When I return to previous screen for epub and pdf
      And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen
    When I open font choices for book
    Then Font choices screen is present
    When I close font choices
      And I open Table of Contents
    Then Table of Contents is opened

  @tier1 @oldOs
  Scenario: Change, View Font and Contrast Settings
    When I save font size as 'fontSize'
    And I INCREASE_FONT of text
    Then Font size 'fontSize' is increased
    When I save font size as 'fontSize'
    And I DECREASE_FONT of text
    Then Font size 'fontSize' is decreased
    When I change font style to FONT_SERIF
    Then Book text displays in FONT_SERIF font
    When I change font style to FONT_SANS
    Then Book text displays in FONT_SANS font
    When I change font style to FONT_DYSLEXIC
    Then Book text displays in FONT_DYSLEXIC font
    When I change contrast to BLACK_TEXT_ON_WHITE
    Then The BLACK_TEXT_ON_WHITE background is correct
    When I change contrast to BLACK_TEXT_ON_SEPIA
    Then The BLACK_TEXT_ON_SEPIA background is correct
    When I change contrast to WHITE_TEXT_ON_BLACK
    Then The WHITE_TEXT_ON_BLACK background is correct

  @tier1 @oldOs
  Scenario: Return to Page (Bookmarking)
    When I scroll page forward from 1 to 2 times
      And I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on EpubReaderScreen
      And I wait for 3 seconds
      And I return to previous screen for epub and pdf
      And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen
      And PageNumber 'pageNumberKey' is correct
    When I click on right book corner
      And I save pageNumber as 'pageNumberKey' and chapterName as 'chapterNameKey' on EpubReaderScreen
      And I wait for 3 seconds
      And I restart app
      And I open Books
    Then Book 'bookInfo' with READ action button is present on Books Screen
    When I open 'bookInfo' book with READ action button on Books Screen
      And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen
      And PageNumber 'pageNumberKey' is correct
