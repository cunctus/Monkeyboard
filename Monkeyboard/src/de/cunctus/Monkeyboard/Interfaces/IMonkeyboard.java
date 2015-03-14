/**
 * @project Monkeyboard
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.Monkeyboard.Interfaces
 * @version 
 * @since   05.03.2015
 *
 */
package de.cunctus.Monkeyboard.Interfaces;

import de.cunctus.Monkeyboard.Enums.ApplicationType;
import de.cunctus.Monkeyboard.Enums.PlayMode;
import de.cunctus.Monkeyboard.Enums.PlayStatus;
import de.cunctus.Monkeyboard.Enums.ProgramType;
import de.cunctus.Monkeyboard.Enums.ServCompType;
import de.cunctus.Monkeyboard.Enums.Stereo;
import de.cunctus.Monkeyboard.Enums.StereoMode;
import de.cunctus.Monkeyboard.Struct.GetBBEEQResult;
import de.cunctus.Monkeyboard.Struct.GetProgramInfoResult;
import de.cunctus.Monkeyboard.Struct.GetRTCResult;
import de.cunctus.Monkeyboard.Struct.GetSignalStrengthResult;

public interface IMonkeyboard {

	/**
	 * Clear the DAB programs stored in the module's database.
	 * 
	 * @return TRUE if successful, FALSE if failed
	 */
	public boolean ClearDatabase();

	/**
	 * Close the COM port of the radio.
	 * 
	 * @return TRUE if successful, FALSE if failed
	 */
	public boolean CloseRadioPort();

	/**
	 * Auto search DAB channels. Current stored DAB channels will be cleared.
	 * <p>
	 * If module is operating outside of China Band, calling DABAutoSearch(0,40)
	 * will speed up the searching process. After calling this function, the
	 * module will be defaulted to MONO and also the volume will be muted,
	 * calling SetStereoMode(1) and SetVolume(volume) are required to set the
	 * module back to STEREO mode and the right volume respectively.
	 * DABAutoSearchNoClear() scan and add channels to the end of the list
	 * without clearing the first list. Maximum channels stored is 100.
	 * </p>
	 * 
	 * @param startIndex
	 *             Starting index to search from. See remark below.
	 * @param endIndex
	 *             Ending index to search to. See remark below.
	 * @return TRUE if successful, FALSE if failed
	 */
	public boolean DABAutoSearch(int startIndex, int endIndex);

	/**
	 * Same like DABAutoSearch, but current stored DAB channels won't be
	 * cleared.
	 * 
	 * @param startIndex
	 *             Starting index to search from. See remark below.
	 * @param endIndex
	 *             Ending index to search to. See remark below.
	 * @return TRUE if successful, FALSE if failed
	 */
	public boolean DABAutoSearchNoClear(int startIndex, int endIndex);

	/**
	 * Get the type of MOT application of the specified DAB channel.
	 * 
	 * @param dabIndex the index value of the DAB channel
	 * @return the ApplicationType
	 * @see ApplicationType
	 */
	public ApplicationType GetApplicationType(int dabIndex);

	/**
	 * Get parameters of BBE HD Sound or Mode of EQ.
	 * 
	 * @return {@link GetBBEEQResult}
	 */
	public GetBBEEQResult GetBBEEQ();

	/**
	 * Get the signal quality of the current DAB channel.
	 * 
	 * @deprecated
	 * @return the signal quality in 0 - 100
	 */
	@Deprecated
	public int GetDABSignalQuality();

	/**
	 * Get the current DAB data rate.
	 * 
	 * @return the current DAB data rate in kbps.
	 */
	public int GetDataRate();

	/**
	 * Get the ensemble name of the current program.
	 * <p>
	 * Please read remark for function GetProgramText().
	 * </p>
	 *
	 * @param dabIndex
	 *            The DAB program index of 0 to GetTotalProgram()-1 to get the
	 *            ensemble from.
	 * @param shortname
	 *            Setting this parameter to TRUE for abbreviated name or FALSE
	 *            for long name. Only valid when mode is DAB
	 * @return the EnsembleName
	 */
	public String GetEnsembleName(long dabIndex, boolean shortname);

	/**
	 * Get the currenct DAB frequency index in while DAB is auto searching.
	 * 
	 * @return the DAB frequency index of the current auto search.
	 */
	public int GetFrequency();

	/**
	 * Get the headroom volume.
	 * 
	 * @return 12 - 0 , represents -12dB to 0dB
	 */
	public int GetHeadroom();

	/**
	 * Get the filename of the SlideShow image.
	 * <p>
	 * When MotQuery() returns true and GetApplicationType() returns SlideShow,
	 * the function will return the filename of the image generated in the
	 * program folder. Depending on the usage of the images collected, for
	 * example browsing old SlideShow or just to view current one, developer
	 * could decide to keep or delete the images after showing on screen. This
	 * function only applicable to KeyStone module with SlideShow.
	 * </p>
	 * 
	 */
	public String GetImage();

