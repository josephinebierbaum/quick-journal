# Quick Journal Project Report
### Description
This app is a journal app with custom shortcuts specific to the app, which cut down on writing time. The shortcuts are similar to an android spell checker framework except it uses a UI, since a SpellChecker class that corrects small phases within normal writing into full sentences may not be feasible for this project. The shortcuts are stored as data that the user can access in a menu while typing a journal entry. Other than the shortcuts, the app has a similar interface to many journaling apps with navigation to view old entries and add new ones.
### Purpose
This app would be convenient for those without much time to journal. Journaling is a leisurely activity, but there is also research that journaling manages stress and eases mental illness. However, for those who have a busy schedule, journaling may not be convenient or easy to find the time to do so. By typing the important information, the user can have a sentence or short paragraph generated for them. Even though navigating through the UI and entering the information for a shortcut takes time, it will save time in the long run. The time saved by not typing the full sentence or paragraph is small, but the time consumed while thinking about how to put their thoughts into a coherent sentence for journaling can be more substantial. This app will save the time from both of these issues, which should make journaling more accessible to those with a busy schedule. Currently, to my knowledge, there isn't a journaling app that cuts down on writing time by generating a sentence or paragraph based on a few given values.
### Tools
The app will use databases and the Room library to store shortcuts and journal entries. The data will persist across sessions, and changes can be made to the data. Different fragments will prevent the UI from being cluttered. In addition, the app will be able to access the GPS to add a location to a journal entry, as well as access the camera or photos to add images to journal entries. 
### Interface
![Figure 1](https://github.com/josephinebierbaum/quick-journal/blob/master/report%20images/Figure%201%20Home.png "Figure 1")
Figure 1

Figure 1 is the home page, which is the screen shown to the user when the app is opened. Arrow buttons on the sides of the screen adjust the calendar to show the previous or upcoming week. The current day that the user is viewing is marked by a colored circle. The button on the bottom of the screen starts a new journal entry when it is clicked.

![Figure 2](https://github.com/josephinebierbaum/quick-journal/blob/master/report%20images/Figure%202%20New%20Journal.png "Figure 2")
Figure 2

When the user begins a new journal entry, they will be directed to Figure 2. The user can type in the title and entry. The button on the bottom right corner of the journal entry brings the user to Figure 4 to view or add a shortcut to the entry. The location of the user can be added using the GPS sensor. Each journal entry can have two photos, and the first photo will be used for the thumbnail of the entry. Clicking the button at the bottom of the screen will add the entry to the database and bring the user back to Figure 1.

![Figure 3](https://github.com/josephinebierbaum/quick-journal/blob/master/report%20images/Figure%203%20View%20Existing%20Journal.png "Figure 3")
Figure 3

When users click on a journal entry from Figure 1, they will come to Figure 3, where they can view the journal entry. The button at the top right corner will take the user back to Figure 2 to edit the contents of the entry. Clicking the back arrow will take the user back to Figure 1.

![Figure 4](https://github.com/josephinebierbaum/quick-journal/blob/master/report%20images/Figure%204%20View%20Existing%20Shortcuts.png "Figure 4")
Figure 4

As stated previously, clicking the button on the bottom right corner of the journal entry in Figure 2 brings the user to Figure 4 to view the current shortcuts. Figure 4 shows the shortcut fields as well as a preview of the resulting text. The button at the top corner of each shortcut brings the user to Figure 6 to edit a shortcut. Otherwise, the user can click the shortcut to go to Figure 7 to fill in the fields and add the resulting text to the entry. Clicking the button at the bottom of the screen takes the user to Figure 5 to create a new shortcut. The arrow at the top of the screen brings the user back to Figure 2.

![Figure 5](https://github.com/josephinebierbaum/quick-journal/blob/master/report%20images/Figure%205%20Create%20Shortcut.png "Figure 5")
Figure 5

Figure 5 is where the user creates a new shortcut. The button at the top right corner adds a new field for users to fill in. The text should have some meaning so that the user understands what to put there when they use the shortcut. If the row of fields becomes full, clicking the button to add another field will start another row of fields. Clicking the button at the bottom of the screen will take the user back to Figure 4. The arrow at the top of Figure 5 will take the user back to Figure 4 without adding the shortcut.

![Figure 6](https://github.com/josephinebierbaum/quick-journal/blob/master/report%20images/Figure%206%20Edit%20Existing%20Shortcut.png "Figure 6")
Figure 6

Figure 6 is where shortcuts are edited. Just like Figure 5, the button at the top right corner adds a new field for users to fill in. After making changes, the user clicks the arrow to save the changes and go back to Figure 4.

![Figure 7](https://github.com/josephinebierbaum/quick-journal/blob/master/report%20images/Figure%207%20Use%20Shortcut.png "Figure 7")
Figure 7

At Figure 7, the user fills in the information required for the shortcut to work. At the bottom, they can preview the text that will result from the shortcut. Clicking the button below the shortcut will add the resulting text to the entry and takes the user back to Figure 2. If the user clicks the arrow, they will be taken back to Figure 4 without adding any text to their journal entry.
