Song is the main class for this model, it extends a TreeMap of NoteSets.
NoteSets are extensions of TreeMaps for Notes. This includes only one type of note.

The Tone class is the implementation of the ITone interface, which represents a distinct sound,
and is comprised of a pitch and a octave.
Pitch is an enum, and octave is just an integer for the octave of a tone.

A note includes a Tone and a start and duration.

I used TreeMaps because I wanted to keep the Notes sorted for easy access based on time. I think
this would allow for constant time min and amx access and log time search. I just think that
in a music player, the time would be important in the future, so it's better to have an efficient
searching algorithm or structure.

Moreover, I decided to implement Notes with start and duration times so that I can keep the data
sparse and save space instead of representing a rest class.

Measures are not used in this implementation since a note can be longer than a measure, but
measures are depicted visually in the GUI view.

There are different styles of view, including a GUI view, a MIDI view, and the textual view.

The textual view is the music piece produced using ascii characters where beat numbers run down
the left side and the rendering is made with text that can be shown in the console.

The GUI View is a view that is more likely to be what the client is used to, with a more graphical
interface that contains green notes with a black note head and measures that run along the x-axis,
similarly to a sheet of music.

The MIDI view is an audible view that allows the client to hear the music being played. MIDI will
play back the compositions in the txt files and parse them to play them as notes.

These three views can be toggled using the IMusicViewFactory that allows the player to select
between different views.

In this week's assignment, one of the design changes that we made were tree map —> hashmap because
to get our list of notes, it would take O(log n) time vs. switching to hash map where it would
take O(1) to access the list of notes. Furthermore, there is now a composite view that can play
the midi song and render the visual view at the same time.

Improved functionality inlcudes the ability to use the keyboard and mouse to perform different
tasks. The mappings are listed below:

Right Arrow - Scrolls the window to the right
Left Arrow - Scrolls the window to the left
Up Arrow - Scrolls the window up
Down Arrow - Scrolls the window down
Space - Play/Pause the song

Left Click - Obtain the Pitch, Octave, and Beat of the note, as well as its numerical value that
can be entered into the text field to add/delete
Right Click - Obtain the Pitch, Octave, and Beat of the note as well as delete the note that was
clicked.

