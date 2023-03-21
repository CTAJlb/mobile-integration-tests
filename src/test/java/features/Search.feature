Feature: Search libraries and books

  @tier2
  Scenario: Find a library and delete it
    When Tutorial screen is opened
      And Close tutorial screen
      And Close welcome screen
      And Enter 'Brookfield Library' library and save name as 'libraryName' on add account screen
    Then Library 'libraryName' is present on add account screen
    When Clear search field on add account screen
    Then Search field is empty on add account screen

  @tier2
  Scenario Outline: Check that library name contains one or more entered latin letters
    When Tutorial screen is opened
      And Close tutorial screen
      And Close welcome screen
      And Enter word <word> and save as 'info' on add account screen
    Then Libraries contain word 'info' on add account screen

  Scenarios:
    |word    |
    |book    |
    |F       |
    |lyrasis |
    |LYRASIS |
    |lYrAsIs |

  @tier2
  Scenario Outline: Enter invalid data
    When Tutorial screen is opened
      And Close tutorial screen
      And Close welcome screen
      And Enter word <data> and save as 'data' on add account screen
    Then Search result is empty on add account screen

  Scenarios:
    |data                                 |
    |книга                                |
    |9822                                 |
    |<font color=red>Red text</font>      |
    |<script>alert(‘hello world’)</script>|
    |@                                    |
    |$!                                   |

  @tier2
  Scenario: Find a book in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
      And Open search modal
      And Search for 'El gato negro' and save bookName as 'bookNameInfo'
    Then EBOOK book with GET action button and 'bookNameInfo' bookName is present on catalog books screen

  @tier2
  Scenario: Delete a book from search bar in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
      And Open search modal
      And Enter book 'El gato negro' and save it as 'bookNameInfo'
    When Clear search field on catalog books screen
    Then Search field is empty on catalog books screen

  @tier2
  Scenario Outline: Check that books from search result contain one or more entered latin letters or numeric in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
      And Open search modal
      And Enter word <word> and save as 'info' on catalog books screen
    Then Books contain word 'info' on catalog books screen

    Scenarios:
    | word          |
    | cat           |
    | a             |
    | 3            |

  @tier2
  Scenario Outline: Find a book with name in different font cases in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
      And Open search modal
      And Enter word <word> and save as 'info' on catalog books screen
    Then The first book has 'info' bookName on catalog books screen

    Scenarios:
      | word          |
      | el gato negro |
      | EL GATO NEGRO |
      | eL gAto NeGrO |

  @tier2
  Scenario Outline: Enter invalid data in book name in Palace
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "Palace Bookshelf" on Add library screen
      And Open search modal
      And Enter word <data> and save as 'info' on catalog books screen
    Then There is no results on catalog books screen

    Scenarios:
    | data                                  |
    | рнл                                   |
    | <font color=red></font>               |
    | <script>alert(‘hello world’)</script> |
    | @$                                    |
    | !                                     |

  @tier2
  Scenario: Check a placeholder in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
    Then Placeholder contains "Search" text in search field

  @tier2
  Scenario: Check the possibility of editing data in search field in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
      And Enter book "Book" and save it as 'word'
      And Edit data by adding "hello" in search field and save it as 'newWord'
    Then Placeholder contains 'newWord' text in search field

  @tier2
  Scenario: Check of empty field in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
    Then There is no possibility to search with empty search field

  @tier2
  Scenario: Check of only spaces input in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
      And I input only spaces in search field
    Then There is no results on catalog books screen

  @tier2
  Scenario: Check of displaying the search field after search a book in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
      And Search for "Book" and save bookName as 'BookNameInfo'
    Then The search field is displayed and contains 'BookNameInfo' book

  @tier2
  Scenario: Find a book in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
      And Search for 'Silk Road' and save bookName as 'bookNameInfo'
    Then EBOOK book with GET action button and 'bookNameInfo' bookName is present on catalog books screen

  @tier2
  Scenario: Delete a book from search bar in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
      And Enter book 'Silk Road' and save it as 'bookNameInfo'
    When Clear search field on catalog books screen
    Then Search field is empty on catalog books screen

  @tier2
  Scenario Outline: Check that books from search result contain one or more entered latin letters or numeric in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
      And Enter word <word> and save as 'info' on catalog books screen
    Then Books contain word 'info' on catalog books screen

    Scenarios:
      | word          |
      | cat           |
      | a             |
      | 1            |

  @tier2
  Scenario Outline: Find a book with name in different font cases in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
      And Enter word <word> and save as 'info' on catalog books screen
    Then The first book has 'info' bookName on catalog books screen

    Scenarios:
      |word       |
      | silk road |
      | SILK ROAD |
      | SiLk rOaD |

  @tier2
  Scenario Outline: Enter invalid data in book name in LYRASIS
    When Close tutorial screen
    Then Welcome screen is opened
    When Close welcome screen
    Then Add library screen is opened
    When Add library "LYRASIS Reads" on Add library screen
      And Open search modal
      And Enter word <data> and save as 'info' on catalog books screen
    Then There is no results on catalog books screen

    Scenarios:
      | data                                  |
      | рнл                                   |
      | <font color=red></font>               |
      | <script>alert(‘hello world’)</script> |
      | @$                                    |
      | !                                     |