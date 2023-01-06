CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system -lpthread

all: bin/StompWCIClient

StompWCIClient: bin/ConnectionHandler.o bin/StompClient.o bin/event.o
	g++ -o bin/StompWCIClient bin/ConnectionHandler.o bin/StompClient.o $(LDFLAGS)

bin/ConnectionHandler.o: src/ConnectionHandler.cpp
	g++ $(CFLAGS) -o bin/ConnectionHandler.o src/ConnectionHandler.cpp

bin/event.o: src/event.cpp
	g++ $(CFLAGS) -o bin/event.o src/event.cpp

bin/StompClient.o: src/StompClient.cpp
	g++ $(CFLAGS) -o bin/StompClient.o src/StompClient.cpp

bin/Frames.o: src/Frames.cpp
	g++ $(CFLAGS) -o bin/Frames.o src/Frames.cpp

bin/ExecutableFrames.o: src/ExecutableFrames.cpp
	g++ $(CFLAGS) -o bin/ExecutableFrames.o src/ExecutableFrames.cpp

bin/ConnectedFrame.o: src/ConnectedFrame.cpp
	g++ $(CFLAGS) -o bin/ConnectedFrame.o src/ConnectedFrame.cpp

bin/ConnectionFrame.o: src/ConnectionFrame.cpp
	g++ $(CFLAGS) -o bin/ConnectionFrame.o src/ConnectionFrame.cpp

bin/DisconnectFrame.o: src/DisconnectFrame.cpp
	g++ $(CFLAGS) -o bin/DisconnectFrame.o src/DisconnectFrame.cpp

bin/ErrorFrame.o: src/ErrorFrame.cpp
	g++ $(CFLAGS) -o bin/ErrorFrame.o src/ErrorFrame.cpp

bin/MessageFrame.o: src/MessageFrame.cpp
	g++ $(CFLAGS) -o bin/MessageFrame.o src/MessageFrame.cpp

bin/ReceiptFrame.o: src/ReceiptFrame.cpp
	g++ $(CFLAGS) -o bin/ReceiptFrame.o src/ReceiptFrame.cpp

bin/SendFrame.o: src/SendFrame.cpp
	g++ $(CFLAGS) -o bin/SendFrame.o src/SendFrame.cpp

bin/SubscribeFrame.o: src/SubscribeFrame.cpp
	g++ $(CFLAGS) -o bin/SubscribeFrame.o src/SubscribeFrame.cpp

bin/UnsubscribeFrame.o: src/UnsubscribeFrame.cpp
	g++ $(CFLAGS) -o bin/UnsubscribeFrame.o src/UnsubscribeFrame.cpp

.PHONY: clean
clean:
	rm -f bin/*
	