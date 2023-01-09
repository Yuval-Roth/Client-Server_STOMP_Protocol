#include "UserData.h"

mutex& UserData::getLock()
{
    return m;
}

UserData &UserData::getInstance()
{
    //TODO - implement UserData::getInstance

    return * new UserData(); 
}

void UserData::addAction(Frame* frame)
{
    frameQueue.push(frame);
}

void UserData::setUserName(string userName)
{
    this->userName = userName;
}

string& UserData::getUserName()
{
    return userName;
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

queue<Frame*>& UserData::getFrameQueue()
{
    return frameQueue;
}

bool UserData::shouldTerminate()
{
    return shouldTerminateFlag;
}

bool UserData::isConnected()
{
    return connected;
}

void UserData::setConnected(bool connected)
{
    this->connected = connected;
}

