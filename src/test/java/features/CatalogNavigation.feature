Feature: Catalog Navigation

  @tier1
  Scenario: Return to last library catalog
    When I add "Digital Public Library of America" account from welcomeScreen
      And I add 'Alameda County Library' account
      And I open Catalog
      And I switch to 'Alameda County Library' from side menu
    Then Books feed is loaded
    When I restart app
    Then Books feed is loaded
      And Current library is 'Alameda County Library' in Catalog

  @tier1
  Scenario: Navigate Lists
    When I add "Digital Public Library of America" account from welcomeScreen
      And I open Catalog
    Then Books feed is loaded
    When I get names of books on screen and save them as 'listOfBooksOnMainPage'
      And I open 'Children\'s Books' category
    Then Current category name is 'Children\'s Books'
      And Books feed is loaded
      And Following subcategories are present:
        | Classics             |
        | Fiction              |
        | Nonfiction           |
        | All Children's Books |
    And List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'
    When I return to previous category screen
      And I open 'Nonfiction' category
    Then Current category name is 'Nonfiction'
      And Books feed is loaded
      And Following subcategories are present:
        | Biography & Memoir    |
        | Textbooks             |
        | Government & Politics |
        | Science & Technology  |
        | History               |
        | Self Help             |
        | Food & Health         |
        | Academic Press        |
        | All Nonfiction        |
      And List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'
    When I open 'All Nonfiction' subcategory
    Then Subcategory screen is present
      And Subcategory name is 'All Nonfiction'

  @tier1
  Scenario: Browse Lanes/Categories
    When I add "Digital Public Library of America" account from welcomeScreen
      And I open Catalog
    Then Books feed is loaded
      And Count of books in first lane is more than 1
    When I open 'Nonfiction' category
    Then Current category name is 'Nonfiction'
      And Following subcategories are present:
        | Biography & Memoir    |
        | Textbooks             |
        | Government & Politics |
        | Science & Technology  |
        | History               |
        | Self Help             |
        | Food & Health         |
        | Academic Press        |
        | All Nonfiction        |
    When I open 'Self Help' subcategory
    Then Subcategory screen is present
      And Subcategory name is 'Self Help'
    When I open first book in subcategory list and save it as 'bookInfo'
    Then Book 'bookInfo' is opened

  @tier1
  Scenario: Sort Lists
    When I add "Alameda County Library" account from welcomeScreen
      And I open Catalog
      And I switch to 'Alameda County Library' from side menu
    Then Books feed is loaded
    When I switch to 'Audiobooks' catalog tab
    Then Books feed is loaded
      And All present books are audiobooks
    When I switch to 'eBooks' catalog tab
    Then Books feed is loaded
    When I open category by chain:
      | Fiction |
      | Drama   |
    Then Subcategory screen is present
    When I sort books by AUTHOR
    Then Subcategory screen is present
      And Books are sorted by Author ascending
    When I sort books by TITLE
    Then Subcategory screen is present
      And Books are sorted by Title ascending
    When I save list of books as 'listOfBooks'
      And I sort books by RECENTLY_ADDED
    Then Subcategory screen is present
      And List of books on subcategory screen is not equal to list of books saved as 'listOfBooks'
    When I save list of books as 'recentlyAddedListOfBooks'
      And I sort books by RANDOM
    Then List of books on subcategory screen is not equal to list of books saved as 'recentlyAddedListOfBooks'
    When I save list of books as 'randomListOfBooks'
      And Change books visibility to show AVAILABLE_NOW
    Then All books can be loaned or downloaded
    When I change books visibility to show ALL
    Then Subcategory screen is present
    When I change books visibility to show YOURS_TO_KEEP
    Then All books can be downloaded

  @tier1 @exclude_ios @ignore
  Scenario: View Book Details
    When I add "LYRASIS" account from welcomeScreen
      And I open Catalog
      And I switch to 'LYRASIS' from side menu
    When I open search modal
    Then Search modal is opened
    When I search for 'Sullivan\'s Promise'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When I open 'EBOOK' book with name 'Sullivan\'s Promise' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And The following values in the information block are present:
        | key         | value                                           |
        | PUBLISHED   | 2021-03-05                                      |
        | PUBLISHER   | Random House Publishing Group                   |
        | DISTRIBUTOR | Bibliotheca                                     |
        | CATEGORIES  | Westerns, Western Romance, Contemporary Romance |
      And Description has text
    """
    Two unforgiving lovers reunite for the sake of their child in this fiery contemporary romance from the New York Times bestselling author of Surrender.
    """
    When I open related books
    Then Current category name is 'Related books…'
      And Count of books in subcategory 'Johnston, Joan' lane is more then 1
    When I open 'Johnston, Joan' subcategory
    Then Current category name is 'Johnston, Joan'
      And Count of books in search result is more then 1

  @tier1 @exclude_android @ignore
  Scenario: View Book Details (iOS)
    When I add "LYRASIS" account from welcomeScreen
      And I open Catalog
      And I switch to 'LYRASIS' from side menu
    When I open search modal
    Then Search modal is opened
    When I search for 'UnEnchanted'
      And I switch to 'eBooks' catalog tab
    Then Subcategory screen is present
    When I open 'EBOOK' book with name 'UnEnchanted' and save it as 'bookInfo'
    Then Book 'bookInfo' is opened
      And The following values in the information block are present:
        | key         | value                     |
        | PUBLISHED   | 29. December 2011         |
        | PUBLISHER   | Chanda Hahn               |
        | DISTRIBUTOR | Bibliotheca               |
        | CATEGORIES  | Fantasy; Folklore; Horror |
      And Description has text
    """
    	Mina Grime is unlucky, unpopular and uncoordinated, that is until she saves her crush's life on a field trip, changing her High School status from loser to hero overnight.
    	But with her new found fame brings misfortune in the form of an old family curse come to light.
    	For Mina is descended from the Brothers Grimm and has inherited all of their unfinished fairy tale business. Which includes
    	trying to outwit a powerful Story from making her its next fairytale victim.To break the fairy tale curse on her family and make these deadly
    	occurrences stop, Mina must finish the tales until the very Grimm end.
     """
    When I open related books
    Then Count of books in subcategory 'Chanda Hahn' lane is up to 3
    When I open 'Chanda Hahn' subcategory
    Then Current category name is 'Chanda Hahn'
      And Count of books in search result is up to 3
