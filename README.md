# SocketServer
Control midi message from microbit to midi module. via raspberry pi.
マイクロビットから、MIDIメッセージをラズパイに送信し、MIDI音源を鳴らす。
### Use case
1. Shake or push button or something.
2. send message to RPi.
3. Java socket server recieve this message in RPi.
4. select or create midi message to midi module.
5. send midi message to midi module like RT-123(Zoom)

1. マイクロビットを振る、ボタンを押すなどする
2. ラズパイにメッセージを送信
3. ラズパイ上の、JavaSocketサーバーがメッセージを受信する
4. MIDI音源へのMIDIメッセージを選択、作成
5. MIDIメッセージをZOOMの様な音源モジュールに送信する

## Work flow
### Setup develop environment
1. Create ripository on RPi
2. Create java socket server for RPi
