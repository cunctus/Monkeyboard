/**
 * @project Monkeyboard
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.Monkeyboard
 * @version 
 * @since   14.03.2015
 *
 */
package de.cunctus.Monkeyboard;

import de.cunctus.Monkeyboard.Enums.PlayMode;
import de.cunctus.Monkeyboard.Enums.StereoMode;

public class RunExample {

	/*
	 * Just a simple example.
	 */
	public static void main(String[] args) {

		try {
			Monkeyboard board = Monkeyboard.getInstance("/dev/ttyACM0",
					"keystonecomm");

			board.PlayStream(PlayMode.FM, 100600); // run FM Mode = 100.6 MHz

			board.SetVolume(10); // set Volume to 10 of 16

			board.SetStereoMode(StereoMode.AUTO_STEREO); // play in Stereo if
															// possible

			while (true) {
				System.out.println(board.GetPlayStatus().getStatus());
				System.out.println(board.GetProgramText());
			}

			// it's a bit dirty cause normally the port should be closed, but
			// it's for testing only

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
