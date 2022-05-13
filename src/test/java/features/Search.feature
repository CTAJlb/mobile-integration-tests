Feature: Search libraries and books

  @tier1
  Scenario: Find a library and delete it
    When Tutorial screen is opened
      And Close tutorial screen
      And Close welcome screen
      And I enter 'Brookfield Library' library and save name as 'libraryName' on add account screen
    Then Library 'libraryName' is present on add account screen
    When Clear search field on add account screen
    Then Search field is empty on add account screen

  @tier1
  Scenario Outline: Check that library name contains one or more entered latin letters
    When Tutorial screen is opened
      And Close tutorial screen
      And Close welcome screen
      And I enter word <word> and save as 'info' on add account screen
    Then Libraries contain word 'info' on add account screen

  Scenarios:
    |word    |
    |book    |
    |F       |
    |lyrasis |
    |LYRASIS |
    |lYrAsIs |


  @tier1
  Scenario Outline: Enter invalid data
    When Tutorial screen is opened
      And Close tutorial screen
      And Close welcome screen
      And I enter word <data> and save as 'data' on add account screen
    Then Search result is empty on add account screen

  Scenarios:
    |data                                 |
    |книга                                |
    |9822                                 |
    |<font color=red>Red text</font>      |
    |<script>alert(‘hello world’)</script>|
    |@                                    |
    |$!                                   |

  @tier1
  Scenario: Find a book in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open search modal
      And I search for 'El gato negro' and save bookName as 'bookNameInfo'
    Then EBOOK book with GET action button and 'bookNameInfo' bookName is present on catalog books screen

  @tier1
  Scenario: Delete a book from search bar in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open search modal
      And I enter book 'El gato negro' and save it as 'bookNameInfo'
    When Clear search field on catalog books screen
    Then Search field is empty on catalog books screen

  @tier1
  Scenario Outline: Check that books from search result contain one or more entered latin letters or numeric in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open search modal
      And I enter word <word> and save as 'info' on catalog books screen
    Then Books contain word 'info' on catalog books screen

    Scenarios:
    | word          |
    | cat           |
    | a             |
    | 1            |

  @tier1
  Scenario Outline: Find a book with name in different font cases in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open search modal
      And I enter word <word> and save as 'info' on catalog books screen
    Then The first book has 'info' bookName on catalog books screen

    Scenarios:
      |word           |
      | el gato negro |
      | EL GATO NEGRO |
      | eL gAto NeGrO |


  @tier1
  Scenario Outline: Enter invalid data in book name in Palace
    When I add "Palace Bookshelf" account from welcomeScreen
      And I open search modal
      And I enter word <data> and save as 'info' on catalog books screen
    Then Search result is empty on catalog books screen

    Scenarios:
    | data                                  |
    | рнл                                   |
    | <font color=red></font>               |
    | <script>alert(‘hello world’)</script> |
    | @$                                    |
    | !                                     |

   @tier1
   Scenario: Find a book in LYRASIS
     When I add "LYRASIS Reads" account from welcomeScreen
     And I open search modal
     And I search for 'Silk Road' and save bookName as 'bookNameInfo'
     Then EBOOK book with GET action button and 'bookNameInfo' bookName is present on catalog books screen

  @tier1
  Scenario: Delete a book from search bar in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
    And I open search modal
    And I enter book 'Silk Road' and save it as 'bookNameInfo'
    When Clear search field on catalog books screen
    Then Search field is empty on catalog books screen

  @tier1
  Scenario Outline: Check that books from search result contain one or more entered latin letters or numeric in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
    And I open search modal
    And I enter word <word> and save as 'info' on catalog books screen
    Then Books contain word 'info' on catalog books screen

    Scenarios:
      | word          |
      | cat           |
      | a             |
      | 1            |

  @tier1
  Scenario Outline: Find a book with name in different font cases in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
    And I open search modal
    And I enter word <word> and save as 'info' on catalog books screen
    Then The first book has 'info' bookName on catalog books screen

    Scenarios:
      |word       |
      | silk road |
      | SILK ROAD |
      | SiLk rOaD |


  @tier1
  Scenario Outline: Enter invalid data in book name in LYRASIS
    When I add "LYRASIS Reads" account from welcomeScreen
    And I open search modal
    And I enter word <data> and save as 'info' on catalog books screen
    Then Search result is empty on catalog books screen

    Scenarios:
      | data                                  |
      | рнл                                   |
      | <font color=red></font>               |
      | <script>alert(‘hello world’)</script> |
      | @$                                    |
      | !                                     |