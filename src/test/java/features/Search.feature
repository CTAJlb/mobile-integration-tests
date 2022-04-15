Feature: Search

  Background:
    Given Tutorial screen is opened
    When Close tutorial screen
      And Close welcome screen

  @tier1
  Scenario: Find a library and delete it
    When I enter 'Brookfield Library' library and save name as 'libraryName' on add account screen
    Then Library 'libraryName' is present on add account screen
    When Clear search field on add account screen
    Then Search field is empty

  @tier1
  Scenario Outline: Check that library name contains one or more entered latin letters
    When I enter word <word> and save as 'info' on add account screen
    Then Libraries contain word 'info' on add account screen

  Scenarios:
    |word    |
    |book    |
    |F       |
    |lyrasis |
    |LYRASIS |
    |lYrAsIs |


  @tier1
  Scenario Outline: Enter invalid data (29-32)
    When I enter word <data> and save as 'data' on add account screen
    Then Search result is empty on add account screen

  Scenarios:
    |data                                 |
    |книга                                |
    |9822                                 |
    |<font color=red>Red text</font>      |
    |<script>alert(‘hello world’)</script>|
    |@                                    |
    |$!                                   |
