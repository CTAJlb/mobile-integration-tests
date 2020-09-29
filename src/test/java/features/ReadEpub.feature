Feature: Read EPUB

  Background:
    Given Application is opened
    When I open Catalog
    Then Books feed is loaded
    When I open search modal
    Then Search modal is opened
    When I set text to the search textBox 'Flower Fables'
      And I click apply search button
    Then Search modal is closed
      And Search page is opened
    When DOWNLOAD book and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button at catalog books screen
    When I open book 'bookInfo' details by clicking on cover
      And Press on the book details screen at the action button READ
    Then Book 'bookInfo' is present on screen

  Scenario: Navigate by Page
      And Book page number is 1
    When I save page info as 'pageInfo'
      And I click on right book corner
    Then Book page number is bigger then previous 'pageInfo'
    When I save page info as 'pageInfo'
      And I swipe from right to left book corner
    Then Book page number is bigger then previous 'pageInfo'
    When I save page info as 'pageInfo'
      And I click on left book corner
    Then Book page number is smaller then previous 'pageInfo'
    When I save page info as 'pageInfo'
      And I swipe from left to right book corner
    Then Book page number is 1

  Scenario: Navigate by Table of Contents Menu
      And Each chapter can be opened from Table of Contents

  Scenario: Navigate View options
    When I return to previous screen
      And Press on the book details screen at the action button READ
    Then Book 'bookInfo' is present on screen
    When I open font choices for book
    Then Font choices screen is present
    When I close font choices
      And I open Table of Contents
    Then Table of Contents is opened

  Scenario: Change, View Font and Contrast Settings
#    When I save font size as 'fontSize'
#      And I INCREASE_FONT of text
#    Then Font size 'fontSize' is increased
#    When I save font size as 'fontSize'
#      And I DECREASE_FONT of text
#    Then Font size 'fontSize' is decreased
#    When I change font style to SERIF
#    Then Book text displays in serif font
#    When I change font style to SANS_SERIF_ARIAL
#    Then Book text displays in sans-serif arial font
#    When I change font style to ALTERNATIVE_SANS
#    Then Book text displays in alternative sans font
    When I change contrast to WHITE_TEXT_ON_BLACK
    Then Book text displays WHITE on BLACK
    When I change contrast to BLACK_TEXT_ON_WHITE
    Then Book text displays BLACK on WHITE
    When I change contrast to BLACK_TEXT_ON_SEPIA
    Then Book text displays BLACK on SEPIA

  Scenario: Return to Page (Bookmarking)
    When I scroll page forward from 10 to 20 times
      And I save page info as 'pageInfo'
      And I return to previous screen
      And Press on the book details screen at the action button READ
    Then Book 'bookInfo' is present on screen
      And Page info 'pageInfo' is correct
    When I restart app
      And I open Books
    Then Book 'bookInfo' is present in Books List
    When I open book 'bookInfo' details by clicking on cover
      And Press on the book details screen at the action button READ
    Then Book 'bookInfo' is present on screen
      And Page info 'pageInfo' is correct
