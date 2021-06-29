Feature: Digital library

  Background:
    Given I add "Digital Public Library of America" account from welcomeScreen
    And I open Catalog
    And I open category by chain:
      | Fiction |
      | Drama   |

@logout @returnBooks @tier2 @go1
Scenario: Check out from Book Detail View and Return from Books
  When I open the book details for the subsequent DOWNLOAD on Subcategory List View and save it as 'bookInfo'
  Then I check that book contains READ action button on book details view
  When I open Books
  Then Book 'bookInfo' is present in Books List
  When I open book 'bookInfo' details by clicking on cover
    And I press on the book details view at the action button RETURN
  Then I check that book contains GET action button on book details view
  When I open Books
  Then Book 'bookInfo' is not present in Books List

  @logout @returnBooks @tier2 @go1
  Scenario: Check out from Book Detail View and Return from Book Detail View
    When I open the book details for the subsequent DOWNLOAD on Subcategory List View and save it as 'bookInfo'
    And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen
    When I return to previous screen
    And I press on the book details view at the action button RETURN
    Then I check that book contains GET action button on book details view

  @logout @returnBooks @tier2 @go1
  Scenario: Check out from Subcategory List View and Return from Books
    When DOWNLOAD book from subcategory screen and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button on Subcategory List View
    When I click on the book 'bookInfo' button READ on catalog books screen
    Then Book 'bookInfo' is present on screen
    And I return to previous screen
    When I open Books
    Then Book 'bookInfo' is present in Books List
    When I open book 'bookInfo' details by clicking on cover
    And I press on the book details view at the action button RETURN
    Then I check that book contains GET action button on book details view
    When I open Books
    Then Book 'bookInfo' is not present in Books List

  @logout @returnBooks @tier2 @exclude_android @go1
  Scenario: Check out from Subcategory List View and Return from Subcategory List View
    When DOWNLOAD book from subcategory screen and save it as 'bookInfo'
    And I click on the book 'bookInfo' button RETURN on catalog books screen
    Then Book saved as 'bookInfo' should contain READ button on Subcategory List View

  @logout @returnBooks @tier2 @go1
  Scenario: Check out from Subcategory List View and Read from Books
    When DOWNLOAD book from subcategory screen and save it as 'bookInfo'
    Then Book saved as 'bookInfo' should contain READ button on Subcategory List View
    When I click on the book 'bookInfo' button READ on catalog books screen
    Then Book 'bookInfo' is present on screen
    And I return to previous screen
    When I open Books
      And I open book 'bookInfo' details by clicking on cover
      And I press on the book details view at the action button READ
    Then Book 'bookInfo' is present on screen
