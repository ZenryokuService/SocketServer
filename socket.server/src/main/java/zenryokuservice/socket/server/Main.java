/**
 * Copyright (c) 2019-present ZS Socket Server Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package zenryokuservice.socket.server;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.L2CAPConnectionNotifier;
import javax.microedition.io.Connector;

/**
 * BluetoothAPIを使用して実装する。
 * @author takunoji
 * 
 * 2019/10/06
 */
public class Main {
	public static void main(String[] args) {
		try {
			L2CAPConnectionNotifier server = (L2CAPConnectionNotifier)
			Connector.open("btl2cap://localhost:3B9FA89520078C303355AAA694238F08;name=L2CAP Server1");
			L2CAPConnection cliCon = (L2CAPConnection)server.acceptAndOpen();
		} catch (IOException e) {
		    /* Handle the failure to setup a connection. */
		}
	}
}
