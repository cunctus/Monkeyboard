/**
 * @project Monkeyboard
 * @author  Christopher Rogoza
 * @email   christopher@cunctus.de
 * @package de.cunctus.Monkeyboard
 * @version 
 * @since   05.03.2015
 *
 */
package de.cunctus.Monkeyboard;

import java.util.HashMap;
import java.util.Properties;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.WString;
import com.sun.jna.ptr.IntByReference;

import de.cunctus.Monkeyboard.Enums.ApplicationType;
import de.cunctus.Monkeyboard.Enums.PlayMode;
import de.cunctus.Monkeyboard.Enums.PlayStatus;
import de.cunctus.Monkeyboard.Enums.ProgramType;
import de.cunctus.Monkeyboard.Enums.ServCompType;
import de.cunctus.Monkeyboard.Enums.Stereo;
import de.cunctus.Monkeyboard.Enums.StereoMode;
import de.cunctus.Monkeyboard.Exceptions.DeviceNotFoundException;
import de.cunctus.Monkeyboard.Exceptions.InvalidPropertiesException;
import de.cunctus.Monkeyboard.Interfaces.IMonkeyboard;
import de.cunctus.Monkeyboard.NativeInterfaces.INativeMonkeyboard;
import de.cunctus.Monkeyboard.Struct.GetBBEEQResult;
import de.cunctus.Monkeyboard.Struct.GetProgramInfoResult;
import de.cunctus.Monkeyboard.Struct.GetRTCResult;
import de.cunctus.Monkeyboard.Struct.GetSignalStrengthResult;

public class Monkeyboard implements IMonkeyboard {

	protected static HashMap<String, Monkeyboard> INSTANCES = new HashMap<String, Monkeyboard>();
	protected String device = null;
	protected String library = null;
	protected INativeMonkeyboard monkeyBoard;
	protected String programText = "";
	protected Properties properties = null;

	private Monkeyboard(String device, String library)
			throws DeviceNotFoundException, InvalidPropertiesException {

		monkeyBoard = (INativeMonkeyboard) Native.loadLibrary(library,
				INativeMonkeyboard.class);

		if (!this.OpenRadioPort(device, true))
			throw new DeviceNotFoundException("Could not open " + device);
	}

