
# README file
high level summary of the projects from the Udacity Android Nano Degree course.
There are nine projects in the Android Basics Nanodegree course. Each build on each other to layer key concepts in building an app. 
#### [Project 1](https://github.com/trigal2012/AndroidNanoDegree#project-1-build-a-single-screen-app) - Single Screen App (A simple business card type app for a local business) - Complete
#### [Project 2](https://github.com/trigal2012/AndroidNanoDegree#project-2-score-keeper-app) - Score Keeper App (A two team scoring game to learn about buttons, onclick methods, keeping track of scores) - Complete
#### [Project 3](https://github.com/trigal2012/AndroidNanoDegree#project-3-quiz-app) - Quiz App (a multi-format question quiz to accept and grade user input) - Complete
#### [Project 4](https://github.com/trigal2012/AndroidNanoDegree#project-4-music-structure-app) - Music Structure App (a framework for building a podcast or music player, introduction to listviews, recyclerviews, custom class and custom class adapters, intents, multiple activity files) - Complete
#### [Project 5](https://github.com/trigal2012/AndroidNanoDegree#project-5-tourapp) - Tour App (a framework for creating touring apps for cities or regions, using list views, fragments, and Bundles, minimal activity files) - Complete
#### [Project 6](https://github.com/trigal2012/AndroidNanoDegree/blob/master/README.md#project-6-newsapp) - News App (part 1 - Connecting to an API, Parsing the JSON response, Handling error cases gracefully, Updating information regularly, Handling Async and background tasks, Doing network operations independent of the Activity lifecycle) - Complete
#### [Project 7](https://github.com/trigal2012/AndroidNanoDegree#project-7-newsapp-with-settings) - New App part 2 - adding in user permissions to control the data requested for the news feed - Complete
#### [Project 8](https://github.com/trigal2012/AndroidNanoDegree#project-8-inventory-app---DB-setup-only) - Inventory app 1 - integrating a sqlite3 database with an app - Complete
#### [Project 9](https://github.com/trigal2012/AndroidNanoDegree#project-9-inventory-app---UI) - Inventory app 2 - adding UI to the db created in Project 8, direct edit of list row item from the listview, details view with editing, Dialog pop-up for Delete Confirmation and leaving edit screen early, UI validation, API validation, DB security controls

## Project 9: Inventory App - UI
key learnings:
* adding Textwatchers to control how users can enter values into a data field
  * quanity nor price can start with 0
  * price can have only 2 digits after the decimal
* using tags in List adaptor item rows to track the DB row index for each item
* Cursors and Load Managers to get data for the views
* Content Providers - replace POJO for the DB with Content Provider to basically build an API Layer into the SQL database
* UI and workflow features to include:
  * message to indicate an empty database
  * Toast Measages to Warn user that data is not valid, Product was saved, Product was not saved
  * Dialogs for Delete Confimration and Edit Cancellation
  * buttons to increase/decrease quantity for the list row with validation to guard against negative value
  * Abilty to Edit Product details
  * Ability to Delete the Product
  * Ability to call the supplier based on the number provided
  
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/Home_no_items.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/Add_new_item.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/Product_added.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/Product_added_subtract_disabled.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/Edit_Product.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/Save_Error.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/Delete_confirmation_dialog.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/Quit_Edit_dialog.png" width="200" height="350"></kbd>

## Project 8: Inventory App - DB setup only
Key Learnings:
* integrating a sqlite3 database with an app, icluding:
  * Contracts - specifies reference values for table names and column names which can be used through out the app
  * DBHelpers - this si where you specify the tables, data types for the information in the tables, as well as all of your CRUD actions
  * Models - while not specifically part of this lesson I wanted to understand how DB Object models (that we learned about in lesson 4) would work with an actual database
* this project was focused on DB setup, configuration, adding mock data and displaying that data. the UI work is intentionally minimal. The functionality includes:
  * the ability to add data into one of three tables Categories, Suppliers, Products
    * the screen refreshes to show new data once it is inserted into the DB
  * the ability to delete all data in all tables
  * the ability to display the contents of the three tables
  
