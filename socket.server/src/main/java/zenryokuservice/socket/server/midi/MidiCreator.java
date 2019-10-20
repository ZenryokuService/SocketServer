/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package zenryokuservice.socket.server.midi;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;


/**
 * マイクロビットから受信したメッセージのハンドリング後に
 * MIDIで伴奏を作成するクラス。
 * @author takunoji
 * @see https://docs.oracle.com/javase/jp/6/technotes/guides/sound/programmer_guide/index.html
 * 2019/10/19
 */
public class MidiCreator implements MetaEventListener {
	// Midi meta event
	public static final int END_OF_TRACK_MESSAGE = 47;	private Sequencer seq;
	private Synthesizer synth;
	
	/** コンストラクタ */
	public MidiCreator() {
	    try {
	          seq = MidiSystem.getSequencer();
	          seq.open();
	          seq.addMetaEventListener(this);
	          synth = MidiSystem.getSynthesizer();
	    } catch (MidiUnavailableException e) {
	    	e.printStackTrace();
          // handle or throw exception
	    }
	}

	/**
	 * MIDIファイルを再生する
	 * @param midi MIDIファイル
	 * @throws InvalidMidiDataException
	 * @throws IOException
	 */
	public void soundMidi(File midi) throws InvalidMidiDataException, IOException {
		Sequence mySeq = MidiSystem.getSequence(midi);
		seq.setSequence(mySeq);
		seq.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		seq.stop();
	}

	/** オブジェクト削除時に走る */
	@Override
	public void finalize() {
		seq.close();
		this.seq = null;
		this.synth = null;
	}
	/**
	 * 1. デバイスのオープン
	 * 2. 
	 * @throws InvalidMidiDataException 
	 */
	@Deprecated
	public void sendMidi() throws MidiUnavailableException, InvalidMidiDataException{
		if (synth.isOpen()) {
			synth.open();
		}
		long timeStamp = -1;
		Receiver synthRcvr = synth.getReceiver();
		Transmitter input = synth.getTransmitter();
		input.setReceiver(synthRcvr);

		// 送信するメッセージを作成と設定
		synthRcvr.send(createBackingA(), timeStamp);
	}

	private ShortMessage createBackingA() throws InvalidMidiDataException {
		ShortMessage mes = new ShortMessage();
		mes.setMessage(ShortMessage.NOTE_ON, 0, 60, 93);

		return mes;
	}

	/* (non-Javadoc)
	 * @see javax.sound.midi.MetaEventListener#meta(javax.sound.midi.MetaMessage)
	 */
	@Override
	public void meta(MetaMessage meta) {
		// Auto-generated method stub
		if (meta.getType() == this.END_OF_TRACK_MESSAGE) {
			if (seq != null && seq.isOpen() && true) {
				seq.start();
			}
		}
	}

	/**
	 * @return the seq
	 */
	public Sequencer getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Sequencer seq) {
		this.seq = seq;
	}

	/**
	 * @return the synth
	 */
	public Synthesizer getSynth() {
		return synth;
	}

	/**
	 * @param synth the synth to set
	 */
	public void setSynth(Synthesizer synth) {
		this.synth = synth;
	}
}
