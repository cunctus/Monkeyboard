# Monkeyboard
A Java wrapper for the Monkeyboard DAB/DAB+ and FM Receiver 

You need a running native library installed, check out http://www.monkeyboard.org/tutorials/78-interfacing/87-raspberry-pi-linux-dab-fm-digital-radio for instructions how to get a native library.

You can control the Monkeyboard DAB/DAB+ & FM receiver with this Java Wrapper. It is in very early development stage, so please report any errors etc. to help me improving the Wrapper. Thx!

Java Native Access (JNA) is required by this project. When using eclipse/maven the dependencies will be downloaded automatically. Running "Maven install" will build the monkeyboard jar file and copy all needed jars to /jar/.

The MonkeyboardServer (https://github.com/cunctus/MonkeyboardServer) provides remote access to the Monkeyboard via Socket and RMI (Remote Method Invocation). Not all functions are implemented yet, but it is extended step by step.

This software is open-source and released under the GNU GPL v3 (see LICENSE file).

