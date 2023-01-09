#include "UserData.h"

mutex& UserData::getLock()
{
    return m;
}

UserData::UserData(): shouldTerminateFlag(false), connected(false), handler(nullptr), userName(), frameQueue(), m(), cv()
{
}


// UserData * UserData::getInstancePtr(){
//     static UserData instance;
//     return &instance;
// }

void UserData::addAction(Frame* frame)
{
    frameQueue.push(frame);
}

void UserData::setUserName(string userName)
{
    this->userName = userName;
}

void UserData::setPassword(string password)
{
    this->password = password;
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


void UserData::setHandler(ConnectionHandler& handler)
{
    this->handler = &handler;
}


ConnectionHandler& UserData::getHandler()
{
    return *handler;
}
