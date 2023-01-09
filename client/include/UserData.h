#pragma once

using namespace std;

#include "Frame.h"
#include <mutex>
#include <condition_variable>
#include <queue>
#include "ConnectionHandler.h"
class Frame;

class UserData{

    private:

        UserData(); // Private constructor. May want to create an actual constructor later. 
        // singleton
        bool shouldTerminateFlag;
        bool connected;
        string userName;
        // string password;
        mutex m;
        condition_variable cv;
        ConnectionHandler* handler;
        queue<Frame*> frameQueue;

        static UserData* instance;

    public:

        UserData(const UserData&) = delete; 
        UserData& operator=(const UserData&) = delete;
        // check if they're needed
        mutex& getLock();

        static UserData& getInstance();

        bool isConnected();
        void setConnected(bool connected);

        void setUserName(string userName);
        string& getUserName();

        // uneccesary. what purpose does it serve?
        // void setPassword(string password);

        void wait();
        void notifyAll();

        void addAction(Frame* frame);
        queue<Frame*>& getFrameQueue();
        ConnectionHandler& getHandler();
        void setHandler(ConnectionHandler& handler);
        bool shouldTerminate();

        ~UserData();
};
