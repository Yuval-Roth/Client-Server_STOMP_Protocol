#pragma once

using namespace std;

#include "Frame.h"
#include <mutex>
#include <condition_variable>
#include <queue>


class UserData{

    private:
        // singleton
        static UserData* instance;
        UserData();
        ~UserData();
        bool loggedIn;
        string userName;
        mutex m;
        condition_variable cv;
        UserData(); // Private constructor
        UserData(const UserData&) = delete; // Prevent copy-construction
        UserData& operator=(const UserData&) = delete; // Prevent assignment

    public:
        static UserData& getInstance()
        {
            static UserData instance;
            return instance;
        }
        void addAction(Frame frame);
        void setUserName();
        void setLoggedIn(bool loggedIn);
        void wait();
        void notifyAll();
        queue<Frame> getActionQueue();

};
