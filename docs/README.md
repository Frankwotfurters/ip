# Hachiware User Guide

> "Oiiaioooooiai.” –Hachiware ([source](https://www.youtube.com/watch?v=Ux2WsPNglQI))

Hachiware frees your mind of having to remember things you need to do. It's,

- **typing**-based
- ~~quite~~ easy to learn
- super __robust__

All you need to do is:
1. download it from [here](https://github.com/frankwotfurters/ip/).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 😉

And it is **FREE**!

Features:

- [X] Managing tasks
- [X] Managing deadlines (coming soon)
- [ ] Reminders (coming soon)
- [x] If you are a Java programmer, you can use it to practice Java too. Just do:

```
Hachiware hachiware = new Hachiware();
hachiware.run();
```

## Adding new todo tasks
Adds a new Task

Example: `todo eat grass`

// Adds a new Task with description "eat grass"

```
Added: [T][ ] eat grass
```

## Adding deadlines

Adds a new Task with a deadline (date time format: DD/MM/YYYY HHMM)

Example: `deadline sleep /by 18/02/2026 2200`

// Adds a new Task with description "sleep" and deadline 18/02/2026 2200

```
Added: [D][ ] sleep (by: 18 Feb 2026 10:00 pm)
```

## Adding events

Adds a new Task with a time range (date time format: DD/MM/YYYY HHMM)

Example: `event concert /from 18/02/2026 1800 /to 18/02/2026 2200`

// Adds a new Task with description "concert" from 18/02/2026 1800 to 18/02/2026 2200

```
Added: [E][ ] concert (from: 18 Feb 2026 06:00 pm to: 18 Feb 2026 10:00 pm)
```

## Listing all tasks

Displays a list of all tasks with their status and id

Example: `list`

```
1.[T][X] eat grass
2.[D][ ] sleep (by: 18 Feb 2026 10:00 pm)
3.[E][ ] class (from: 19 Feb 2026 10:00 am to: 19 Feb 2026 02:00 pm)
4.[D][ ] sleep (by: 18 Feb 2026 10:00 pm)
5.[T][ ] eat grass
6.[E][ ] concert (from: 18 Feb 2026 06:00 pm to: 18 Feb 2026 10:00 pm)
```

## Deleting a task

Deletes a task

Example: `delete 2`

// Deletes task at index 2

```
Okay! Deleting this task:
[D][ ] sleep (by: 18 Feb 2026 10:00 pm)
```

## Mark task as done

Example: `mark 2`

// Marks the task at index 2 as Done

```
Yay! I've ticked off this task:
[E][X] class (from: 19 Feb 2026 10:00 am to: 19 Feb 2026 02:00 pm)
```

## Mark task as not done

Example: `unmark 2`

// Marks the task at index 2 as not done

```
Oops! I've unmarked this task:
[E][ ] class (from: 19 Feb 2026 10:00 am to: 19 Feb 2026 02:00 pm)
```

## Find tasks

Searches the list of Tasks to find any matching the provided string

Example: `find eat`

// Lists tasks that contain the string "eat"

```
1.[T][X] eat grass
4.[T][ ] eat grass
```

## Undo last action

Undoes the last actionable action.
Commands that don't change the state of any tasks are not counted. E.g. list

Example: `undo`

```
Undid the previous action!
```
