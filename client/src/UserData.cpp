using namespace std;

#include "UserData.h"
#include "../include/frames/Frame.h"
#include "../include/event.h"
#include "ConnectionHandler.h"
#include "Summary.h"

UserData *UserData::instance;

UserData::UserData()
    : shouldTerminateFlag(false), connected(false), nextReceiptNumber(0), nextSubscriptionNumber(0),
      userName(), m(), cv(), handler(nullptr), frameQueue(), gameNameToSubId(), subIdToGameName(), gameSummaries() {}

mutex &UserData::getLock()
{
    return m;
}

UserData &UserData::getInstance()
{
    if (instance == NULL)
    { // why not nullptr?
        instance = new UserData();
    }
    return *instance;
}

void UserData::addAction(Frame *frame)
{
    frameQueue.push(frame);
}

void UserData::setUserName(string userName)
{
    this->userName = userName;
}

string &UserData::getUserName()
{
    return userName;
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

void UserData::setHandler(ConnectionHandler &handler)
{
    this->handler = &handler;
}

ConnectionHandler &UserData::getHandler()
{
    return *handler;
}

void UserData::deleteInstance(bool b1, bool b2, bool b3)
{
    if (b1 & b2 & b3)
        delete instance;
    instance = NULL;
}

UserData::~UserData()
{
    delete handler;
    // TODO: delete event and summary pointers
}

int UserData::getReceiptId()
{
    return nextReceiptNumber++;
}

int UserData::generateSubId(string topic)
{
    // TODO: add to topics map
    return nextSubscriptionNumber++;
}

int UserData::getSubId(string topic)
{
    return gameNameToSubId[topic];
}

string UserData::getGameName(int subId)
{
    return subIdToGameName[subId];
}

void UserData::addGameEvent(Event *gameEvent)
{
    string reporter = gameEvent->get_reporter();
    string gameName = gameEvent->get_game_name();
    GameReport gameReport(reporter, gameName);
    Summary *summary = new Summary(reporter, gameName);
    gameSummaries[gameReport] = summary;
}

string UserData::getSummary(string reporter, string gameName) const
{
    GameReport gameReport(reporter, gameName);
    Summary *summary = nullptr;
    try {
        summary = gameSummaries.at(gameReport);
    } catch (const std::out_of_range& oor) {
        cout << "No such game, or no such reporter, returning empty string" << endl;
        return "";
    }
    string summaryString = summary->printSummary(); // warning: reference to local variable ‘summaryString’ returned
    return summaryString;
}