### Comment from the reviewer
<dl><dt>This project has a great user interface beyond of this project rubrics. Good job 👍 </dt></dl>

<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/start_no_items_in_db.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/action_list.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/add_category.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/add_suppliers.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/Inventory1/add_products.png" width="200" height="350"></kbd>

## Project 7: NewsApp with Settings
Key learnings:
   * The API integration with The Guardian news API was a bit wonky. The available documetion explained the basics of the api construct but had few detals on the data structure. For example, there is the idea of "edition" which is supposed to be country or region specific news with the regions being "us, "uk", "au" and "international". There is another concept called "sections" wich is a means to categorize the type of news to be returned, for example sports, business, money, technology. The basic api contruct as specified in the available documentation to get "us" centric "sports should be https://content.guardianapis.com/us/sport however, depending on the section, I found I had to do this:
<br> https://content.guardianapis.com/edition-section or even a third option: https://content.guardianapis.com/section/edition. </br>
    * The option to use depended on the section. The api did not provide data details at this level. I was able to figure this out by inspecting the guardian website and creating various javascript functions to parse a variety of GET request results.</br>
   here are some examples:<br>
    NEWS CATEGORY | Original URL Tried | Actual URL Needed <br>
    us news| https://content.guardianapis.com/us/news | https://content.guardianapis.com/us-news <br>
    european travel| https://content.guardianapis.com/uk/travel | https://content.guardianapis.com/travel/europe <br>
    cycling|https://content.guardianapis.com/cycling | https://content.guardianapis.com/sport/cycling 
      
### Comment from the reviewer
<dl><dt>Awesome, great job here! You've made good use of the Loader framework to grab your data and you've parsed it well and displayed it properly after that. This is quite a common set of operations that we will be performing often, so keep the lessons learned during this project in mind as you go forward and write more Android apps! Well done, have fun with whatever you work on next!</dt></dl>

<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/NewsApp2/settings.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/NewsApp2/settings_menu.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/NewsApp2/settings_news_pref.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/NewsApp2/settings_num_results.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/NewsApp2/settings_results.png" width="200" height="350"></kbd>

#### Funtionality I'd like to add:
 * add Navigation Drawer for News Categories
 * add Tabs for News subCategries
 * change list views to be more performant - use something like Recycle view
 * add search ability
 * Change settings to have Edition
 * add a setting for "My News" where user can specify preferred News Categories and subcategories,
 * the Nav drawer will have an option for "My News" along with the other main News catgories

## Project 6: NewsApp
1. Key learnings:
   * API integration with The Guardian news API - https://open-platform.theguardian.com/documentation
     * external libraries for integrating with the above API, JSONParsing, or handling network connectivity were not allowed so they needed to be created manually
   * JSONResponse Parsing
   * Error handling
     * to cover network connectivity issues
     * malformed data
     * missing data - response was formed correctly but requested fields either don't exist or have no data

### Comment from the reviewer
<dl><dt>Hi Student, that was an excellent effort and you did a great job! 🎉 🎊:smile:</dt></dl>
<dl><dt>You have made an very good User Interface and the code quality was great.🎉 🎊:smile:</dt></dl>
<dl><dt>As a programmer, a major part of your work would be to find and fix bugs and focus on feature completion. Great job on doing that this time 🎉:smile:</dt></dl>

<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/NewsApp1/appLoad.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/NewsApp1/intentToWeb.png" width="200" height="350"></kbd>
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/NewsApp1/openInGuardian.png" width="200" height="350"></kbd>
   
