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