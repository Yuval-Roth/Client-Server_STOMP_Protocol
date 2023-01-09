#pragma once

using namespace std;

#include "Frame.h"
#include <mutex>
#include <condition_variable>
#include <queue>


class UserData{

    private:

    mutex m;
    condition_variable cv;
    string userName;
    bool connected;
    queue<Frame> actionQueue;

    public:

    UserData() = default;
    void addAction(Frame frame);
    void setUserName(string userName);
    string getUserName();
    void wait();
    void notifyAll();
    queue<Frame> getActionQueue();
    bool isConnected();
    void setConnected(bool connected);

};
