using namespace std;

#include "../include/frames/Frame.h"
#include "../include/event.h"
#include "UserData.h"
#include "ConnectionHandler.h"
#include "Summary.h"

#include <ios>


UserData *UserData::instance;

UserData::UserData()
    : shouldTerminateFlag(false), connected(false), nextReceiptNumber(0), nextSubscriptionNumber(0),
      userName(), m(), cv(), handler(nullptr), gameNameToSubId(), subIdToGameName(), gameSummaries() {}

UserData &UserData::getInstance()
{
    if (instance == nullptr)
    {
        instance = new UserData();
    }
    return *instance;
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
    instance = nullptr;
}

UserData::~UserData()
{
    delete handler;
    for (auto &pair : gameSummaries)
    {
        delete pair.second;
    }
}

int UserData::getReceiptId()
{
    return nextReceiptNumber++;
}

int UserData::generateSubId(string topic)
{
    return nextSubscriptionNumber++;
}

int UserData::getSubId(string topic)
{
    return gameNameToSubId[topic];
}

void UserData::addGameEvent(Event &gameEvent) {
    string reporter = gameEvent.get_reporter();
    string gameName = gameEvent.get_game_name();
    GameReport gameReport(reporter, gameName);
    auto it = gameSummaries.find(gameReport);
    Summary* summary;
    if (it != gameSummaries.end()) {
        summary = it->second;
    } else {
        summary = new Summary(reporter, gameName);
        gameSummaries[gameReport] = summary;
    }
    summary->addEvent(gameEvent);
}

string UserData::getSummary(string reporter, string gameName) const
{
    GameReport gameReport(reporter, gameName);
    Summary *summary = nullptr;
    try {
        summary = gameSummaries.at(gameReport);
    } catch (const std::out_of_range& ignored) {
        throw std::ios_base::failure("No such game, or no such reporter");
    }
    string summaryString = summary->printSummary();
    return summaryString;
}
