#include "UserData.h"


UserData::UserData()
{
    loggedIn = false;
}

void UserData::wait()
{
    unique_lock<mutex> lock(m);
    cv.wait(lock);
    lock.unlock();
}

void UserData::notifyAll()
{
    cv.notify_all();
}

void UserData::addAction(Frame frame)
{
    actionQueue.push(frame);
}

void UserData::setLoggedIn(bool loggedIn)
{
    this->loggedIn = loggedIn;
}