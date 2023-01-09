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

    public:

    void addAction(Frame frame);
    void setUserName();
    void wait();
    void notifyAll();
    queue<Frame> getActionQueue();

};
