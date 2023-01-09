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
        bool connected;
        string userName;
        string password;
        mutex m;
        queue<Frame*> frameQueue;
        condition_variable cv;
        bool shouldTerminateFlag;
        ConnectionHandler* handler;

    public:

        // ~UserData() = delete; // Prevent destruction
        // UserData(const UserData&) = delete; // Prevent copy-construction
        // UserData& operator=(const UserData&) = delete; // Prevent assignment
       // check if they're needed
        mutex& getLock();

        static UserData& getInstance(){ 
            /*should be declare here as it is a static method and not a member method.
             Do NOT move this to the .cpp file*/
            static UserData instance;
            return instance;
        };

        bool isConnected();
        void setConnected(bool connected);

        void setUserName(string userName);
        string& getUserName();

        void setPassword(string password);

        void wait();
        void notifyAll();

        void addAction(Frame* frame);
        queue<Frame*>& getFrameQueue();
        ConnectionHandler& getHandler();
        void setHandler(ConnectionHandler& handler);
        bool shouldTerminate();

};
