/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package zenryokuservice.socket.server.midi;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;


/**
 * マイクロビットから受信したメッセージのハンドリング後に
 * MIDIで伴奏を作成するクラス。
 * @author takunoji
 * 2019/10/19
 */
public class MidiCreator {
	private Sequencer seq;
	private Transmitter seqTrans;
	private Synthesizer synth;
	private Receiver synthRcvr;
	
	/** コンストラクタ */
	public MidiCreator() {
	    try {
	          seq = MidiSystem.getSequencer();
	          seqTrans = seq.getTransmitter();
	          synth = MidiSystem.getSynthesizer();
	          synthRcvr = synth.getReceiver(); 
	          seqTrans.setReceiver(synthRcvr);	
	    } catch (MidiUnavailableException e) {
	          // handle or throw exception
	    }
	}
}