## Project 5: TourApp
Key learnings:
   * App contains at least 4 lists of relevant attractions for a location - Home page, + 4 lists, one for events around town, food/drink/coffee, walking tour, outside art . 
   * Each list has it's own icon in the nav drawer
   * User navigates between lists in Fragments using a Navigation Drawer
   * All lists provide for selecting a row and going to another fragment displaying additional details. The type of details vary per list, though only one "details fragment" is used.
   * Art and walking tours have pics
   * lists with an address will auto link to google maps when the address is clicked
   * details page with phone and address will autolink to a phone app or map app 
   * For this exercise I focused on functionality rather than visual appeal.
   * Challenges/Issues:
    * determine the best way to pass data between fragments - ended up choosing Bundles
    * adding the autolink feature to a TextView would interfere with the onItemclickListner I had setup on the row items in the list view. lots of searching landed me on this attribute, a complete lifesaver! ```android:descendantFocusability="blocksDescendants"```
   
   https://stackoverflow.com/questions/39626337/can-someone-explain-descendantfocusability-afterdescendants
   
   https://developer.android.com/reference/android/view/ViewGroup
   
### Comment from the reviewer
<dl><dt>Great work overall. Thank you for your effort, see you in the next project :smile:</dt></dl>

   
<kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/TourApp/home.png" width="200" height="350"></kbd><kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/TourApp/navigation_drawer.png" width="200" height="350"></kbd><kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/TourApp/artoutside_withmap.png" width="200" height="350"></kbd><kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/TourApp/art_details.png" width="200" height="350"></kbd><kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/TourApp/restaurants.png" width="200" height="350"></kbd><kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/TourApp/restaurant_details.png" width="200" height="350"></kbd><kbd><img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/TourApp/walking_tour.png" width="200" height="350"></kbd><kbd>
<img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/TourApp/event_details.png" width="200" height="350"></kbd>
 
 #### Funtionality I'd like to add:
 * get data from a db - use actual data not placeholder data
 * artist name, name of artwork, details of the painting
 * ability to upload image and location of other painted utility boxes and/or other outdoor art
 * add food type icons to food and beverage list
 * add ability to click on image thumbnail to get to a full screen image
 * add a walking tour interactive map
 * with pins for each location
 * click on pins to get more information
 * "start" the tour to get directions from where you are to the 1st pin on the map

## Project 4: Music Structure App
1. Key learnings:
    * Using multiple Activities
    * Implementing The RecyclerView and the various partial layout files to efficiently create an infinite scrolling list based on the number of entries in the data ArrayList
    * Using Intents to pass data from a Recycler View row to another activity
    * Using the Batch drawable import in Android Studio to quickly convert images to all relevant sizes
    * Introduction to Objects and ObjectAdapters to get info out of an ArrayList to create list views
    * learned about Using Tabs along the top of the screen so users could swipe left/right to see more data
    
    I spent a lot of extra time on this one to make sure I had a good handle on Objects, Object Adapters and the intricacies of using the RecyclerView
2. The design must include:
    * The app’s structure is suitable for a music player app. A similarly structured app which focuses on audiobooks, podcasts, or other audio media is also acceptable.
    * The app contains 2 to 6 activities
    * Features in the app are clearly defined either by labelling or images. For example, a button to play music could use a universally recognized triangular "Play" symbol or could have the text label "Play".
    * Each activity contains button(s) which link it to other activities a user should be able to reach from that activity. For instance, a ‘Library’ activity might contain a button to move to the ‘Now Playing’ activity.
    * Each button’s behavior is determined by an OnClickListener in the Java code rather than by the android:onClick attribute in the XML Layout.
    * Each button properly opens the intended activity using an explicit Intent.
    * App uses a custom adapter to populate the layout with views based on instances of the custom class.
    * Information about instances of the custom class are stored in an appropriate data structure (e.g. ArrayList, Array).
    * When the information needs to be displayed, it is efficiently retrieved (e.g. Looping).
    * Data about each song (or equivalent audio media such as podcast episode) should be stored in a custom class that contains at least 2 states (e.g. Song Name, Artist Name)
    * If images are included (e.g. Album Art), they are stored as drawables. All drawables are stored at multiple densities. Images are not required.
<kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/MusicStructureApp/category_list.png" width="200" height="350">
</kbd><kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/MusicStructureApp/lesson_list.png" width="200" height="350">
</kbd><kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/MusicStructureApp/lesson_details.png" width="200" height="350">
</kbd><kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/MusicStructureApp/pause.png" width="200" height="350">
</kbd>


