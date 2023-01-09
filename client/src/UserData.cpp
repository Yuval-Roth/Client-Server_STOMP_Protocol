#include "UserData.h"

UserData* UserData::instance;

mutex& UserData::getLock()
{
    return m;
}

UserData &UserData::getInstance()
{
    if(instance == NULL){
        instance = new UserData();
    }
    return *instance;
}

UserData::UserData()
    : shouldTerminateFlag(false), connected(false), userName(), m(),cv(), handler(nullptr), frameQueue()
{
}

void UserData::addAction(Frame* frame)
{
    frameQueue.push(frame);
}

void UserData::setUserName(string userName)
{
    this->userName = userName;
}

// void UserData::setPassword(string password)
// {
//     this->password = password;
// }

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

UserData::~UserData()
{
    delete handler;
    while(frameQueue.empty() == false){
        Frame* toDelete = frameQueue.front();
        frameQueue.pop();
        delete toDelete;
    }
    delete instance;
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
