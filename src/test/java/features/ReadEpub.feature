Feature: Read EPUB

  Background:
    Given I add "Digital Public Library of America" account from welcomeScreen
    When I open Catalog
      And I open search modal
      And I search for 'Flower Fables'
      And DOWNLOAD book from subcategory screen and save it as 'bookInfo'
      And I open book 'bookInfo' details by clicking on cover
      And Press on the book details screen at the action button READ
    Then Book 'bookInfo' is present on screen

  @tier1
  Scenario: Navigate by Page
      And Book page number is 1
    When I save page info as 'pageNumberInfo' and 'chapterNameInfo'
      And I click on right book corner
    Then Navigated to the next page and old page 'pageNumberInfo' and 'chapterNameInfo'
    When I save page info as 'pageNumberInfo' and 'chapterNameInfo'
      #And I swipe from right to left book corner
    #Then Navigated to the next page and old page 'pageNumberInfo' and 'chapterNameInfo'
    When I save page info as 'pageNumberInfo' and 'chapterNameInfo'
      And I click on left book corner
    Then Navigated to the previous page and old page 'pageNumberInfo' and 'chapterNameInfo'
    #When I save page info as 'pageNumberInfo' and 'chapterNameInfo'
      #And I swipe from left to right book corner
    #Then Book page number is 1

  @tier1
  Scenario: Navigate by Table of Contents Menu
      And Each chapter can be opened from Table of Contents

  @tier1
  Scenario: Navigate View options
    When I return to previous screen
      And Press on the book details screen at the action button READ
    Then Book 'bookInfo' is present on screen
    When I open font choices for book
    Then Font choices screen is present
    When I close font choices
      And I open Table of Contents
    Then Table of Contents is opened

  @tier1
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
    When I change contrast to WHITE_TEXT_ON_BLACK
    Then Book text displays WHITE on BLACK
    When I change contrast to BLACK_TEXT_ON_WHITE
    Then Book text displays BLACK on WHITE
    When I change contrast to BLACK_TEXT_ON_SEPIA
    Then Book text displays BLACK on SEPIA

  @tier1
  Scenario: Return to Page (Bookmarking)
    When I scroll page forward from 10 to 20 times
    #chapterNameInfo does not check in Assert for ios we check pageNumber and chapterName fot android only pageNumber
      And I save page info as 'pageNumberInfo' and 'chapterNameInfo'
      And I return to previous screen
      And Press on the book details screen at the action button READ
    Then Book 'bookInfo' is present on screen
      And Page info 'pageNumberInfo' is correct
    When I restart app
      And I open Books
    Then Book 'bookInfo' is present in Books List
    When I open book 'bookInfo' details by clicking on cover
      And Press on the book details screen at the action button READ
    Then Book 'bookInfo' is present on screen
      And Page info 'pageNumberInfo' is correct
