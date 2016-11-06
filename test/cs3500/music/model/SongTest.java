package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test suite for Song.
 */
public class SongTest {
  Song test1 = new Song();

  @Test
  public void testGoodAdd() {
    test1.addNote(new Note(Pitch.B, 10, 3, 0));
    test1.addNote(new Note(Pitch.B, 2, 3, 4));
    // diff starting point, same note
    // testing overlap
    test1.addNote(new Note(Pitch.CS, 10, 3, 1));
    // testing using sharps
    test1.addNote(new Note(Pitch.C, 2, 3, 5));
    // testing if sharp works while natural works same note
    test1.addNote(new Note(Pitch.D, 3, 5, 1));
    // testing different octave
    assertEquals("     C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3   " +
            "C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5   D5\n" +
            " 0                                                          X         " +
            "                                                                    \n" +
            " 1        X                                                 |         " +
            "                                                                 X  \n" +
            " 2        |                                                 |         " +
            "                                                                 |  \n" +
            " 3        |                                                 |         " +
            "                                                                 |  \n" +
            " 4        |                                                 X         " +
            "                                                                    \n" +
            " 5   X    |                                                 |         " +
            "                                                                    \n" +
            " 6   |    |                                                 |         " +
            "                                                                    \n" +
            " 7        |                                                 |         " +
            "                                                                    \n" +
            " 8        |                                                 |         " +
            "                                                                    \n" +
            " 9        |                                                 |         " +
            "                                                                    \n" +
            "10        |                                                           " +
            "                                                                    \n",
            test1.toString());
  }

  @Test
  public void testNegative() {
    test1 = new Song();
    test1.addNote(new Note(Pitch.A, 5, -1, 0));
    test1.addNote(new Note(Pitch.B, 2, 2, 4));
    // testing overlap
    test1.addNote(new Note(Pitch.CS, 7, 2, 1));
    // testing using sharps
    test1.addNote(new Note(Pitch.D, 2, 3, 5));
    // testing if sharp works while natural works same note
    test1.addNote(new Note(Pitch.E, 3, 0, 3));
    test1.addNote(new Note(Pitch.F, 6, -1, 4));
    // same starting point
    // negative octave
    test1.addNote(new Note(Pitch.G, 1, 1, 1));
    test1.addNote(new Note(Pitch.E, 3, 2, 3));
    // same note different octave
    assertEquals("    F-1 F#-1  G-1 G#-1  A-1 A#-1  B-1   C0  C#0   D0  D#0   E0   " +
            "F0  F#0   G0  G#0   A0  A#0   B0   C1  C#1   D1  D#1   E1   F1  F#1   " +
            "G1  G#1   A1  A#1   B1   C2  C#2   D2  D#2   E2   F2  F#2   G2  G#2   " +
            "A2  A#2   B2   C3  C#3   D3\n" +
            " 0                       X                                            " +
            "                                                                      " +
            "                                                                      " +
            "                       \n" +
            " 1                       |                                            " +
            "                                                                 X    " +
            "                         X                                            " +
            "                       \n" +
            " 2                       |                                            " +
            "                                                                      " +
            "                         |                                            " +
            "                       \n" +
            " 3                       |                                  X         " +
            "                                                                      " +
            "                         |              X                             " +
            "                       \n" +

            " 4   X                   |                                  |         " +
            "                                                                      " +
            "                         |              |                             " +
            "     X                 \n" +
            " 5   |                                                      |         " +
            "                                                                      " +
            "                         |              |                             " +
            "     |              X  \n" +
            " 6   |                                                                " +
            "                                                                      " +
            "                         |                                            " +
            "                    |  \n" +
            " 7   |                                                                " +
            "                                                                      " +
            "                         |                                            " +
            "                       \n" +
            " 8   |                                                                " +
            "                                                                      " +
            "                                                                      " +
            "                       \n" +
            " 9   |                                                               " +
            "                                                                     " +
            "                                                                     " +
            "                          \n", test1.toString());
  }

  @Test
  public void testDoubleDigits() {
    test1 = new Song();
    test1.addNote(new Note(Pitch.A, 5, 3, 0));
    test1.addNote(new Note(Pitch.B, 2, 2, 4));
    // testing overlap
    test1.addNote(new Note(Pitch.CS, 7, 2, 1));
    // testing using sharps
    test1.addNote(new Note(Pitch.D, 2, 3, 5));
    // testing if sharp works while natural works same note
    test1.addNote(new Note(Pitch.E, 3, 0, 3));
    test1.addNote(new Note(Pitch.F, 6, -1, 4));
    // same starting point
    // negative octave
    test1.addNote(new Note(Pitch.G, 1, 1, 1));
    test1.addNote(new Note(Pitch.E, 3, 2, 3));
    test1.addNote(new Note(Pitch.AS, 3, 2, 3));
    test1.addNote(new Note(Pitch.B, 3, 2, 1));
    test1.addNote(new Note(Pitch.DS, 10, 2, 6));
    // same note different octave
    assertEquals("    F-1 F#-1  G-1 G#-1  A-1 A#-1  B-1   C0  C#0   D0  D#0   E0   F0  " +
            "F#0   G0  G#0   A0  A#0   B0   C1  C#1   D1  D#1   E1   F1  F#1   G1  G#1 " +
            "  A1  A#1   B1   C2  C#2   D2  D#2   E2   F2  F#2   G2  G#2   A2  A#2   B2" +
            "   C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3\n" +
            " 0                                                                        " +
            "                                                                          " +
            "                                                                          " +
            "                                           X  \n" +
            " 1                                                                        " +
            "                                                             X            " +
            "                 X                                                 X      " +
            "                                           |  \n" +
            " 2                                                                        " +
            "                                                                          " +
            "                 |                                                 |      " +
            "                                           |  \n" +
            " 3                                                          X             " +
            "                                                                          " +
            "                 |              X                             X    |      " +
            "                                           |  \n" +
            " 4   X                                                      |             " +
            "                                                                          " +
            "                 |              |                             |    X      " +
            "                                           |  \n" +
            " 5   |                                                      |             " +
            "                                                                          " +
            "                 |              |                             |    |      " +
            "        X                                     \n" +
            " 6   |                                                                    " +
            "                                                                          " +
            "                 |         X                                              " +
            "        |                                     \n" +
            " 7   |                                                                    " +
            "                                                                          " +
            "                 |         |                                              " +
            "                                              \n" +
            " 8   |                                                                    " +
            "                                                                          " +
            "                           |                                              " +
            "                                              \n" +
            " 9   |                                                                    " +
            "                                                                          " +
            "                           |                                              " +
            "                                              \n" +
            "10                                                                        " +
            "                                                                          " +
            "                           |                                              " +
            "                                              \n" +
            "11                                                                        " +
            "                                                                          " +
            "                           |                                              " +
            "                                              \n" +
            "12                                                                        " +
            "                                                                          " +
            "                           |                                              " +
            "                                              \n" +
            "13                                                                        " +
            "                                                                          " +
            "                           |                                              " +
            "                                              \n" +
            "14                                                                        " +
            "                                                                          " +
            "                           |                                              " +
            "                                              \n" +
            "15                                                                        " +
            "                                                                          " +
            "                           |                                              " +
            "                                              \n", test1.toString());
  }

  @Test
  public void testEmpty() {
    assertEquals("", test1.toString());
  }
}


