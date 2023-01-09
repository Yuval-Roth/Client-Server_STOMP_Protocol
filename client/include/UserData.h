#pragma once

using namespace std;

#include "Frame.h"
#include <mutex>
#include <condition_variable>
#include <queue>


class UserData{

    private:
    ~UserData();
    bool loggedIn;
    string userName;

    mutex m;
    condition_variable cv;

    public:

    void addAction(Frame frame);
    void setUserName();
    void setLoggedIn(bool loggedIn);
    UserData();
    void wait();
    void notifyAll();
    queue<Frame> getActionQueue();

};
