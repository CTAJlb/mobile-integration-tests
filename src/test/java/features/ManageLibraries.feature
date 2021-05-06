Feature: Manage Libraries

  Background:
    Given I find "Digital Public Library of America" library

  @tier1
  Scenario: Add Library
    When I add 'Alameda County Library' account
    Then Account 'Alameda County Library' is present on Accounts screen

  @tier1
  Scenario: Switch Library Catalogs
    When I add 'Alameda County Library' account
      And Catalog is opened
      And I get names of books on screen and save them as 'nameOfBooks'
      And I switch to 'Alameda County Library' from side menu
    Then Books feed is loaded
      And List of books on screen is not equal to list of books saved as 'nameOfBooks'

  @tier1
  Scenario: Remove library
    When I add 'Alameda County Library' account
      And I remove 'Alameda County Library' account
    Then Account 'Alameda County Library' is not present on Accounts screen

  @tier2
  Scenario: Switch library bookshelf
    When I add 'Alameda County Library' account
      And Catalog is opened
      And I switch to 'Digital Public Library of America' from side menu
      And I open category by chain:
        | Fiction |
        | Drama   |
      And DOWNLOAD book and save it as 'bookInfo'
      And I open Books
    Then Book 'bookInfo' is present in Books List
    When I open Catalog
      And I return to previous screen
      And I switch to 'Alameda County Library' from side menu
      And I open Books
    Then No books are present in Books list

  @logout @returnBooks @tier2 @fixed
  Scenario: Switch Library Reservations
    When I add 'Alameda County Library' account
    When I add 'LYRASIS' account
      And I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open Catalog
      And I switch to 'LYRASIS' from side menu
      And I open search modal
    When I search for 'The Premonition: A Pandemic Story'
      And I open 'EBOOK' book with name 'The Premonition: A Pandemic Story' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And I press on the book details screen at the action button RESERVE
    Then I check that opened book contains CANCEL button at book details screen
    When I open Holds
    Then Holds feed is loaded
      And Book 'bookInfo' is present in Holds List
    When I open Catalog
      And I open Catalog
      And I switch to 'Alameda County Library' from side menu
      And Open Holds
    Then Holds feed is loaded
      And No books are present in Holds list

  @logout @tier2
  Scenario: Store library card
    When I add 'LYRASIS' account
    Then Account 'LYRASIS' is present on Accounts screen
    When I enter credentials for 'LYRASIS' account
    Then Login is performed successfully
    When I open account 'LYRASIS'
      And I click the log out button on the account screen
    Then Text on Logout button is changed to Log in on Account screen
