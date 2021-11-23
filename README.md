# king-Application-impl

Welcome to the Todo List program!

---

### Table of Contents


- [Description](#description)
- [How To Use](#how-to-use)
- [Add](#add)
- [Delete](#delete)
- [Delete All](#delete-all)
- [Edit](#edit)
- [Mark Complete](#mark-complete)
- [Mark Incomplete](#mark-incomplete)
- [Display Complete](#display-complete)
- [Display Incomplete](#display-incomplete)
- [Display All](#display-all)
- [Save](#save)
- [Load](#load)
- [Author Info](#author-info)

---

## Description

Users are able to manage a Todo List via the Text box, the Date Picker, and the provided buttons.

[Back To The Top](#table-of-contents)

---

## How To Use

Below are details on the features of the program with included screenshots.

---

## Add

In order to use the Add button 2 pre-Conditions must be met.
>1) The text field and DatePicker must be entered.
>2) The text field and DatePicker entries must be in valid format.

> Valid add process on an empty list:
>
> ![Project Image](src/main/resources/readmeImages/Before-Add-Pressed.png)
>
> After the entry was added:
>
> ![Project Image](src/main/resources/readmeImages/After-Add-Pressed.png)

[Back To The Top](#table-of-contents)

---

## Delete

In order to use the Delete button 1 pre-Condition must be met.
>1) An entry must be selected before clicking Delete. Therefore, Delete may not be used on an empty list.

> Valid delete process on an occupied list (Before pressing Delete):
>
> ![Project Image](src/main/resources/readmeImages/Before-Delete-Pressed.png)
>
> Valid delete process on an occupied list (After pressing Delete):
>
> ![Project Image](src/main/resources/readmeImages/After-Delete-Pressed.png)

[Back To The Top](#table-of-contents)

---

## Delete All

DeleteAll may be used without any preconditions, however, it only makes sense to use when the list isn't empty.

> DeleteAll process on an occupied list (Before pressing DeleteAll):
>
> ![Project Image](src/main/resources/readmeImages/Before-DeleteAll-Pressed.png)
>
> After pressing DeleteAll:
>
> ![Project Image](src/main/resources/readmeImages/After-DeleteAll-Pressed.png)

[Back To The Top](#table-of-contents)

---

## Edit

In order to use the Edit button 2 pre-Conditions must be met.
> 1) An entry must be selected before clicking Edit. Therefore, Edit may not be used on an empty list.
> 2) The text field and DatePicker must contain a valid entry for the edit to process.

> Edit process on an occupied list (Before pressing Edit):
>
> ![Project Image](src/main/resources/readmeImages/Before-Edit-Pressed.png)
>
> After pressing Edit:
>
> Edit button name changes from 'Edit' to 'Save'.
> The entry to be edited will then be displayed in their respective Description Text Fields.
>
> ![Project Image](src/main/resources/readmeImages/During-Edit.png)
>
> The user must then click Save to apply their new edit.
>
> ![Project Image](src/main/resources/readmeImages/Edit-toBechanged.png)
>
> After edit process goes through correctly:
>
> ![Project Image](src/main/resources/readmeImages/Edit-toBechanged-FINAL.png)


[Back To The Top](#table-of-contents)

---

## Mark Complete

Changes the status of a list entry from incomplete to complete.

In order to use Mark Complete 1 pre-Condition must be met.
> 1) There must be at least one entry in the list.

> Mark Complete process on an occupied list (Before pressing Mark C):
>
> ![Project Image](src/main/resources/readmeImages/Before-MarkC-Pressed.png)
>
> After pressing Mark C:
>
> The Entry will now have a status of complete.
>
> ![Project Image](src/main/resources/readmeImages/After-MarkC-Pressed.png)


[Back To The Top](#table-of-contents)

---

## Mark Incomplete

Changes the status of a list entry from complete to incomplete.

In order to use Mark Incomplete 1 pre-Condition must be met.
> 1) There must be at least one entry in the list.

> Mark Incomplete process on an occupied list (Before pressing Mark I):
>
> ![Project Image](src/main/resources/readmeImages/Before-MarkI-Pressed.png)
>
> After pressing Mark I:
>
> The Entry will now have a status of incomplete.
>
> ![Project Image](src/main/resources/readmeImages/After-MarkI-Pressed.png)


[Back To The Top](#table-of-contents)

---

## Display Complete

In order to use Display Complete 1 pre-Condition must be met.
> 1) There must be an existing entry in the list with a status of Complete.

> Search process on an occupied list (Before pressing Display Complete from the Options menu):
>
> ![Project Image](src/main/resources/readmeImages/Before-DisplayComplete-Pressed.png)
>
> After pressing Display Complete:
>
> The list will now be filtered to only display completed items.
>
> ![Project Image](src/main/resources/readmeImages/After-DisplayComplete-Pressed.png)


[Back To The Top](#table-of-contents)

---

## Display Incomplete

In order to use Display Incomplete 1 pre-Condition must be met.
> 1) There must be an existing entry in the list with a status of Incomplete.

> Search process on an occupied List (Before pressing Display Incomplete from the Options menu):
>
> ![Project Image](src/main/resources/readmeImages/Before-DisplayIncomplete-Pressed.png)
>
> After pressing Display Incomplete:
>
> The list will now be filtered to only display incomplete items.
>
> ![Project Image](src/main/resources/readmeImages/After-DisplayIncomplete-Pressed.png)


[Back To The Top](#table-of-contents)

---

## Display All

In order to use Display Incomplete 1 pre-Condition must be met.
> 1) There must be an existing entry in the list.

> Display All process on an occupied list (Before pressing Display All from the Options menu):
>
> ![Project Image](src/main/resources/readmeImages/Before-DisplayAll-Pressed.png)
>
> After pressing Display All:
>
> The list will now return to its original state.
>
> ![Project Image](src/main/resources/readmeImages/After-DisplayAll-Pressed.png)


[Back To The Top](#table-of-contents)

---

## Save

Saves the Contents of the list to a .txt file

> Save process on an occupied list (Before pressing Save):
>
> ![Project Image](src/main/resources/readmeImages/Before-Save-Pressed.png)
>
> After pressing Save:
>
> The file chooser window appears for the user to enter save information.
>
> ![Project Image](src/main/resources/readmeImages/After-Save-Pressed.png)

Note: An unsaved changes warning message is shown if the user tries to terminate the program after adding, deleting, editing, or sorting.
>  The user has the choice to CANCEL and save or click OK and terminate.
>
> ![Project Image](src/main/resources/readmeImages/Unsaved-changes-warning.png)

[Back To The Top](#table-of-contents)

---

## Load

Loads the contents of the file to the list. Handles .txt file type

> Load process on an occupied list (Before pressing Load):
>
> ![Project Image](src/main/resources/readmeImages/Before-Load-Pressed.png)
>
> After pressing Load:
>
> The file chooser window appears for the user to choose a file to load.
>
> ![Project Image](src/main/resources/readmeImages/After-Load-Pressed.png)


[Back To The Top](#table-of-contents)

---

## Author Info

- Tyler King - UCF Comp Science Student
- Email - tylerking885@gmail.com


[Back To The Top](#table-of-contents)
	