	/**
	 * Get the index of current playing DAB stream or the current playing
	 * frequency.
	 * <p>
	 * GetPlayMode() is required to be called before or after this function to
	 * determine the radio mode in order to differentiate the returned value.
	 * </p>
	 * 
	 * @return If the radio is in DAB mode, return the current playing DAB index
	 *         within 0 to GetTotalProgram()-1. If the radio is in FM mode, the
	 *         current playing FM frequency in kHz is returned, eg 94500 is
	 *         94.5Mhz.
	 */
	public long GetPlayIndex();

	/**
	 * Determine if the current mode is DAB or FM.
	 * 
	 * @return 0 when current mode is DAB or 1 when current mode is FM. Any
	 *         other value is invalid
	 * @see PlayMode
	 */
	public PlayMode GetPlayMode();

	/**
	 * Determine if the current radio status is playing, searching, tuning, stop
	 * sorting or reconfiguring.
	 * 
	 * @return 0-5 for the current status, other value invalid
	 * @see PlayStatus
	 */
	public PlayStatus GetPlayStatus();

	/**
	 * Get the preset DAB index or preset FM frequency. The module is able to
	 * store 10 DAB and 10 FM preset.
	 * 
	 * @param mode
	 *             0 to get DAB preset or 1 to get FM preset
	 * @param presetIndex
	 *             Preset location from 0 to 9
	 * @return If mode is DAB (mode 0), this value contains the DAB program
	 *         index. If mode is FM (mode 1), this value contains the FM
	 *         frequency in kHz , eg 94500 for 94.5Mhz.
	 */
	public long GetPreset(int mode, int presetIndex);

	/**
	 * Get the Service Component ID, Service ID and Ensemble ID for particular
	 * DAB station.
	 * <p>
	 * This function allows developers to create a RadioDNS compatible hostname
	 * for example:<br>
	 * <br>
	 * 
	 * 0.c3a6.c186.ce1.dab IN CNAME thisisglobal.com.<br>
	 * <br>
	 * 
	 * In RadioDNS's hostname example, developer will need to supply ECC to form
	 * the hostname. Our board does not send out ECC, developers need to
	 * hardcode ECC based on their country of origin. John Jore from Australia
	 * shared this link http://web.itu.edu.tr/~pazarci/rtv/rbds1998.pdf, Annex D
	 * and N, while coding his Centrafuse plugin to support the ECC.
	 * </p>
	 * 
	 * @param dabIndex
	 *             Index of the DAB channel from 0 to GetTotalProgram()-1 .
	 * @return {@link GetProgramInfoResult}
	 */
	public GetProgramInfoResult GetProgramInfo(long dabIndex);

	/**
	 * Get the name of the current program.
	 * <p>
	 * Please read remark for function GetProgramText().
	 * </p>
	 * 
	 * @param mode
	 *             {@link PlayMode} DAB or FM
	 * @param dabIndex
	 *             Index of the DAB channel from 0 to GetTotalProgram()-1 if
	 *            mode is DAB or this parameter is ignored when mode is FM.
	 * @param shortname
	 *             Setting this parameter to TRUE for abbreviated name or FALSE for
	 *            long name. Only valid when mode is DAB.
	 * @return the name of the current program
	 */
	public String GetProgramName(PlayMode mode, long dabIndex,
			boolean shortname);

	/**
	 * Get the RDS text of the current stream.
	 * <p>
	 * Linux programmer porting this function need to be aware of the different
	 * sizes of wchar_t when porting this function, proper routines are required
	 * to convert the text into a printable string. For example, capital "Z"<br>
	 * Windows wchar_t is 0x5A, 0x00<br>
	 * Linux wchar_t is 0x5A, 0x00, 0x00, 0x00<br>
	 * Printing strings of wchar_t in Linux requires wprintf(L"%ls", buffer);
	 * </p>
	 * 
	 * @return RDS Text of the current stream
	 */
	public String GetProgramText();

	/**
	 * @param mode
	 *            {@link PlayMode} DAB or FM
	 * @param dabIndex
	 *             the index of the DAB channel required to get the program
	 *            type. Function will ignore this value in if mode is 1 (FM).
	 * @return {@link ProgramType}
	 * @throws FunctionFailedException
	 */
	public ProgramType GetProgramType(PlayMode mode, long dabIndex);

	/**
	 * Get realtime clock from the module.
	 * <p>
	 * Before calling GetRTC(), call SyncRTC(true) first, then call GetRTC() in
	 * a loop to wait for it to return a true. The parameters returned are valid
	 * date and time once it returns true.
	 * </p>
	 * 
	 * @return {@link GetRTCResult}
	 */
	public GetRTCResult GetRTC();

