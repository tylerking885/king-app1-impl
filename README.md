# king-Application-impl

Welcome to the Todo List program!

Users are able to manage a Todo List via the Text box, the Date Picker, and the provided buttons.



How to use the Features:

Add Button)

	In order to correctly add an item to the Todo List the user must enter something into the Description Text Box. 
	Then choose a date from the DatePicker. Finally press the Add button to add to the list. 
	Items that are first added to the list are provided a Status of Incomplete by default.

Delete Button)

	This button deletes an item from the list at a specific index. 


Delete All Button)

	Clears list of all entries.

Edit Button)

	By pressing the Edit button the user places the program in an edit status. 
	After pressing the Edit button the user can double click into a particular item and type a new description with the text box with blinking cursor.
	If the user wishes to edit the current date then this done through the Date Picker. 
	After the new description is entered and date chosen the edit is finalized by pressing the ENTER key from a keyboard.
	
	NOTE: After pressing the edit button the program is technically in a PERMANANT STATE OF EDITING.
	      The user can partially escape the edit process by pressing the ESCAPE KEY.
	      IF the user has only one item in the list the edit process hides the item in the list.
	      After pressing the ENTER KEY and commiting the edit the status of the item is overriden to Incomplete.
	      If the user doesn't enter a date and commits the edit with enter the current date is used by default.
	      If the user doesn't enter a description then description field will be blank.
 
Mark Complete Button)

	Pressing this button will change the status of an item in the list from Incomplete to Complete.


Mark Incomplete Button)

	Pressing this button will change the status of an item in the list from Complete to Incomplete.


Display Complete Button) 
	
	If the user presses this button then the Todo List is filtered to show only items with a Status of Complete.

Display Incomplete Button)

	If the user presses this button then the Todo List is filtered to show only items with a Status of Incomplete.

Display All Button)

	If the user presses this button then the Todo List will display every entry in the list.

File ComboBox)

	The FIle ComboBox contains a Save button and Load Button

	NOTE: If the user has previously clicked the Save Button then the Save Button cannot be pressed again.
	      If the user wishes to Save again the Load must be clicked and then and Canceled then the Save Button may be pressed.
	      The same is for the Load Button.

Save Button)

	Upon pressing the Save Button the users list is defaulted with the name mytodolist.txt . 
	The user may only save txt files.

Load Button)

	Upon pressing the Load Button the users list is replaced with the .txt if it is in the correct format.
	