Song is the main class for this model, it extends a TreeMap of NoteSets.
NoteSets are extensions of TreeMaps for Notes. This includes only one type of note.

Pitch, Octave, and Accidentals represent the pitch, octave, and accidental of a note,
Pitch and Accidentals is an enum, while Octave is a class.
These three form a Tone class (basically represents a distinct sound).

A note includes a Tone and a start and duration.

I used TreeMaps because I wanted to keep the Notes sorted for easy access based on time. I think
this would allow for constant time min and amx access and log time search. I just think that
in a music player, the time would be important in the future, so it's better to have an efficient
searching algorithm or structure.

Moreover, I decided to implement Notes with start and duration times so that I can keep the data
sparse and save space instead of representing a rest class.

For measures, with respect to the models, I don't think they currently make a difference, so I
ignored it. I was considering making it contain notes but it didn't make sense since notes
can be longer than a measure.