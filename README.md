# AndroidNanoDegree README file
high level summary of the projects from the Udacity Android Nano Degree course.


Project 1: Build a Single Screen App
Requirements: 

Your design must include:

Business name
At least one photo representing the business
Two or more other pieces of information, such as:
Contact information for the business (eg phone number, email address, website)
Address of the Business
Description of business
Hours of operation

Program must function in Android Studio emulator and on a phone. Upon launching on a phone, the card appears.

Layout scales responsively for screen sizes in portrait mode, with no part of any images only partially visible. Student is not responsible for responsive design in landscape mode.

Note: Reviewers will test on a Nexus 5X which has a screen size of 5.2 inches (132.08mm). It is advised you test on this size at minimum. If your content does not fit on this size, consider implementing a ScrollView.

XML must include a Relative Layout, Linear Layout, or Constraint Layout
Must have one or more image views or text views.
Elements are specified in dp, text is specified in sp.

STUDENT NOTES
devices used for testing:
-->emulator - nexus5x
-->actual - HTC M7

-----------questions-------------

Performance
I purposefully played around with varying combinations or Relative and Linear Layouts. What I'm not clear on is how using these will impact performance. For example, using LinearLayouts look to require much less code compared to getting a similar look and feel using Relative layouts. so some explanation about app performance as well as how to test my app performance would be helpful.

responsive design
This item in the requirements was not clear to me:
"Layout scales responsively for screen sizes in portrait mode, with no part of any images only partially visible. Student is not responsible for responsive design in landscape mode."
I understand "responsive design" but I do not understand how this is going to be tested. What is the largest size our business card should conform to? Do we need to have it work for tablet sizes or just phone sizes?

the phone I can use for testing is not as large as the Nexus 5X which has a screen size of 5.2 inches (132.08mm) so I verified using just the emulator. I made the emulator larger and smaller by dragging the corners and the layout scaled cleanly, however if I changed the size by selecting a device larger than the Nexus 5X from within the “Preview” tool of Android Studio, the selected design did not look as good. Can anyone clarify how this requirement is to be tested? “Layout scales responsively for screen sizes”...? dragging the size of the emulator or loading the project and changing the device in the Preview tool of Android studio.

Styles and dimens
I'm not clear on if the styles.xml should reference the dimens.xml or if those two should be kept separate and that the layout xml should have line items for both?

