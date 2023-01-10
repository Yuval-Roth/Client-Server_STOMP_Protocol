#pragma once

using namespace std;

#include <mutex>
#include <condition_variable>
#include <queue>

class ConnectionHandler;
class Frame;

class UserData{

    //====================================================================================|
    //================================ Fields ============================================|
    //====================================================================================|

    private:

        static UserData* instance;

        bool shouldTerminateFlag;
        bool connected;
        string userName;
        mutex m;
        condition_variable cv;
        ConnectionHandler* handler;
        queue<Frame*> frameQueue;


    //====================================================================================|
    //================================ Methods ===========================================|
    //====================================================================================|
    
    public:

        // we need these to be deleted functions so the comiler would not
        // allow us to use a copy constructor and a copy assignment operator
        // also it gives warnings because we have pointers and didn't implement these
        // so it's necessary to delete these methods.
        UserData(const UserData&) = delete; 
        UserData& operator=(const UserData&) = delete;

        mutex& getLock();

        bool isConnected();
        void setConnected(bool connected);

        void setUserName(string userName);
        string& getUserName();

        void wait();
        void notifyAll();

        void addAction(Frame* frame);
        queue<Frame*>& getFrameQueue();
        ConnectionHandler& getHandler();
        void setHandler(ConnectionHandler& handler);

        bool shouldTerminate();

        static UserData& getInstance();
        static void deleteInstance(bool,bool,bool);

    private:
    
        UserData(); // Private constructor. May want to create an actual constructor later. 
        ~UserData();
        
};