	/**
	 * Get an instance of a Monkeyboard
	 * <p>You will always get the same Monkeyboard instance for a specific device</p>
	 * 
	 * @param device Path to the device, e.g. /dev/ttyACM0
	 * @param library Library name, e.g. keystonecomm (linux)
	 * @return an instance if a Monkeyboard
	 * @throws DeviceNotFoundException
	 * @throws InvalidPropertiesException
	 */
	public static Monkeyboard getInstance(String device, String library)
			throws DeviceNotFoundException, InvalidPropertiesException {
		if (!INSTANCES.containsKey(device))
			INSTANCES.put(device, new Monkeyboard(device, library));

		return INSTANCES.get(device);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#ClearDatabase()
	 */
	@Override
	public boolean ClearDatabase() {
		return monkeyBoard.ClearDatabase();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#CloseRadioPort()
	 */
	@Override
	public boolean CloseRadioPort() {
		return monkeyBoard.CloseRadioPort();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#DABAutoSearch(int,
	 * int)
	 */
	@Override
	public boolean DABAutoSearch(int startIndex, int endIndex) {
		return monkeyBoard.DABAutoSearch((byte) startIndex, (byte) endIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#DABAutoSearchNoClear(int,
	 * int)
	 */
	@Override
	public boolean DABAutoSearchNoClear(int startIndex, int endIndex) {
		return monkeyBoard.DABAutoSearchNoClear((byte) startIndex,
				(byte) endIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetApplicationType(int)
	 */
	@Override
	public ApplicationType GetApplicationType(int dabIndex) {
		return ApplicationType.getByValue(monkeyBoard
				.GetApplicationType(new NativeLong(dabIndex)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetBBEEQ()
	 */
	@Override
	public GetBBEEQResult GetBBEEQ() {
		byte b = 0;
		byte BBEOn = new Byte(b);
		byte EQMode = new Byte(b);
		byte BBELo = new Byte(b);
		byte BBEHi = new Byte(b);
		byte BBECFreq = new Byte(b);
		byte BBEMachFreq = new Byte(b);
		byte BBEMachGain = new Byte(b);
		byte BBEMachQ = new Byte(b);
		byte BBESurr = new Byte(b);
		byte BBEMp = new Byte(b);
		byte BBEHpF = new Byte(b);
		byte BBEHiMode = new Byte(b);

		if (!monkeyBoard.GetBBEEQ(BBEOn, EQMode, BBELo, BBEHi, BBECFreq,
				BBEMachFreq, BBEMachGain, BBEMachQ, BBESurr, BBEMp, BBEHpF,
				BBEHiMode))
			return null;

		GetBBEEQResult r = new GetBBEEQResult();
		r.setBBEOn(BBEOn);
		r.setEQMode(EQMode);
		r.setBBELo(BBELo);
		r.setBBEHi(BBEHi);
		r.setBBECFreq(BBECFreq);
		r.setBBEMachFreq(BBEMachFreq);
		r.setBBEMachGain(BBEMachGain);
		r.setBBEMachQ(BBEMachQ);
		r.setBBESurr(BBESurr);
		r.setBBEMp(BBEMp);
		r.setBBEHpF(BBEHpF);
		r.setBBEHiMode(BBEHiMode);

		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetDABSignalQuality()
	 */
	@Override
	@Deprecated
	public int GetDABSignalQuality() {
		return (int) monkeyBoard.GetDABSignalQuality();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetDataRate()
	 */
	@Override
	public int GetDataRate() {
		return monkeyBoard.GetDataRate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetEnsembleName(int,
	 * boolean)
	 */
	@Override
	public String GetEnsembleName(long dabIndex, boolean shortname) {
		WString buffer = new WString("");

		if (!monkeyBoard.GetEnsembleName(new NativeLong(dabIndex),
				(byte) (shortname ? 0 : 1), buffer))
			return "";

		return buffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetFrequency()
	 */
	@Override
	public int GetFrequency() {
		return monkeyBoard.GetFrequency();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetHeadroom()
	 */
	@Override
	public int GetHeadroom() {
		return monkeyBoard.GetHeadroom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetImage()
	 */
	@Override
	public String GetImage() {
		char[] buffer = new char[300];
		monkeyBoard.GetImage(buffer);
		return String.valueOf(buffer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetPlayIndex()
	 */
	@Override
	public long GetPlayIndex() {
		return monkeyBoard.GetPlayIndex().longValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetPlayMode()
	 */
	@Override
	public PlayMode GetPlayMode() {
		PlayMode result = PlayMode.getByValue((int) monkeyBoard.GetPlayMode());

		if (result == PlayMode.FUNCTION_FAILED)
			return PlayMode.FUNCTION_FAILED;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetPlayStatus()
	 */
	@Override
	public PlayStatus GetPlayStatus() {
		PlayStatus result = PlayStatus.getByValue(monkeyBoard.GetPlayStatus());

		if (result == PlayStatus.FUNCTION_FAILED)
			return PlayStatus.FUNCTION_FAILED;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetPreset(int, int)
	 */
	@Override
	public long GetPreset(int mode, int presetIndex) {
		return monkeyBoard.GetPreset((byte) mode, (byte) presetIndex)
				.longValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetProgramInfo(long)
	 */
	@Override
	public GetProgramInfoResult GetProgramInfo(long dabIndex) {

		byte b = 0;
		byte ServiceComponentID = new Byte(b);
		byte ServiceID = new Byte(b);
		byte EnsembleID = new Byte(b);

		if (!monkeyBoard.GetProgramInfo(new NativeLong(dabIndex),
				ServiceComponentID, ServiceID, EnsembleID))
			return null;

		GetProgramInfoResult r = new GetProgramInfoResult();
		r.setServiceComponentID(ServiceComponentID);
		r.setEnsembleID(EnsembleID);
		r.setServiceID(ServiceID);

		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetProgramName(de.cunctus
	 * .Monkeyboard.Enums.PlayMode, long, boolean)
	 */
	@Override
	public String GetProgramName(PlayMode mode, long dabIndex, boolean shortname) {
		char[] buffer = new char[300];
		if (!monkeyBoard.GetProgramName((byte) mode.getValue(), new NativeLong(
				dabIndex), (byte) (shortname ? 0 : 1), buffer))
			return "";

		return String.valueOf(buffer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetProgramText()
	 */
	@Override
	public String GetProgramText() {
		char[] buffer = new char[300];

		byte result = monkeyBoard.GetProgramText(buffer);

		if (result == 0)
			programText = String.valueOf(buffer);
		else if (result == -1)
			return "";

		return programText;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetProgramType(de.cunctus
	 * .Monkeyboard.Enums.PlayMode, long)
	 */
	@Override
	public ProgramType GetProgramType(PlayMode mode, long dabIndex) {
		return ProgramType.getByValue(monkeyBoard.GetProgramType(
				(byte) mode.getValue(), new NativeLong(dabIndex)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetRTC()
	 */
	@Override
	public GetRTCResult GetRTC() {
		byte b = 0;
		byte sec = new Byte(b);
		byte min = new Byte(b);
		byte hour = new Byte(b);
		byte day = new Byte(b);
		byte month = new Byte(b);
		byte year = new Byte(b);

		if (!monkeyBoard.GetRTC(sec, min, hour, day, month, year))
			return null;

		GetRTCResult r = new GetRTCResult();
		r.setSec(sec);
		r.setMin(min);
		r.setHour(hour);
		r.setDay(day);
		r.setMonth(month);
		r.setYear(year);

		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetSamplingRate()
	 */
	@Override
	public int GetSamplingRate() {
		return monkeyBoard.GetSamplingRate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetServCompType(long)
	 */
	@Override
	public ServCompType GetServCompType(long dabIndex) {
		return ServCompType.getByValue(monkeyBoard
				.GetServCompType(new NativeLong(dabIndex)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetSignalStrength(int)
	 */
	@Override
	public GetSignalStrengthResult GetSignalStrength(int biterror) {
		IntByReference buffer = new IntByReference(biterror);
		byte strength = monkeyBoard.GetSignalStrength(buffer);

		return new GetSignalStrengthResult(buffer.getValue(), strength);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetStereo()
	 */
	@Override
	public Stereo GetStereo() {
		return Stereo.getByValue(monkeyBoard.GetStereo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetStereoMode()
	 */
	@Override
	public StereoMode GetStereoMode() {
		StereoMode result = StereoMode.getByValue(monkeyBoard.GetStereoMode());

		if (result == StereoMode.FUNCTION_FAILED)
			return StereoMode.FUNCTION_FAILED;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetTotalProgram()
	 */
	@Override
	public int GetTotalProgram() {
		return monkeyBoard.GetTotalProgram().intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#GetVolume()
	 */
	@Override
	public int GetVolume() {
		return monkeyBoard.GetVolume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#HardResetRadio()
	 */
	@Override
	public boolean HardResetRadio() {
		return monkeyBoard.HardResetRadio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#IsSysReady()
	 */
	@Override
	public boolean IsSysReady() {
		return monkeyBoard.IsSysReady();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#MotQuery()
	 */
	@Override
	public boolean MotQuery() {
		return monkeyBoard.MotQuery();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#MotReset(int)
	 */
	@Override
	public void MotReset(int enMode) {
		monkeyBoard.MotReset((byte) enMode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#NextStream()
	 */
	@Override
	public boolean NextStream() {
		return monkeyBoard.NextStream();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#OpenRadioPort(java.lang
	 * .String, boolean)
	 */
	@Override
	public boolean OpenRadioPort(String port, boolean usehardmute) {
		return monkeyBoard.OpenRadioPort(this.device, usehardmute);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#PlayStream(de.cunctus.
	 * Monkeyboard.Enums.PlayMode, long)
	 */
	@Override
	public boolean PlayStream(PlayMode mode, long channel) {
		return monkeyBoard.PlayStream((byte) mode.getValue(), new NativeLong(
				channel));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#PrevStream()
	 */
	@Override
	public boolean PrevStream() {
		return monkeyBoard.PrevStream();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#SetBBEEQ(int, int,
	 * int, int, int, int, int, int, int, int, int, int)
	 */
	@Override
	public boolean SetBBEEQ(int BBEOn, int EQMode, int BBELo, int BBEHi,
			int BBECFreq, int BBEMachFreq, int BBEMachGain, int BBEMachQ,
			int BBESurr, int BBEMp, int BBEHpF, int BBEHiMode) {
		return monkeyBoard.SetBBEEQ((byte) BBEOn, (byte) EQMode, (byte) BBELo,
				(byte) BBEHi, (byte) BBECFreq, (byte) BBEMachFreq,
				(byte) BBEMachGain, (byte) BBEMachQ, (byte) BBESurr,
				(byte) BBEMp, (byte) BBEHpF, (byte) BBEHiMode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#SetHeadroom(int)
	 */
	@Override
	public boolean SetHeadroom(int headroom) {
		return monkeyBoard.SetHeadroom((byte) headroom);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#SetPreset(de.cunctus.
	 * Monkeyboard.Enums.PlayMode, int, long)
	 */
	@Override
	public boolean SetPreset(PlayMode mode, int presetindex, long channel) {
		return monkeyBoard.SetPreset((byte) mode.getValue(),
				(byte) presetindex, new NativeLong(channel));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#SetStereoMode(de.cunctus
	 * .Monkeyboard.Enums.StereoMode)
	 */
	@Override
	public boolean SetStereoMode(StereoMode mode) {
		return monkeyBoard.SetStereoMode((byte) mode.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#SetVolume(int)
	 */
	@Override
	public boolean SetVolume(int volume) {
		return monkeyBoard.SetVolume((byte) volume);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#StopStream()
	 */
	@Override
	public boolean StopStream() {
		return monkeyBoard.StopStream();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#SyncRTC(boolean)
	 */
	@Override
	public boolean SyncRTC(boolean sync) {
		return monkeyBoard.SyncRTC(sync);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#VolumeMinus()
	 */
	@Override
	public int VolumeMinus() {
		return monkeyBoard.VolumeMinus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#VolumeMute()
	 */
	@Override
	public void VolumeMute() {
		monkeyBoard.VolumeMute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cunctus.Monkeyboard.Interfaces.IMonkeyboard#VolumePlus()
	 */
	@Override
	public int VolumePlus() {
		return monkeyBoard.VolumePlus();
	}

}
