#include "SubscribeFrame.h"
#include "UserData.h"


SubscribeFrame *SubscribeFrame::get(string gameName) {
    unordered_map<string, string> headers;
    headers["destination"] = "/" + gameName;
    headers["id"] = to_string(UserData::getInstance().generateSubscriptionID(gameName));
    // TODO: check if this is the right way to do it
    headers["receipt"] = to_string(UserData::getInstance().getRecieptID());
    return new SubscribeFrame(StompCommand::SUBSCRIBE, headers, "");
}


