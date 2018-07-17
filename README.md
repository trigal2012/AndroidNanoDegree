# Udacity AndroidNanoDegree

# README file
high level summary of the projects from the Udacity Android Nano Degree course.

## Music Structure App
1. Key learnings:
    * Using multiple Activities
    * Implementing The RecyclerView and the various partial layout files to efficiently create an infinite scrolling list based on the number of entries in the data ArrayList
    * Using Intents to pass data from a Recycler View row to another activity
    * Using the Batch drawable import in Android Studio to quickly convert images to all relevant sizes
    * Introduction to Objects and ObjectAdapters to get info out of an ArrayList to create list views
    
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
   Awesome job here,Your app is more than wonderful. I am very happy to have reviewed your project.
   You should be proud of yourself, if I can rating you I will give you Definitely the highest rating.
   Keep it up and you will have a great place in the field.
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