## Project 3: Quiz App
1. Key learnings:
   * using new types of views such as radio buttons, checkboxes, infinite +scrolling view
   * capturing user input - text, radio buttons, checkboxes
   * applying logic to keep track of user input on screen roation and layout changes
2. The Design must include
   * App contains 4 - 10 questions, including at least one check box, one radio button, and one text entry.
   * Questions are in a variety of formats such as free text response, checkboxes, and radio buttons.
   * Checkboxes are only used for questions with multiple right answers. Radio buttons are only used for questions with a single right answer.
   * App includes a button for the user to submit their answers and receive a score.
   * The app includes at least four of the following Views: TextView, ImageView, Button, Checkbox, EditText, LinearLayout, RelativeLayout, ScrollView, RadioButton, RadioGroup.
   * * If applicable, the app uses nested ViewGroups to reduce the complexity of the layout.
   The app gracefully handles displaying all the content on screen when rotated. Either by updating the layout, adding a scrollable feature or some other mechanism that adheres to Android development guidelines.
   * The app contains at least one if/else statement
   * The grading logic checks each answer correctly. The app accurately calculates the number of correct answers and does not include incorrect answers in the count.
   * Note: When applicable, in the grading logic remember to check that the correct answers are checked AND the incorrect answers are not checked.
   
   ### Comment from reviewer:
   <dl><dt>Awesome job here,Your app is more than wonderful. I am very happy to have reviewed your project.
   You should be proud of yourself, if I can rating you I will give you Definitely the highest rating. Keep it up and you will have a great place in the field.</dt><dl>
<kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/QuizMe/start.png" width="200" height="350">
</kbd> <kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/QuizMe/radio_button.png" width="200" height="350">
</kbd> <kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/QuizMe/text_entry.png" width="200" height="350">
</kbd> <kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/QuizMe/score.png" width="200" height="350">
</kbd>

## Project 2: Score Keeper App
1. key learnings:
    * create more sophisticated xmls to include buttons and other items users can interact with
    * start learning java and writing backend logic to generate the desired results based on user actions
2. The Design must include:
    * The chosen game has either multiple amounts of points that can be scored, as in american football, or multiple important metrics to track, such as fouls, outs, and innings in baseball.
    * App is divided into two columns, one for each team.
    * Each column contains a large TextView to keep track of the current score for that team.
      ** Optionally, a second TextView to track another important metric such as fouls can be added.
    * Each column contains multiple buttons. The buttons must track either:
    * Each track a different kind of scoring, Or
    * Each score button updates the score TextView in its column by adding the correct number of points.
    * The reset button resets the scores on both of the score TextViews.
    * Each track a different metric (one score, the other fouls, for instance).
<kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/ScoreKeeperApp/start.png" width="200" height="350">
</kbd>
<kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/ScoreKeeperApp/team1_lucky_lost_4.png" width="200" height="350">
</kbd>
<kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/ScoreKeeperApp/team2_lucky_won_12.png" width="200" height="350">
</kbd>
<kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/ScoreKeeperApp/team1_lucky_draw.png" width="200" height="350">
</kbd>
    
## Project 1: Build a Single Screen App
1. Key learnings:
    * build a basic understanding of how android studio works
    * understand how to create views using xmls, use autolinks, images, and varying layouts to create the desired design
2. The design must include:
    * Business name
    * At least one photo representing the business
    * Two or more other pieces of information, such as:
    * Contact information for the business (eg phone number, email address, website)
    * Address of the Business
    * Description of business
    * Hours of operation
    * Program must function in Android Studio emulator and on a phone. Upon launching on a phone, the card appears.
    * Layout scales responsively for screen sizes in portrait mode, with no part of any images only partially visible. Student is not responsible for responsive design in landscape mode.
    * XML must include a Relative Layout, Linear Layout, or Constraint Layout
    * Must have one or more image views or text views.
    * Elements are specified in dp, text is specified in sp.
<kbd>
  <img src="https://github.com/trigal2012/AndroidNanoDegree/blob/master/app_images/SingleScreenApp/SingleScreenApp.png" width="200" height="350">
</kbd>