	/**
	 * Get the current audio sampling rate.
	 * 
	 * @return the current audio sampling rate in kHz. 24, 32 or 48. Return 0 when unknown.
	 */
	public int GetSamplingRate();

	/**
	 * Get the type of MOT application of the specified DAB channel.
	 * 
	 * @param dabIndex
	 *             the index value of the DAB channel
	 * @return the ServCompType
	 * @see ServCompType
	 */
	public ServCompType GetServCompType(long dabIndex);

	/**
	 * Get the signal strengh of the current playing stream.
	 * 
	 * @param biterror
	 *            0 if FM mode and bit error rate if DAB mode. ** ignore
	 *            this out value until further notice.
	 * @return {@link GetSignalStrengthResult}
	 */
	public GetSignalStrengthResult GetSignalStrength(int biterror);

	/**
	 * Get the stereo reception status of the current playing stream.
	 * 
	 * @return {@link Stereo}
	 * @see Stereo
	 */
	public Stereo GetStereo();

	/**
	 * Get the current stereo mode in the radio configuration.
	 * 
	 * @return {@link StereoMode}
	 * @see StereoMode
	 */
	public StereoMode GetStereoMode();

	/**
	 * Get the total number of DAB programs stored in the module.
	 * 
	 * @return Total DAB programs stored in the module.
	 */
	public int GetTotalProgram();

	/**
	 * Get the current volume.
	 * 
	 * @return Current volume in 0 to 16
	 */
	public int GetVolume();

	/**
	 * Hard reset the radio module by pulling the RESET pin LOW.
	 * 
	 * @return true if successful, false if failed.
	 */
	public boolean HardResetRadio();

	/**
	 * Check if the module is ready to receive command.
	 * 
	 * @return true if successful, false if failed.
	 */
	public boolean IsSysReady();

	/**
	 * Query radio module for MOT data.
	 * <p>
	 * In order to get the DLL to collect the MOT segments, this function needs
	 * to be called every 40-50ms, calling this function longer that 40-50ms
	 * will caused segment lost and failure in building MOT data. For example
	 * SlideShow images might be lost or never displayed. This function only
	 * applicable to KeyStone module with SlideShow.
	 * </p>
	 * 
	 * @return true if successful built complete data from segments.
	 */
	public boolean MotQuery();

	/**
	 * Reset the MOT segment buffer.
	 * <p>
	 * After each changing of DAB channel, this function needs to be called in
	 * order to reset the MOT segment buffer. Failure to call this function
	 * after channel changes will caused MOT segment data corrupted and
	 * SlideShow or EPG data will not be properly built. This function only
	 * applicable to KeyStone module with SlideShow.
	 * </p>
	 * 
	 * @param enMode
	 *             0 to reset SlideShow segments, 1 to reset EPG segments.
	 */
	public void MotReset(int enMode);

	/**
	 * Forward to the next available stream in the current mode. When radio is
	 * in DAB mode, the dabindex will be incremented and then played. When the
	 * radio is in FM mode, search by increasing the FM frequency until a
	 * channel is found.
	 * 
	 * @return true if successful, false if failed.
	 */
	public boolean NextStream();

	/**
	 * Open the COM port of the radio and set mute behavior.
	 * 
	 * @param port
	 *             COM port of the radio. Example "\\.\COM1", refer to
	 *            http://support.microsoft.com/kb/115831 for details.
	 * @param usehardmute
	 *             true to enable or false disable hard mute. Hard mute will
	 *            turn on the MOSFET on the board to shunt transitional noise
	 *            like psss or pop sound.
	 * @return true if successful, false if failed.
	 */
	public boolean OpenRadioPort(String port, boolean usehardmute);

	/**
	 * Play radio stream in FM or DAB.
	 * 
	 * @param mode
	 *             The mode of the radio, 0 is DAB and 1 is FM.
	 * @param channel
	 *             When mode is DAB (mode 0), this value is the index of the
	 *            DAB channels from 0 to GetTotalProgram()-1. When mode is FM
	 *            (mode 1), this value is the FM frequency in kHz, eg 105000 is
	 *            105Mhz, eg 94500 is 94.5Mhz.
	 * @return true if successful, false if failed.
	 */
	public boolean PlayStream(PlayMode mode, long channel);

	/**
	 * Backward to the previous available stream in the current mode. When radio
	 * is in DAB mode, the dabindex will be decremented and then played. When
	 * the radio is in FM mode, search by decresing the FM frequency until a
	 * channel is found.
	 * 
	 * @return true if successful, false if failed.
	 */
	public boolean PrevStream();

