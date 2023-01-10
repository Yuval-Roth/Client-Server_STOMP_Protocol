using namespace std;

#include "UserData.h"
#include "Frame.h"
#include "ConnectionHandler.h"

UserData* UserData::instance;

UserData::UserData()
    : shouldTerminateFlag(false), connected(false), nextRecieptNumber(0),nextSubscriptionNumber(0),
    userName(), m(), cv(), handler(nullptr), frameQueue(){}

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

void UserData::terminate()
{
    shouldTerminateFlag = true;
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

void UserData::deleteInstance(bool b1, bool b2, bool b3)
{
    if(b1 & b2 & b3) delete instance;
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

int UserData::getReceiptId() {
    return nextRecieptNumber++;
}

int UserData::generateSubId(string topic) {
    // TODO: add to topics map
    return nextSubscriptionNumber++;
}

void UserData::addGameEvent(GameEvent *gameEvent)
{
    gameEvents.push_back(gameEvent);
}

list<GameEvent *> &UserData::getGameEvents()
{
    return gameEvents;
}