	/**
	 * Set BBE HD Sound or Preset EQ.
	 * <p>
	 * BBE HD Sound is a software based EQ and for this module, when the values
	 * are over a certain value, the sound will be distorted and sometimes will
	 * not produce any music at all. Please refer to the board's user manual on
	 * how to tune these parameters.
	 * </p>
	 * 
	 * @param BBEOn
	 *             0 to turn off BBE or EQ, 1 turn on BBE, 2 turn on EQ
	 * @param EQMode
	 *             0 Bass Boost, 1 Jazz, 2 Live, 3 Vocal, 4 Acoustic
	 * @param BBELo
	 *             0-24 BBE Low response represent 0-12dB
	 * @param BBEHi
	 *             0-24 BBE High response represent 0-12dB
	 * @param BBECFreq
	 *             BBE Center frequency, when 0 is 595Hz, 1 is 1KHz. Note:
	 *            From experience, this value does not affect much.
	 * @param BBEMachFreq
	 *             BBE Mach Frequency in hertz, 60, 90, 120, 150
	 * @param BBEMachGain
	 *             BBE Mach Gain in dB, 0, 4, 8, 12
	 * @param BBEMachQ
	 *             BBE Mach Q (Bandwidth) of the Mach3Bass filter, 1 or 3
	 * @param BBESurr
	 *             BBE Surround in dB, 0 - 10
	 * @param BBEMp
	 *             Minimised Polynomial Non-Linear Saturation process in dB,
	 *            0 -10. Note: From experience, when set a little higher than 1,
	 *            the sound output will be distorted.
	 * @param BBEHpF
	 *             Activates a High Pass Filter in Hz, 20 - 250 (in steps of
	 *            10).
	 * @param BBEHiMode
	 *             Generates a larger phase shift to the higher frequencies
	 *            in dB, 0 - 12.
	 * @return true if successful, false if failed.
	 */
	public boolean SetBBEEQ(int BBEOn, int EQMode, int BBELo, int BBEHi,
			int BBECFreq, int BBEMachFreq, int BBEMachGain, int BBEMachQ,
			int BBESurr, int BBEMp, int BBEHpF, int BBEHiMode);

	/**
	 * Set audio headroom.
	 * 
	 * @param headroom
	 *             Headroom volume curve, 12 - 0 represents -12dB to 0dB
	 * @return true if successful, false if failed.
	 */
	public boolean SetHeadroom(int headroom);

	/**
	 * Store program into preset location.
	 * 
	 * @param mode
	 *             0 to store DAB program, 1 to store FM program.
	 * @param presetindex
	 *             Preset location to be stored from 0 to 9.
	 * @param channel
	 *             If mode is DAB (mode 0), this parameter is the DAB
	 *            program index. If mode is FM (mode 1), this parameter is the
	 *            FM frequency to be stored in kHz, eg 94500 for 94.5Mhz.
	 * @return true if successful, false if failed.
	 */
	public boolean SetPreset(PlayMode mode, int presetindex, long channel);

	/**
	 * Set radio to forced mono or auto detect stereo mode.
	 * 
	 * @param mode
	 *            {@link StereoMode} If mode is 0, the radio will be forced into mono mode. If
	 *            mode is 1, the radio will auto detect stereo mode, switching
	 *            to mono when reception is poor.
	 * @return true if successful, false if failed.
	 */
	public boolean SetStereoMode(StereoMode mode);

	/**
	 * Set the volume of the radio.
	 * 
	 * @param volume
	 *             A char value of the volume from 0 to 16
	 * @return true if successful, false if failed.
	 */
	public boolean SetVolume(int volume);

	/**
	 * Stop currently played FM or DAB stream.
	 * 
	 * @return true if successful, false if failed.
	 */
	public boolean StopStream();

	/**
	 * Enable clock sync from the broadcast.
	 * <p>
	 * This function should one be called once as it will reset SECOND to 0 in
	 * every 5 seconds causing inaccuracy of SECOND in the clock. After calling
	 * this function with true parameter, call GetRTC() in a loop and once
	 * GetRTC() returns a true, call SyncRTC(false) to disable the sync. This
	 * behaviour is caused by the firmware.
	 * </p>
	 * 
	 * @param sync
	 *             true to enable sync of clock, false to disable sync of
	 *            clock
	 * @return true if successful, false if failed.
	 */
	public boolean SyncRTC(boolean sync);

	/**
	 * Minus one volume step from the current volume.
	 * 
	 * @return Volume value of 0 to 15 after executing successfully
	 */
	public int VolumeMinus();

	/**
	 * Mute or Un-mute the volume.
	 */
	public void VolumeMute();

	/**
	 * Add one volume step to the current volume.
	 * 
	 * @return Volume value of 0 to 16 after executing successfully
	 */
	public int VolumePlus();

}
